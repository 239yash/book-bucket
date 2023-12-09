package com.example.bookBucketBackend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI myOpenAPI() {

        Contact contact = new Contact();
        contact.setName("Yash");

        Info info = new Info()
                .title("Book-Bucket APIs")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to book-bucket project.");

        return new OpenAPI().info(info);
    }
}
