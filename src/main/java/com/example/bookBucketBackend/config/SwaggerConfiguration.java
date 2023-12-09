package com.example.bookBucketBackend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI myOpenAPI() {
        Server localServer = new Server();
        localServer.setUrl("http://localhost:8080");
        localServer.setDescription("Server URL in Local environment");

        Contact contact = new Contact();
        contact.setName("Yash");

        Info info = new Info()
                .title("Book-Bucket APIs")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to book-bucket project.");

        return new OpenAPI().info(info).servers(List.of(localServer));
    }
}
