package com.zti.yerbastore.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI jsonSchemaDemoOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Yerba Mate Store API")
                        .description("Simple API for Yerba Mate fans")
                        .version("v1.0.0"));
    }
}
