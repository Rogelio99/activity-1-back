package com.activity1.services;

import com.activity1.repository.CategoriasRepository;

import java.util.List;
import java.util.Optional;

import com.activity1.models.Categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriasService {
    @Autowired
    private CategoriasRepository categoriasRepository;

    public List<Categoria> getCategorias() {
        return categoriasRepository.findAll();
    }

    public Optional<Categoria> getCategoria(Long id) {
        return categoriasRepository.findById(id);
    }

    public Categoria createCategoria(Categoria categoria) {
        return categoriasRepository.save(categoria);
    }

    public Categoria updateCategoria(Long id, Categoria categoria) {
        Categoria categoriaToUpdate = categoriasRepository.findById(id).get();
        categoriaToUpdate.setNombre(categoria.getNombre());
        categoriaToUpdate.setActivo(categoria.getActivo());
        return categoriasRepository.save(categoria);
    }

    public void changeStatus(Long id) {
        Categoria categoria = categoriasRepository.findById(id).get();
        categoria.setActivo(!categoria.getActivo());
        categoriasRepository.save(categoria);
    }

    public void deleteCategoria(Long id) {
        categoriasRepository.deleteById(id);
    }
}
