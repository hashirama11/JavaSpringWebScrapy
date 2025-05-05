package com.example.DemoServiceSpring.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DemoServiceSpring.Model.ScrapyWebLocatel;
import com.example.DemoServiceSpring.Service.DjangoService.DjangoDRFService;
import com.example.DemoServiceSpring.Service.Enum.FarmaEnum;
import com.example.DemoServiceSpring.Service.FarmaService.LocatelService;

@RestController
@RequestMapping("/api/locatel")
public class LocatelController {

    // Controlador para la logica de negocio de Locatel
    // Inyeccion de dependencias de LocatelService
    
    private final LocatelService locatelService;
    private final DjangoDRFService djangoService;
    
    public LocatelController(DjangoDRFService djangoService, LocatelService locatelService) {
        this.locatelService = locatelService;
        this.djangoService = djangoService;
    }
    
    // Endpoint para obtener todos los productos de Locatel
    @GetMapping("/product")
    public List<ScrapyWebLocatel> getProductos() {
        return locatelService.getProductos();
    }
    
    // Endpoint para buscar productos por nombre en Locatel
    @GetMapping("/search")
    public List<ScrapyWebLocatel> buscarPorNombre(@PathVariable String nombre) {
        return locatelService.buscarPorNombre(nombre);
    }

    // Endpoint para enviar nombre de producto a la API Django DRF
    @GetMapping("/api_search")
    public String enviarProducto(@PathVariable String item) {
        return djangoService.enviarNombreProducto(item, FarmaEnum.LOCATEL);
    }
    
    // Endpoint para verificar la existencia de un producto por nombre en Locatel
    @GetMapping("/existencia")
    public List<ScrapyWebLocatel> existenciaPorNombre(@PathVariable String nombre) {
        return locatelService.existenciaPorNombre(nombre);
    }
}
