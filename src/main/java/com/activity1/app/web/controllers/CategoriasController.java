package com.activity1.app.web.controllers;

import com.activity1.app.domain.entity.Categoria;
import com.activity1.app.services.CategoriasService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriasController{

    private final CategoriasService categoriasService;

    @Autowired
    public CategoriasController(CategoriasService categoriasService){
        this.categoriasService = categoriasService;
    }

    @GetMapping("/")
    @Operation(summary = "Lista todas las categorias activas")
    public ResponseEntity<List<Categoria>> getCategorias() {
        return categoriasService.getAllActiveCategorias();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca una categoria por id")
    public ResponseEntity<Categoria> getCategoria(@PathVariable(value = "id") Long id) {
        return categoriasService.getCategoriaById(id);
    }

    @PostMapping("/")
    @Operation(summary = "Crea una categoria")
    public ResponseEntity<Categoria> createCategoria(@RequestBody Categoria categoria) {
        return categoriasService.saveCategoria(categoria);
    }

    @PutMapping(value="/{id}")
    @Operation(summary = "Actualiza una categoria")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable(value = "id") Long id, @RequestBody Categoria categoria) {
        return categoriasService.updateCategoria(id, categoria);
    }

    @PatchMapping(value="/{id}")
    @Operation(summary = "Actualiza el estado de una categoria")
    public ResponseEntity<Categoria> changeStatus(@PathVariable(value = "id") Long id, @RequestParam(value = "status") Boolean status) {
        return categoriasService.changeStatus(id, status);
    }
}
