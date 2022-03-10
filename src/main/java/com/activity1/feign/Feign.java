package com.activity1.feign;

import java.util.List;

import com.activity1.models.Producto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.v3.oas.annotations.Operation;

@FeignClient(value="feignVentas",url="http://localhost:8080")
public interface Feign {

    @GetMapping("/productos/")
    @Operation(summary = "Obtener todos los productos")
	List<Producto> getProductos() ;
}