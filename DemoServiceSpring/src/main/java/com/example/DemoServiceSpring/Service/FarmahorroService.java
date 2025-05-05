package com.example.DemoServiceSpring.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.DemoServiceSpring.Model.ScrapyWebFarmahorro;
import com.example.DemoServiceSpring.Repository.FarmahorroRepository;


@Service
public class FarmahorroService {

    
    private final FarmahorroRepository farmahorroRepository;
    private final DjangoService djangoService;
    
    public FarmahorroService(DjangoService djangoService, FarmahorroRepository farmahorroRepository) {
        this.farmahorroRepository = farmahorroRepository;
        this.djangoService = djangoService;
    }
    
    public List<ScrapyWebFarmahorro> getProductos(){
        // Llamar FarmatodoRepository para buscar el producto por el nombre
        return farmahorroRepository.findAll();
    }

    public List<ScrapyWebFarmahorro> buscarPorNombre(String nombre) {
        // Llamar FarmatodoRepository para buscar el producto por semejanza de nombre en la base de datos
        return farmahorroRepository.findByNombreContaining(nombre);
    }

    public List<ScrapyWebFarmahorro> existenciaPorNombre(String nombre) {
        // Validar si el producto existe en la base de datos
        List<ScrapyWebFarmahorro> productos = farmahorroRepository.findByNombreContaining(nombre);
        if (productos.isEmpty()) {
            // Si no existe, enviar el nombre del producto a la API Django
            djangoService.enviarNombreProducto(nombre);
            // Consultar nuevamente la base de datos
            productos = farmahorroRepository.findByNombreContaining(nombre);
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
                djangoService.enviarNombreProducto(nombre);
                // Consultar nuevamente la base de datos
                productos = farmahorroRepository.findByNombreContaining(nombre);
            }
            // Si existe, devolver la lista de productos encontrados
            return productos;
        }
       
    }
}
