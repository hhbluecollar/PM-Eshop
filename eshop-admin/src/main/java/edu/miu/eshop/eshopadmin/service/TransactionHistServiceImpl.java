package edu.miu.eshop.eshopadmin.service;

import edu.miu.eshop.eshopadmin.domain.TransactionHist;
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
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

@Service
public class TransactionHistServiceImpl implements TransactionHistService{

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
    public double getTotalTransaction(boolean b) {
        if(b)
            return restTemplate.getForObject(prop.getProperty("eshop.payment.url.base") + "/card/totalsuccess", Double.class, "");
        else
            return restTemplate.getForObject(prop.getProperty("eshop.payment.url.base") + "/card/totalfailure", Double.class, "");
    }

    @Override
    public List<TransactionHist> getAllTransactionHist() {
        ResponseEntity<List<TransactionHist>> response = restTemplate.exchange(
                prop.getProperty("eshop.payment.url.base") + "/card/allTransaction",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TransactionHist>>(){});
        return response.getBody();
    }

    @Override
    public double getTotalTransactionAmt() {
        return restTemplate.getForObject(prop.getProperty("eshop.payment.url.base") + "/card/totalTransaction", Double.class, "");
    }
    @Override
    public Map<Month, Double> getTransactionByMonth(int year) {
        return restTemplate.getForObject(prop.getProperty("eshop.payment.url.base") + "/card/totalamtbymonth", Map.class, "");
    }
}
