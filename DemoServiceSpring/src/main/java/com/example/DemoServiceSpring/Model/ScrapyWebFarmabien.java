package com.example.DemoServiceSpring.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "farmabien_scrapywebfarmabien")
public class ScrapyWebFarmabien {
    private Long id;
    private String nombre;
    private Double precio;
    private String url;
    private String fecha;

    public ScrapyWebFarmabien(Long id, String nombre, Double precio, String url, String fecha) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.url = url;
        this.fecha = fecha;
    }

    protected ScrapyWebFarmabien() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return nombre;
    }

    public Double getPrice() {
        return precio;
    }

    public String getUrls() {
        return url;
    }

    public String getFecha() {
        return fecha;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.nombre = name;
    }

    public void setPrice(Double price) {
        this.precio = price;
    }

    public void setUrls(String urls) {
        this.url = urls;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    @Override
    public String toString() {
        return "ScrapyWebFarmabien{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", url='" + url + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
