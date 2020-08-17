package edu.miu.eshop.eshopadmin.service;

// HH

import edu.miu.eshop.eshopadmin.domain.Dto.CustomerDto;
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
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private RestTemplate restTemplate;

	HttpHeaders headers;
	Properties prop;
	InputStream inputStream;
	String propFileName = "application.properties";

	@PostConstruct
	private void initProperty() throws IOException {

		headers = new HttpHeaders();
		prop = new Properties();

		inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		headers.setContentType(MediaType.APPLICATION_JSON);

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
	public List<CustomerDto> getAll() throws IOException {

		HttpEntity<String> entity = new HttpEntity<String>(headers);
		headers.set("Authorization", "Bearer " + prop.getProperty("userModule.accessToken"));
		ResponseEntity<List<CustomerDto>> response = restTemplate.exchange(
				prop.getProperty("eshop.user.url.base") + "/users",
				HttpMethod.GET,
				entity,
				new ParameterizedTypeReference<>(){});
		return response.getBody();
	}

	@Override
	public CustomerDto findById(String customerId) throws IOException {

		headers.set("Authorization", "Bearer " + prop.getProperty("userModule.accessToken"));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return restTemplate.exchange(prop.getProperty("eshop.user.url.base") + "/users/" + customerId,
				HttpMethod.GET,
				entity,
				CustomerDto.class).getBody();
	}

	@Override
	public CustomerDto update(String customerId, CustomerDto newCustomer) {
		headers.set("Authorization", "Bearer " + prop.getProperty("userModule.accessToken"));
		HttpEntity<CustomerDto> entity = new HttpEntity<>(newCustomer, headers);
		return restTemplate.exchange(prop.getProperty("eshop.user.url.base") + "/users/" + customerId,
				HttpMethod.PUT,
				entity,
				CustomerDto.class).getBody();
	}
}