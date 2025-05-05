package com.example.DemoServiceSpring.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DemoServiceSpring.Model.ScrapyWebFarmahorro;
import com.example.DemoServiceSpring.Service.DjangoService;
import com.example.DemoServiceSpring.Service.FarmahorroService;


@RestController
@RequestMapping("/api/farmahorro")
public class FarmahorroController {
    // Controlador para la logica de negocio de Farmahorro

    // Inyeccion de dependencias de FarmahorroService
    private final FarmahorroService farmahorroService;
    private final DjangoService djangoService;

    public FarmahorroController(DjangoService djangoService, FarmahorroService farmahorroService) {
        this.farmahorroService = farmahorroService;
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
