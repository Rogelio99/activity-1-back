package com.activity1.repository;

import com.activity1.models.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriasRepository extends JpaRepository<Categoria, Long> {

}
