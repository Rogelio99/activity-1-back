package com.activity1.controllers;

import java.util.List;

import com.activity1.feign.Feign;
import com.activity1.models.Producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/ventas")
public class VentasController {
	
	@Autowired
	private Feign feignService;
	
	@Operation(summary = "Get all products")
	@GetMapping(path="/productos/")
	public ResponseEntity<List<Producto>> getProductos(){
		return new ResponseEntity<>(feignService.getProductos() ,HttpStatus.OK);
	}
	
}