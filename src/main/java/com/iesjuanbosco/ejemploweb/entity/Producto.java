package com.iesjuanbosco.ejemploweb.entity;

import jakarta.persistence.*;

@Entity //Indica que esta clase es una entidad
@Table(name="productos") //Indica que la tabla de la BBDD relacionada con esta entidad se llama productos
public class Producto {

    @Id //Anotación que indica que campo va a ser la clave primaria de la tabla en la BBDD
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Anotación que indicia que la clave primaria sera Auto-Increment
    private Long id;
    private String titulo;
    private Integer cantidad;
    private Double precio;

    public Producto(Long id, String titulo, Integer cantidad, Double precio) {
        this.id = id;
        this.titulo = titulo;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Producto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                '}';
    }
}
