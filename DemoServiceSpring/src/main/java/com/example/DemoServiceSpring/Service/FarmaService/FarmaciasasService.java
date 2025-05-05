package com.example.DemoServiceSpring.Service.FarmaService;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.DemoServiceSpring.Model.ScrapyWebFarmaciasas;
import com.example.DemoServiceSpring.Repository.FarmaciasasRepository;
import com.example.DemoServiceSpring.Service.DjangoService.DjangoDRFService;
import com.example.DemoServiceSpring.Service.Enum.FarmaEnum;

@Service
public class FarmaciasasService {
    
    private final FarmaciasasRepository farmaciasasRepository;

    private final DjangoDRFService djangoService;
    
    public FarmaciasasService(DjangoDRFService djangoService, FarmaciasasRepository farmaciasasRepository) {
        this.farmaciasasRepository = farmaciasasRepository;
        this.djangoService = djangoService;
    }
    
    public List<ScrapyWebFarmaciasas> getProductos(){
        // Llamar FarmatodoRepository para buscar el producto por el nombre
        return farmaciasasRepository.findAll();
    }

    public List<ScrapyWebFarmaciasas> buscarPorNombre(String nombre) {
        // Llamar FarmatodoRepository para buscar el producto por semejanza de nombre en la base de datos
        return farmaciasasRepository.findByNombreContaining(nombre);
    }

    public List<ScrapyWebFarmaciasas> existenciaPorNombre(String nombre) {
        // Validar si el producto existe en la base de datos
        List<ScrapyWebFarmaciasas> productos = farmaciasasRepository.findByNombreContaining(nombre);
        if (productos.isEmpty()) {
            // Si no existe, enviar el nombre del producto a la API Django
            djangoService.enviarNombreProducto(nombre, FarmaEnum.FARMACIASAS);
            // Consultar nuevamente la base de datos
            productos = farmaciasasRepository.findByNombreContaining(nombre);
            // Verificar si el producto fue agregado a la base de datos
            return productos;
        }else {
            // Validar que la fecha de los registros sean distintos a la fecha actual
            // Si la fecha de los registros son distintos a la fecha actual, se debe actualizar el registro
            // Si la fecha de los registros son iguales a la fecha actual, se debe devolver el registro
            // Obtener la fecha actual
            LocalDate fechaActual = LocalDate.now();
            // Obtener la fecha de los registros
            var fechaRegistro = LocalDate.parse(productos.get(0).getFecha());
            // Validar si la fecha de los registros son distintos a la fecha actual
            if (!fechaRegistro.equals(fechaActual)) {
                // Si son distintos, se debe actualizar el registro
                // Llamar a la API Django para actualizar el registro
                djangoService.enviarNombreProducto(nombre, FarmaEnum.FARMACIASAS);
                // Consultar nuevamente la base de datos
                productos = farmaciasasRepository.findByNombreContaining(nombre);
            }
            // Si existe, devolver la lista de productos encontrados
            return productos;
        }
       
    }
}
