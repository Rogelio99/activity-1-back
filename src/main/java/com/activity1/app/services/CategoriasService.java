package com.activity1.app.services;

import com.activity1.app.domain.entity.Categoria;
import com.activity1.app.domain.repository.CategoriasRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class CategoriasService {
    private final CategoriasRepository categoriasRepository;

    @Autowired
    public CategoriasService(CategoriasRepository categoriasRepository) {
        this.categoriasRepository = categoriasRepository;
    }

    public ResponseEntity<Categoria> saveCategoria(Categoria categoria) {
        try {
            validateCategoria(categoria);
            Categoria categoriaCreated = categoriasRepository.save(categoria);
            return new ResponseEntity<>(categoriaCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Categoria> updateCategoria(Long id, Categoria categoria) {
        try {
            Categoria categoriaExistente = categoriasRepository.findById(id).orElse(null);
            if (categoriaExistente == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            validateCategoria(categoria);
            categoria.setId(id);
            Categoria categoriaUpdated = categoriasRepository.save(categoria);
            return new ResponseEntity<>(categoriaUpdated, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Categoria> changeStatus(Long id, Boolean status) {
        try {
            Categoria categoria = categoriasRepository.findById(id).orElse(null);
            if (categoria == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            categoria.setActivo(status);
            Categoria categoriaUpdated = categoriasRepository.save(categoria);
            return new ResponseEntity<>(categoriaUpdated, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<Categoria> getCategoriaById(Long id) {
        try {
            Categoria categoria = categoriasRepository.getById(id);
            if (categoria != null)
                return new ResponseEntity<>(categoria, HttpStatus.OK);
            else
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Categoria>> getAllActiveCategorias() {
        try {
            return new ResponseEntity<>(categoriasRepository.getByActivo(Boolean.TRUE), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void validateCategoria(Categoria categoria) throws Exception {
        if (categoria.getNombre() == null || categoria.getNombre().isEmpty())
            throw new Exception("El nombre de la categoria es obligatorio");
        if (categoria.getActivo() == null)
            throw new Exception("El campo activo es obligatorio");
    }
}
