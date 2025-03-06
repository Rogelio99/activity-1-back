package com.activity1.app.domain.repository;

import com.activity1.app.domain.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriasRepository extends JpaRepository<Categoria, Long> {

    Categoria getByNombre(String nombre);

    List<Categoria> getByActivo(Boolean activo);
}
