package com.example.DemoServiceSpring.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    // Definiendo un RestTemplate bean para la inyección de dependencias
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
}
