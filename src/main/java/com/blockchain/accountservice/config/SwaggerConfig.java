package com.blockchain.accountservice.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("user-account-service")
                        .description("user can fund,transfer and withdraw from account service")
                        .version("1.0.0")
                        .license(new License().name("1.0.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("user can fund,transfer and withdraw from account service"));
    }

}