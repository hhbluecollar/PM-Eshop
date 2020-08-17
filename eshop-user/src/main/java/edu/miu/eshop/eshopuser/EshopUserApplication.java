package edu.miu.eshop.eshopuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class EshopUserApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(EshopUserApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(EshopUserApplication.class);
	}
}
