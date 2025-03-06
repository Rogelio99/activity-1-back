package com.activity1.app.services;

import com.activity1.app.domain.entity.Producto;
import com.activity1.app.domain.repository.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductosService {

    private final ProductosRepository productosRepository;

    @Autowired
    public ProductosService(ProductosRepository productosRepository) {
        this.productosRepository = productosRepository;
    }

    public ResponseEntity<Producto> createProducto(Producto producto) {
        try {
            validateProducto(producto);
            Producto productoCreated = productosRepository.save(producto);
            return new ResponseEntity<>(productoCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Producto> updateProducto(Long id, Producto producto) {
        try {
            Producto productoExistente = productosRepository.findById(id).orElse(null);
            if (productoExistente == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            validateProducto(producto);
            producto.setId(id);
            Producto productoUpdated = productosRepository.save(producto);
            return new ResponseEntity<>(productoUpdated, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    private void validateProducto(Producto producto) throws Exception {
        if (producto.getNombre() == null || producto.getNombre().isEmpty())
            throw new Exception("El nombre del producto es obligatorio");
        if (producto.getPrecio() == 0 || producto.getPrecio() < 0)
            throw new Exception("El precio del producto es obligatorio");
        if (producto.getDescripcion() == null || producto.getDescripcion().isEmpty())
            throw new Exception("La descripcion del producto es obligatoria");
        if (producto.getStock() == 0 || producto.getStock() < 0)
            throw new Exception("El stock del producto es obligatorio");
    }


    public ResponseEntity<Producto> changeStatus(Long id, Boolean status) {
        try {
            Producto producto = productosRepository.findById(id).orElse(null);
            if (producto == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            producto.setActivo(status);
            productosRepository.save(producto);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<Producto>> getProductos() {
        try {
            List<Producto> productos = productosRepository.findAll();
            return new ResponseEntity<>(productos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Producto> getProductoById(Long id) {
        try {
            Optional<Producto> producto = productosRepository.findById(id);
            return producto.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
