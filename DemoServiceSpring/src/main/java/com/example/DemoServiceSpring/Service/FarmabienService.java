package com.example.DemoServiceSpring.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.DemoServiceSpring.Model.ScrapyWebFarmabien;
import com.example.DemoServiceSpring.Repository.FarmabienRepository;

public class FarmabienService {
    @Autowired
    private FarmabienRepository farmabienRepository;

    final private DjangoService djangoService;
    
    public FarmabienService(DjangoService djangoService) {
        this.djangoService = djangoService;
    }
    
    public List<ScrapyWebFarmabien> getProductos(){
        // Llamar FarmatodoRepository para buscar el producto por el nombre
        return farmabienRepository.findAll();
    }

    public List<ScrapyWebFarmabien> buscarPorNombre(String nombre) {
        // Llamar FarmatodoRepository para buscar el producto por semejanza de nombre en la base de datos
        return farmabienRepository.findByNombreContaining(nombre);
    }

    public List<ScrapyWebFarmabien> existenciaPorNombre(String nombre) {
        // Validar si el producto existe en la base de datos
        List<ScrapyWebFarmabien> productos = farmabienRepository.findByNombreContaining(nombre);
        if (productos.isEmpty()) {
            // Si no existe, enviar el nombre del producto a la API Django
            djangoService.enviarNombreProducto(nombre);
            // Consultar nuevamente la base de datos
            productos = farmabienRepository.findByNombreContaining(nombre);
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
                productos = farmabienRepository.findByNombreContaining(nombre);
            }
            // Si existe, devolver la lista de productos encontrados
            return productos;
        }
       
    }
}
