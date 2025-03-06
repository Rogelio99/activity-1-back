package com.activity1.app.web.controllers;

import com.activity1.app.domain.entity.Categoria;
import com.activity1.app.services.CategoriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriasController{

    private final CategoriasService categoriasService;

    @Autowired
    public CategoriasController(CategoriasService categoriasService){
        this.categoriasService = categoriasService;
    }

    @PostMapping()
    public Categoria saveCategoria(@RequestBody Categoria categoria){
        return categoriasService.saveCategoria(categoria);
    }

    @GetMapping()
    public List<Categoria> getAllActiveCategorias(){
        return categoriasService.getAllActiveCategorias();
    }

    @GetMapping("/{id}")
    public Categoria getCategoriaById(@PathVariable Long id){
        return categoriasService.getCategoriaById(id);
    }

    @GetMapping("/")
    public Categoria getCategoriaByNombre(@PathVariable String nombre){
        return categoriasService.getCategoriaByNombre(nombre);
    }
}
