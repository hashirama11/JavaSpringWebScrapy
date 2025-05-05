package com.example.DemoServiceSpring.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.DemoServiceSpring.Model.ScrapyWebLocatel;

@Repository
public interface LocatelRepository extends JpaRepository<ScrapyWebLocatel, Long> {
    
    // Ya con la interfaz puedo obtener consultas comunes

    // Para filtrar por nombre segun semejanza 


    // Método utilizando directamente la convención de nombres en Spring Data JPA
    List<ScrapyWebLocatel> findByNombreContaining(String nombre);

    // Método utilizando @Query para más control
    @Query("SELECT s FROM ScrapyWebFarmatodo s WHERE LOWER(s.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<ScrapyWebLocatel> buscarPorNombreSimilar(@Param("nombre") String nombre);
}
