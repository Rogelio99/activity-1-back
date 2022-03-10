package com.activity1.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import io.swagger.v3.oas.annotations.media.Schema;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Schema(description = "Productos de la tienda")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    @Schema(description = "Nombre del producto")
    private String nombre;
    @Column(nullable = false)
    @Schema(description = "Descripción del producto")
    private String descripcion;
    @Column(nullable = false)
    @Schema(description = "Precio del producto")
    private int precio;
    @Column(nullable = false)
    @Schema(description = "Cantidad del producto")
    private int stock;
    @Column(nullable = false)
    @Schema(description = "Activo del producto")
    private Boolean activo = true;
    //@ManyToOne(fetch = FetchType.LAZY, optional = false)
    //@JoinColumn(name = "categoria_id", nullable = false)
    @Schema(description = "Categoria a la que pertenece el producto")
    private int categoria_id;

    public Producto(String nombre, String descripcion, int precio, int stock, int categoria_id) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.categoria_id = categoria_id;
    }

    public Producto() {
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public int getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(int categoria_id) {
        this.categoria_id = categoria_id;
    }

    

    
}
