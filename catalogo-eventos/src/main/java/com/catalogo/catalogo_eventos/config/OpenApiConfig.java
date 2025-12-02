package com.catalogo.catalogo_eventos.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
            .info(new Info()
                .title("Catalogo de Eventos y Venues API")
                .version("1.0.0")
                .description("API REST In-Memory para gestión de eventos y venues"));
    }
}
