package com.example.DemoServiceSpring.Service.FarmaService;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.DemoServiceSpring.Model.ScrapyWebLocatel;
import com.example.DemoServiceSpring.Repository.LocatelRepository;
import com.example.DemoServiceSpring.Service.DjangoService.DjangoDRFService;
import com.example.DemoServiceSpring.Service.Enum.FarmaEnum;

@Service
public class LocatelService {
    
    
    private final LocatelRepository locatelRepository;
    private final DjangoDRFService djangoService;
    
    public LocatelService(DjangoDRFService djangoService, LocatelRepository locatelRepository) {
        this.locatelRepository = locatelRepository;
        this.djangoService = djangoService;
    }
    
    public List<ScrapyWebLocatel> getProductos(){
        // Llamar LocatelRepository para buscar el producto por el nombre
        return locatelRepository.findAll();
    }

    public List<ScrapyWebLocatel> buscarPorNombre(String nombre) {
        // Llamar LocatelRepository para buscar el producto por semejanza de nombre en la base de datos
        return locatelRepository.findByNombreContaining(nombre);
    }

    public List<ScrapyWebLocatel> existenciaPorNombre(String nombre) {
        // Validar si el producto existe en la base de datos
        List<ScrapyWebLocatel> productos = locatelRepository.findByNombreContaining(nombre);
        if (productos.isEmpty()) {
            // Si no existe, enviar el nombre del producto a la API Django
            djangoService.enviarNombreProducto(nombre, FarmaEnum.LOCATEL);
            // Consultar nuevamente la base de datos
            productos = locatelRepository.findByNombreContaining(nombre);
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
                djangoService.enviarNombreProducto(nombre, FarmaEnum.LOCATEL);
                // Consultar nuevamente la base de datos
                productos = locatelRepository.findByNombreContaining(nombre);
            }
            // Si existe, devolver la lista de productos encontrados
            return productos;
        }
       
    }

}
