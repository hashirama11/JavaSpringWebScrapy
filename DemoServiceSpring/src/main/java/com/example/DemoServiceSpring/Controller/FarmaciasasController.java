package com.example.DemoServiceSpring.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DemoServiceSpring.Model.ScrapyWebFarmaciasas;
import com.example.DemoServiceSpring.Service.DjangoService;
import com.example.DemoServiceSpring.Service.FarmaciasasService;


@RestController
@RequestMapping("/api/farmaciasas")
public class FarmaciasasController {
    // Controlador para la logica de negocio de Farmaciasas

    // Inyeccion de dependencias de FarmaciasasController
    private final FarmaciasasService farmaciasasService;
    private final DjangoService djangoService;
    
    public FarmaciasasController(FarmaciasasService farmaciasasService, DjangoService djangoService) {
        this.djangoService = djangoService;
        this.farmaciasasService = farmaciasasService;
    }
    
    // Endpoint para obtener todos los productos de Farmaciasas
    @GetMapping("/product")
    public List<ScrapyWebFarmaciasas> getProductos() {
        return farmaciasasService.getProductos();
    }
    
    // Endpoint para buscar productos por nombre en Farmaciasas
    @GetMapping("/search")
    public List<ScrapyWebFarmaciasas> buscarPorNombre(@PathVariable String nombre) {
        return farmaciasasService.buscarPorNombre(nombre);
    }

    // Endpoint para enviar nombre de producto a la API Django DRF
    @GetMapping("/api_search")
    public String enviarProducto(@PathVariable String item) {
        return djangoService.enviarNombreProducto(item);
    }
    
    // Endpoint para verificar la existencia de un producto por nombre en Farmaciasas
    @GetMapping("existencia")
    public List<ScrapyWebFarmaciasas> existenciaPorNombre(@PathVariable String nombre) {
        return farmaciasasService.existenciaPorNombre(nombre);
    }
}
