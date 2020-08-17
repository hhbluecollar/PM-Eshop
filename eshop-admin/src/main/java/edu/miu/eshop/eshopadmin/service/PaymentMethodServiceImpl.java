package edu.miu.eshop.eshopadmin.service;

import edu.miu.eshop.eshopadmin.domain.Card;
import edu.miu.eshop.eshopadmin.domain.Dto.ProductDto;
import edu.miu.eshop.eshopadmin.domain.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService{

    @Autowired
    private RestTemplate restTemplate;

    HttpHeaders headers;
    Properties prop;
    InputStream inputStream;
    String propFileName = "application.properties";

    @PostConstruct
    private void initProperty() throws IOException {

        prop = new Properties();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            try {
                throw new FileNotFoundException("Property file '" + propFileName + "' not found in the classpath");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public PaymentMethod findPaymentMethodById(String id) {
        return restTemplate.getForObject(prop.getProperty("eshop.payment.url.base") + "/card/paymentmethod/{id}", PaymentMethod.class, id);
    }

    @Override
    public void deletePaymentMethod(String id) {
        restTemplate.getForObject(prop.getProperty("eshop.payment.url.base") + "/card/deletepaymentmethod/{id}", String.class, id);
    }

    @Override
    public List<PaymentMethod> getAllPaymentMethod() {
        ResponseEntity<List<PaymentMethod>> response = restTemplate.exchange(
                prop.getProperty("eshop.payment.url.base") + "/card/allpaymentmethod",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<PaymentMethod>>(){});
        return response.getBody();
    }

    @Override
    public void addPaymentMethod(PaymentMethod paymentmethod) {
        restTemplate.postForObject(prop.getProperty("eshop.payment.url.base") + "/card/addpaymentmethod", paymentmethod, String.class);
    }
}
