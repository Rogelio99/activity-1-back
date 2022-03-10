package com.activity1.controllers;

import java.util.List;
import java.util.Optional;

import com.activity1.models.Producto;
import com.activity1.services.ProductosService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/productos")
public class ProductosController {
    @Autowired
    private ProductosService productosService;

    @PostMapping("/")
    @Operation(summary = "Crea un producto")
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        try {
            if (producto.getNombre() == null || producto.getNombre().isEmpty())
                throw new Exception("El nombre del producto es obligatorio");
            if (producto.getPrecio() == 0 || producto.getPrecio() < 0)
                throw new Exception("El precio del producto es obligatorio");
            if (producto.getDescripcion() == null || producto.getDescripcion().isEmpty())
                throw new Exception("La descripcion del producto es obligatoria");
            if (producto.getStock() == 0 || producto.getStock() < 0)
                throw new Exception("El stock del producto es obligatorio");
            Producto productoCreated = productosService.createProducto(producto);
            return new ResponseEntity<>(productoCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca un producto por id")
    public ResponseEntity<Producto> getProducto(@PathVariable(value = "id") Long id) {
        try {
            Optional<Producto> producto = productosService.getProducto(id);
            if (producto.isPresent())
                return new ResponseEntity<>(producto.get(), HttpStatus.OK);
            else
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    @Operation(summary = "Busca todos los productos")
    public ResponseEntity<List<Producto>> getProductos() {
        try {
            List<Producto> productos = productosService.getProductos();
            return new ResponseEntity<>(productos, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un producto")
    public ResponseEntity<Producto> updateProducto(@PathVariable(value = "id") Long id, @RequestBody Producto producto) {
        try {
            if (producto.getNombre() == null || producto.getNombre().isEmpty())
                throw new Exception("El nombre del producto es obligatorio");
            if (producto.getPrecio() == 0 || producto.getPrecio() < 0)
                throw new Exception("El precio del producto es obligatorio");
            if (producto.getDescripcion() == null || producto.getDescripcion().isEmpty())
                throw new Exception("La descripcion del producto es obligatoria");
            if (producto.getStock() == 0 || producto.getStock() < 0)
                throw new Exception("El stock del producto es obligatorio");
            Producto productoUpdated = productosService.updateProducto(id, producto);
            return new ResponseEntity<>(productoUpdated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Actualiza el status de un producto")
    public ResponseEntity<Producto> changeStatus(@PathVariable(value = "id") Long id) {
        try {
            productosService.changeStatus(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un producto")
    public ResponseEntity<Void> deleteProducto(@PathVariable(value = "id") Long id) {
        try {
            productosService.deleteProducto(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
}
