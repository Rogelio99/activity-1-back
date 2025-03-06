package com.activity1.app.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Schema(description = "Productos de la tienda")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto", unique = true)
    private Long id;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "categoria_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Categoria categoria;

}
