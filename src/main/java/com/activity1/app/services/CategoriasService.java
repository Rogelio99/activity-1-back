package com.activity1.app.services;

import com.activity1.app.domain.entity.Categoria;
import com.activity1.app.domain.repository.CategoriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriasService {
    private final CategoriasRepository categoriasRepository;

    @Autowired
    public CategoriasService(CategoriasRepository categoriasRepository) {
        this.categoriasRepository = categoriasRepository;
    }

    public Categoria saveCategoria(Categoria categoria) {
        try{
            Categoria categoriaExistente = categoriasRepository.getByNombre(categoria.getNombre());
            if(categoriaExistente != null){
                return categoriaExistente;
            }
            return categoriasRepository.save(categoria);
        } catch (Exception e){
            return null;
        }
    }

    public Categoria getCategoriaById(Long id) {
        return categoriasRepository.getById(id);
    }

    public Categoria getCategoriaByNombre(String nombre) {
        return categoriasRepository.getByNombre(nombre);
    }

    public List<Categoria> getAllActiveCategorias() {
        return categoriasRepository.getByActivo(Boolean.TRUE);
    }
}
