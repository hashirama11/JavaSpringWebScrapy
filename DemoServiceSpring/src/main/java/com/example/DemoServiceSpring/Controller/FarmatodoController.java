package com.example.DemoServiceSpring.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DemoServiceSpring.Model.ScrapyWebFarmatodo;
import com.example.DemoServiceSpring.Service.DjangoService;
import com.example.DemoServiceSpring.Service.FarmatodoService;



@RestController
@RequestMapping("/api/farmatodo")
public class FarmatodoController {

    // Controlador para consultar la existencia del producto en la base de datos

    @Autowired
    private FarmatodoService farmatodoService;

    private final DjangoService djangoService;

    public FarmatodoController(DjangoService djangoService) {
        this.djangoService = djangoService;
    }

    // Endpoint Obtener todos los productos de la base de datos
    @GetMapping("/product")
    public List<ScrapyWebFarmatodo> getProduct(){
        return farmatodoService.getProductos();
    }

    // Endpoint Obtener un producto por su nombre
    @GetMapping("/search")
    public List<ScrapyWebFarmatodo> buscarProductos(@RequestParam String nombre) {
        return farmatodoService.buscarPorNombre(nombre);
    }

    // Endpoint para enviar nombre de producto a la API Django DRF
    @GetMapping("/api_search")
    public String enviarProducto(@RequestParam String item){
        return djangoService.enviarNombreProducto(item);
    }

    // Endpoint para verificar existencia de producto en la base de datos y enviar a Django si no existe
    @GetMapping("/existencia")
    public List<ScrapyWebFarmatodo> existenciaProducto(@RequestParam String nombre) {
        return farmatodoService.existenciaPorNombre(nombre);
    }

}