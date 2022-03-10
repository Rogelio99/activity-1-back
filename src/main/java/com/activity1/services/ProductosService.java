package com.activity1.services;

import java.util.List;
import java.util.Optional;

import com.activity1.models.Producto;
import com.activity1.repository.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductosService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getProductos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> getProducto(Long id) {
        return productoRepository.findById(id);
    }

    public Producto createProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto updateProducto(Long id,Producto producto) {
        Producto productoToUpdate = productoRepository.findById(id).get();
        productoToUpdate.setNombre(producto.getNombre());
        productoToUpdate.setActivo(producto.getActivo());
        productoToUpdate.setCategoria(producto.getCategoria());
        productoToUpdate.setDescripcion(producto.getDescripcion());
        productoToUpdate.setPrecio(producto.getPrecio());
        productoToUpdate.setStock(producto.getStock());
        return productoRepository.save(producto);
    }

    public void changeStatus(Long id) {
        Producto producto = productoRepository.findById(id).get();
        producto.setActivo(!producto.getActivo());
        productoRepository.save(producto);
    }

    public void deleteProducto(Long id) {
        productoRepository.deleteById(id);
    }
}
