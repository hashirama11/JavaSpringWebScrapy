package com.example.DemoServiceSpring.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.DemoServiceSpring.Model.ScrapyWebFarmahorro;
import com.example.DemoServiceSpring.Service.DjangoService;
import com.example.DemoServiceSpring.Service.FarmahorroService;

public class FarmahorroController {
    // Controlador para consultar la existencia del producto en la base de datos

    @Autowired
    private FarmahorroService farmahorroService;

    private final DjangoService djangoService;

    public FarmahorroController(DjangoService djangoService) {
        this.djangoService = djangoService;
    }

    // Endpoint Obtener todos los productos de la base de datos
    @GetMapping("/product")
    public List<ScrapyWebFarmahorro> getProduct(){
        return farmahorroService.getProductos();
    }

    // Endpoint Obtener un producto por su nombre
    @GetMapping("/search")
    public List<ScrapyWebFarmahorro> buscarProductos(@RequestParam String nombre) {
        return farmahorroService.buscarPorNombre(nombre);
    }

    // Endpoint para enviar nombre de producto a la API Django DRF
    @GetMapping("/api_search")
    public String enviarProducto(@RequestParam String item){
        return djangoService.enviarNombreProducto(item);
    }

    // Endpoint para verificar existencia de producto en la base de datos y enviar a Django si no existe
    @GetMapping("/existencia")
    public List<ScrapyWebFarmahorro> existenciaProducto(@RequestParam String nombre) {
        return farmahorroService.existenciaPorNombre(nombre);
    }
}
