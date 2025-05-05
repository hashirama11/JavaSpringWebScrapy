package com.example.DemoServiceSpring.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DemoServiceSpring.Model.ScrapyWebFarmabien;
import com.example.DemoServiceSpring.Service.DjangoService;
import com.example.DemoServiceSpring.Service.FarmabienService;


@RestController
@RequestMapping("/api/farmabien")
public class FarmabienController {
    
    // Controlador para la logica de negocio de Farmabien

    // Inyeccion de dependencias de FarmabienController
    private final FarmabienService farmabienService;
    private final DjangoService djangoService;

    public FarmabienController(DjangoService djangoService, FarmabienService farmabienService) {
        this.farmabienService = farmabienService;
        this.djangoService = djangoService;
    }

    // Endpoint Obtener todos los productos de la base de datos
    @GetMapping("/product")
    public List<ScrapyWebFarmabien> getProduct(){
        return farmabienService.getProductos();
    }

    // Endpoint Obtener un producto por su nombre
    @GetMapping("/search")
    public List<ScrapyWebFarmabien> buscarProductos(@RequestParam String nombre) {
        return farmabienService.buscarPorNombre(nombre);
    }

    // Endpoint para enviar nombre de producto a la API Django DRF
    @GetMapping("/api_search")
    public String enviarProducto(@RequestParam String item){
        return djangoService.enviarNombreProducto(item);
    }

    // Endpoint para verificar existencia de producto en la base de datos y enviar a Django si no existe
    @GetMapping("/existencia")
    public List<ScrapyWebFarmabien> existenciaProducto(@RequestParam String nombre) {
        return farmabienService.existenciaPorNombre(nombre);
    }
}
