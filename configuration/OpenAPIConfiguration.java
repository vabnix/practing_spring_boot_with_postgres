package com.vaibhav.spring.configuration;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("User API - Connecting to PostgreSQL")
                        .version("1.0")
                        .description("User API v1.0")
                        .termsOfService("http://swagger.io/terms/")
                        .contact(new Contact()
                                .name("Vaibhav Srivastava")
                                .url("http://ish-aum.com")
                                .email("info@ish-aum.com")));
    }
}
