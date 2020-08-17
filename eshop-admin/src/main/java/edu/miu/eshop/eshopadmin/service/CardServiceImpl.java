package edu.miu.eshop.eshopadmin.service;

import edu.miu.eshop.eshopadmin.domain.Card;
import edu.miu.eshop.eshopadmin.domain.Dto.ProductDto;
import edu.miu.eshop.eshopadmin.domain.Product;
import edu.miu.eshop.eshopadmin.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Service
public class CardServiceImpl implements CardService{

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
    public String addNewCard(Card card) {
        return restTemplate.postForObject(prop.getProperty("eshop.payment.url.base") + "/card/addcard", card, String.class);
    }

    @Override
    public Card getCard(String cardnumber) {
        return restTemplate.getForObject(prop.getProperty("eshop.payment.url.base") + "/card/getcard/{cardnumber}", Card.class, cardnumber);
    }

    @Override
    public Boolean process(Transaction transaction) {
        return restTemplate.postForObject(prop.getProperty("eshop.payment.url.base") + "/card/process", transaction, Boolean.class);
    }
}
