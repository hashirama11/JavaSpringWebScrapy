package com.example.DemoServiceSpring.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.DemoServiceSpring.Model.ScrapyWebLocatel;
import com.example.DemoServiceSpring.Service.LocatelService;

public class LocatelController {
    // Inyectar el servicio de LocatelService
    private final LocatelService locatelService;
    
    public LocatelController(LocatelService locatelService) {
        this.locatelService = locatelService;
    }
    
    // Endpoint para obtener todos los productos de Locatel
    @GetMapping("/locatel")
    public List<ScrapyWebLocatel> getProductos() {
        return locatelService.getProductos();
    }
    
    // Endpoint para buscar productos por nombre en Locatel
    @GetMapping("/locatel/buscar/{nombre}")
    public List<ScrapyWebLocatel> buscarPorNombre(@PathVariable String nombre) {
        return locatelService.buscarPorNombre(nombre);
    }
    
    // Endpoint para verificar la existencia de un producto por nombre en Locatel
    @GetMapping("/locatel/existencia/{nombre}")
    public List<ScrapyWebLocatel> existenciaPorNombre(@PathVariable String nombre) {
        return locatelService.existenciaPorNombre(nombre);
    }
}
