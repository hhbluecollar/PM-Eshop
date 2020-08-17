package edu.miu.eshop.eshopadmin.service;

// HH

import edu.miu.eshop.eshopadmin.domain.Category;
import edu.miu.eshop.eshopadmin.domain.Dto.CategoryDto;
import edu.miu.eshop.eshopadmin.domain.Dto.ProductDto;
import edu.miu.eshop.eshopadmin.domain.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private RestTemplate restTemplate;

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
    public void saveCategory(Category category) {
        restTemplate.postForObject(prop.getProperty("eshop.product.url.base") + "/categories/create", category, Category.class);
    }

    @Override
    public void addCategory(CategoryDto categoryDto) {
        restTemplate.postForObject(prop.getProperty("eshop.product.url.base") + "/categories/add", categoryDto, CategoryDto.class);
    }

    @Override
    public void editCategory(CategoryDto categoryDto) {
        HttpEntity<CategoryDto> requestUpdate = new HttpEntity<>(categoryDto);
        restTemplate.exchange(prop.getProperty("eshop.product.url.base") + "/categories/edit", HttpMethod.PUT, requestUpdate, CategoryDto.class);
    }

    @Override
    public List<Category> getAll() {
        ResponseEntity<List<Category>> response = restTemplate.exchange(
                prop.getProperty("eshop.product.url.base") + "/categories",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Category>>(){});
        return response.getBody();
    }

    @Override
    public void deleteCategory(String categoryId) { // this is the category id in the dto used inplace of paretntId
        restTemplate.delete(prop.getProperty("eshop.product.url.base") + "/categories/delete/" + categoryId, null, CategoryDto.class);
    }
}