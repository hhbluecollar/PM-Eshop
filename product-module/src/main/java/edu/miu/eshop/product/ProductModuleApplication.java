package edu.miu.eshop.product;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.logging.Logger;

@EnableDiscoveryClient
@SpringBootApplication
public class ProductModuleApplication extends SpringBootServletInitializer {

//	private static final Logger logger = (Logger) LoggerFactory.getLogger(ProductModuleApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ProductModuleApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ProductModuleApplication.class);
	}
}