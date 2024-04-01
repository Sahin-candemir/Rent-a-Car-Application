package com.shn.user.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("User Management Service")
                        .description("User Management API")
                        .version("1.0")
                        .contact(new Contact().name("Şahin Candemir")
                                .email( "https://github.com/Sahin-candemir")
                                .url("shncandemir@gmail.com"))
                        .license(new License().name("License of API")
                                .url("API license URL")))
                .externalDocs(new ExternalDocumentation()
                        .description("User Management Documentation"));
    }
}
