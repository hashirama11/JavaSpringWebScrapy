package com.example.DemoServiceSpring.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DemoServiceSpring.Model.ScrapyWebFarmatodo;
import com.example.DemoServiceSpring.Service.DjangoService.DjangoDRFService;
import com.example.DemoServiceSpring.Service.Enum.FarmaEnum;
import com.example.DemoServiceSpring.Service.FarmaService.FarmatodoService;



@RestController
@RequestMapping("/api/farmatodo")
public class FarmatodoController {

    // Controlador para la logica de negocio de Farmatodo

    // Inyeccion de dependencias de FarmatodoService
    private final FarmatodoService farmatodoService;
    private final DjangoDRFService djangoService;

    public FarmatodoController(DjangoDRFService djangoService, FarmatodoService farmatodoService) {
        this.farmatodoService = farmatodoService;
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
        return djangoService.enviarNombreProducto(item, FarmaEnum.FARMATODO);
    }

    // Endpoint para verificar existencia de producto en la base de datos y enviar a Django si no existe
    @GetMapping("/existencia")
    public List<ScrapyWebFarmatodo> existenciaProducto(@RequestParam String nombre) {
        return farmatodoService.existenciaPorNombre(nombre);
    }

}