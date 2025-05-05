package com.example.DemoServiceSpring.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.DemoServiceSpring.Model.ScrapyWebFarmaciasas;
import com.example.DemoServiceSpring.Service.FarmaciasasService;

public class FarmaciasasController {
    // Controlador para consultar la existencia del producto en la base de datos

    // Inyectar el servicio de FarmaciasasService
    private final FarmaciasasService farmaciasasService;
    
    public FarmaciasasController(FarmaciasasService farmaciasasService) {
        this.farmaciasasService = farmaciasasService;
    }
    
    // Endpoint para obtener todos los productos de Farmaciasas
    @GetMapping("/farmaciasas")
    public List<ScrapyWebFarmaciasas> getProductos() {
        return farmaciasasService.getProductos();
    }
    
    // Endpoint para buscar productos por nombre en Farmaciasas
    @GetMapping("/farmaciasas/buscar/{nombre}")
    public List<ScrapyWebFarmaciasas> buscarPorNombre(@PathVariable String nombre) {
        return farmaciasasService.buscarPorNombre(nombre);
    }
    
    // Endpoint para verificar la existencia de un producto por nombre en Farmaciasas
    @GetMapping("/farmaciasas/existencia/{nombre}")
    public List<ScrapyWebFarmaciasas> existenciaPorNombre(@PathVariable String nombre) {
        return farmaciasasService.existenciaPorNombre(nombre);
    }
}
