package edu.miu.eshop.product.config;


import io.swagger.v3.oas.annotations.servers.Servers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class OpenApiConfig {

    // springdoc config
    @Bean
    public OpenAPI customOpenAPI() {
        List<Server> servers = new ArrayList<>();
        servers.add(new Server().url("http://localhost:8080").description("Development server"));

        return new OpenAPI().components(new Components()).info(new Info()
                .description("<p>This is PI documentation for Shopping Cart microservice.</p>")
                .title("Shopping Cart Microservice Module of eshop").version("1.0.0")).servers(servers);
    }
}
