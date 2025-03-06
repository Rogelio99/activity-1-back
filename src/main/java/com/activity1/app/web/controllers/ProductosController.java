package com.activity1.app.web.controllers;



import com.activity1.app.domain.entity.Producto;
import com.activity1.app.services.ProductosService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductosController {

    private final ProductosService productosService;

    @Autowired
    public ProductosController(ProductosService productosService) {
        this.productosService = productosService;
    }

    @PostMapping("/")
    @Operation(summary = "Crea un producto")
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        return productosService.createProducto(producto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca un producto por id")
    public ResponseEntity<Producto> getProducto(@PathVariable(value = "id") Long id) {
        return productosService.getProductoById(id);
    }

    @GetMapping("/")
    @Operation(summary = "Busca todos los productos")
    public ResponseEntity<List<Producto>> getProductos() {
        return productosService.getProductos();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un producto")
    public ResponseEntity<Producto> updateProducto(@PathVariable(value = "id") Long id, @RequestBody Producto producto) {
        return productosService.updateProducto(id, producto);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Desactiva o activa un producto")
    public ResponseEntity<Producto> changeStatus(@PathVariable(value = "id") Long id, @RequestParam(value = "status") Boolean status) {
        return productosService.changeStatus(id, status);
    }

    
    
}
