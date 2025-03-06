package com.activity1.app.domain.repository;

import com.activity1.app.domain.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductosRepository extends JpaRepository<Producto, Long> {
}
