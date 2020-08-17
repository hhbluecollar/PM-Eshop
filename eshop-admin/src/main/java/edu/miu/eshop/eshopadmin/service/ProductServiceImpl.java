package edu.miu.eshop.eshopadmin.service;

// HH

import edu.miu.eshop.eshopadmin.domain.Dto.ProductDto;
import edu.miu.eshop.eshopadmin.domain.Dto.StatusDto;
import edu.miu.eshop.eshopadmin.domain.Product;
import edu.miu.eshop.eshopadmin.domain.ProductStatus;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
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

public class ProductServiceImpl implements ProductService {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ModelMapper modelMapper;

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

	public ProductDto getProduct(String productId) {
		return restTemplate.getForObject(prop.getProperty("eshop.product.url.base") + "/products/{productid}", ProductDto.class, productId);
	}

	@Override
	public void save(Product product) {
		restTemplate.postForObject(prop.getProperty("eshop.product.url.base") + "/products/create", product, Product.class);
	}

	@Override
	public List<ProductDto> getProductsByCategoryId(String categoryId) {
		ResponseEntity<List<ProductDto>> response = restTemplate.exchange(
				prop.getProperty("eshop.product.url.base") + "/products/category/" + categoryId,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<ProductDto>>(){});
		return response.getBody();
	}

	@Override
	public List<ProductDto> getAll() {
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<List<ProductDto>> response = restTemplate.exchange(
				prop.getProperty("eshop.product.url.base") + "/products",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<ProductDto>>(){});
		return response.getBody();
	}

	@Override
	public List<ProductDto> getProductsByCategoryName(String categoryid, String subCategoryName) {
		ResponseEntity<List<ProductDto>> response = restTemplate.exchange(
				prop.getProperty("eshop.product.url.base") + "/products/category/" + categoryid + "/" + subCategoryName,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<ProductDto>>(){});
		return response.getBody();
	}

	@Override
	public List<ProductDto> getProductsByVendorId(String findByVendroId) {
		ResponseEntity<List<ProductDto>> response = restTemplate.exchange(
				prop.getProperty("eshop.product.url.base") + "/products/vendor/" + findByVendroId,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<ProductDto>>(){});
		return response.getBody();
	}

	@Override
	public List<ProductDto> getProductsOnPromotion() {
		ResponseEntity<List<ProductDto>> response = restTemplate.exchange(
				prop.getProperty("eshop.product.url.base") + "/products/promotion",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<ProductDto>>(){});
		return response.getBody();
	}

	@Override
	public ResponseEntity<String> updateStatus(String id, StatusDto status) {
		HttpEntity<StatusDto> requestUpdate = new HttpEntity<>(status);
		return restTemplate.exchange(prop.getProperty("eshop.product.url.base") + "/products/updatestatus/" + id, HttpMethod.PUT, requestUpdate, String.class);
	}

	@Override
	public ResponseEntity<ProductDto> updateProduct(String productId, ProductDto productDto) {
		HttpEntity<ProductDto> requestUpdate = new HttpEntity<>(productDto);
		return restTemplate.exchange(prop.getProperty("eshop.product.url.base") + "/products/update/" + productId, HttpMethod.PUT, requestUpdate, ProductDto.class);
	}


	/**
	 *TO DO
	 */
	private ProductDto convertToProductDTO(Product product ) {
		modelMapper.getConfiguration()
				.setMatchingStrategy(MatchingStrategies.LOOSE);
		ProductDto productDto = modelMapper
				.map(product, ProductDto.class);
		return productDto;
	}
}