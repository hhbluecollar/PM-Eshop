package edu.miu.eshop.eshopadmin.service;

import edu.miu.eshop.eshopadmin.domain.Dto.CustomerDto;
import edu.miu.eshop.eshopadmin.domain.Dto.ProductDto;
import edu.miu.eshop.eshopadmin.domain.Product;
import edu.miu.eshop.eshopadmin.domain.Promotion;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ModelMapper modelMapper;

    Properties prop;
    InputStream inputStream;
    String propFileName = "application.properties";

    @PostConstruct
    private void initProperty() throws IOException {

        prop = new Properties();

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
    public List<Promotion> getAll() {
        ResponseEntity<List<Promotion>> response = restTemplate.exchange(
                prop.getProperty("eshop.product.url.base") + "/promotions",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>(){});
        return response.getBody();
    }

    @Override
    public Promotion get(String promotionId) {
        return restTemplate.getForObject(prop.getProperty("eshop.product.url.base") + "/promotions/{promotionId}", Promotion.class, promotionId);
    }

    @Override
    public void delete(String promotionId) {
        restTemplate.delete(prop.getProperty("eshop.product.url.base") + "/promotions/" + promotionId);
    }

    @Override
    public Promotion update(String promotionId, Promotion promotion) {
        HttpEntity<Promotion> entity = new HttpEntity<>(promotion);
        return restTemplate.exchange(prop.getProperty("eshop.product.url.base") + "/promotions/update/" + promotionId,
                HttpMethod.PUT,
                entity,
                Promotion.class).getBody();
    }

    @Override
    public List<Promotion> getByVendor(String vendorId) {
        ResponseEntity<List<Promotion>> response = restTemplate.exchange(
                prop.getProperty("eshop.product.url.base") + "/promotions/vendor/" + vendorId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>(){});
        return response.getBody();
    }

    @Override
    public void save(Promotion promotion) {
        restTemplate.postForObject(prop.getProperty("eshop.product.url.base") + "/promotions/add", promotion, Promotion.class);
    }
}
