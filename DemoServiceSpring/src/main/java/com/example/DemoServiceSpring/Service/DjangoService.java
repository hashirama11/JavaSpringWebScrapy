package com.example.DemoServiceSpring.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DjangoService {

    // Definiendo la logica para la comunicacion y envio de nombre de producto a Django DRF

    private final RestTemplate restTemplate;

    public DjangoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String enviarNombreProducto(String item) {
        // URL de la API Django DRF
        String url = "http://127.0.0.1:8000/api/farmatodo/search/" + item; // URL corregida
        return restTemplate.getForObject(url, String.class);
    }
    
}
