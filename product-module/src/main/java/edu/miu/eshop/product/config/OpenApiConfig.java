package edu.miu.eshop.product.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import java.util.ArrayList;

@Configuration
public class OpenApiConfig {

    // springdoc config
    @Bean
    public OpenAPI customOpenAPI() {
        var servers = new ArrayList<Server>();
        servers.add(new Server().url("http://localhost:8102").description("Development server"));

        return new OpenAPI().components(new Components()).info(new Info()
                .description("<p>This is PI documentation for Shopping Cart microservice.</p>")
                .title("Shopping Cart Microservice Module of eshop").version("1.0.0")).servers(servers);
    }

}
