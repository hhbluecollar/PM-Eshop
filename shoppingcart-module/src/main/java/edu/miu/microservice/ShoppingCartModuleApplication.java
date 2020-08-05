package edu.miu.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ShoppingCartModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartModuleApplication.class, args);
	}

}