package com.example.DemoServiceSpring.Service.DjangoService;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.DemoServiceSpring.Service.Enum.FarmaEnum;

@Service
public class DjangoDRFService {

    // Definiendo la logica para la comunicacion y envio de nombre de producto a Django DRF

    private final RestTemplate restTemplate;
    

    public DjangoDRFService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String enviarNombreProducto(String item, FarmaEnum farmEnum) {
        // URL de la API Django DRF
        String url = "http://127.0.0.1:8000/" + farmEnum.getValue() + "/" + "api/" + farmEnum.getValue() + "/search/" + item + "/"; // URL corregida
        return restTemplate.getForObject(url, String.class);
    }
    
}
