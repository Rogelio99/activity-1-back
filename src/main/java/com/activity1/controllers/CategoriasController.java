package com.activity1.controllers;

import java.util.List;
import java.util.Optional;

import com.activity1.models.Categoria;
import com.activity1.services.CategoriasService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/categorias")
public class CategoriasController {
    private CategoriasService categoriasService;

    @GetMapping("/")
    public ResponseEntity<List<Categoria>> getCategorias() {
        try {
            List<Categoria> categorias = categoriasService.getCategorias();
            return new ResponseEntity<>(categorias, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoria(@PathVariable(value = "id") Long id) {
        try {
            Optional<Categoria> categoria = categoriasService.getCategoria(id);
            if (categoria.isPresent())
                return new ResponseEntity<>(categoria.get(), HttpStatus.OK);
            else
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Categoria> createCategoria(@RequestBody Categoria categoria) {
        try {
            
            if (categoria.getNombre() == null || categoria.getNombre().isEmpty())
                throw new Exception("El nombre de la categoria es obligatorio");
            if (categoria.getActivo() == null)
                throw new Exception("El campo activo es obligatorio");
            Categoria categoriaCreated = categoriasService.createCategoria(categoria);
            return new ResponseEntity<>(categoriaCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable(value = "id") Long id, @RequestBody Categoria categoria) {
        try {
            if (categoria.getNombre() == null || categoria.getNombre().isEmpty())
                throw new Exception("El nombre de la categoria es obligatorio");
            if (categoria.getActivo() == null)
                throw new Exception("El campo activo es obligatorio");
            Categoria categoriaUpdated = categoriasService.updateCategoria(id, categoria);
            return new ResponseEntity<>(categoriaUpdated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping(value="/{id}")
    public ResponseEntity<Categoria> changeStatus(@PathVariable(value = "id") Long id) {
        try {
            categoriasService.changeStatus(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Categoria> deleteCategoria(@PathVariable(value = "id") Long id) {
        try {
            categoriasService.deleteCategoria(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
