package com.activity1;

import java.util.List;

import com.activity1.controllers.CategoriasController;
import com.activity1.models.Categoria;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CategoriasTest {
    @Autowired
    private CategoriasController categoriasController;

    @Test
    @Order(1)
    public void findByIdCategoriaTest(){
        ResponseEntity<Categoria> categoria;
        Long id = 3L;
        categoria = categoriasController.getCategoria(id);
        assertEquals(HttpStatus.OK.toString(),categoria.getStatusCode().toString());
        id = -1L;
        categoria = categoriasController.getCategoria(id);
        assertEquals(HttpStatus.NOT_FOUND.toString(),categoria.getStatusCode().toString());
    }

    @Test
    @Order(2)
    public void findAllCategoriasTest(){
        ResponseEntity<List<Categoria>> categorias;
        categorias = categoriasController.getCategorias();
        assertEquals(HttpStatus.OK.toString(),categorias.getStatusCode().toString());
    }

    @Test
    @Order(3)
    public void createCategoriaTest(){
        Categoria categoria = new Categoria(
            "Categoria 1 Test"
        );
        ResponseEntity<Categoria> categoriaResponse = categoriasController.createCategoria(categoria);
        assertEquals(HttpStatus.CREATED, categoriaResponse.getStatusCode());
        assertNotNull(categoriaResponse.getBody().getId());
    }

    @Test
    @Order(4)
    public void deleteCategoriaTest(){
        //Test de categoria no existente que intenta ser eliminado
        Long id = -1L;
        assertEquals(HttpStatus.NOT_FOUND,categoriasController.deleteCategoria(id).getStatusCode());

        //Test de categoria existente que es eliminado.
        Categoria categoria = new Categoria(
            "Categoria 1 Test"
        );
        ResponseEntity<Categoria> categoriaResponse = categoriasController.createCategoria(categoria);
        assertEquals(HttpStatus.CREATED, categoriaResponse.getStatusCode());
        assertNotNull(categoriaResponse.getBody().getId());
        id = categoriaResponse.getBody().getId();
        assertEquals(HttpStatus.ACCEPTED,categoriasController.deleteCategoria(id).getStatusCode());
    }

    @Test
    @Order(5)
    public void updateCategoriaTest(){
        //Test de categoria no existente que intenta ser actualizado
        Long id = -1L;
        Categoria categoria = new Categoria(
            "Categoria 1 Test",
            Boolean.TRUE
        );
        ResponseEntity<Categoria> categoryToUpdate = categoriasController.updateCategoria(id,categoria);
        assertEquals(HttpStatus.NOT_FOUND, categoryToUpdate.getStatusCode());

        //Test de categoria existente que es actualizado.
        ResponseEntity<Categoria> categoriaResponse = categoriasController.createCategoria(categoria);
        assertEquals(HttpStatus.CREATED, categoriaResponse.getStatusCode());
        assertNotNull(categoriaResponse.getBody().getId());
        id = categoriaResponse.getBody().getId();
        assertEquals(HttpStatus.ACCEPTED,categoriasController.updateCategoria(id,new Categoria("Categoria 1 Test", Boolean.FALSE)).getStatusCode());
    }

    @Test
    @Order(6)
    public void changeStatusTest(){
        //Test de categoria no existente que intenta ser actualizado
        Long id = -1L;
        assertEquals(HttpStatus.NOT_FOUND,categoriasController.changeStatus(id).getStatusCode());

        //Test de categoria existente que es actualizado.
        Categoria categoria = new Categoria(
            "Categoria 1 Test"
        );
        ResponseEntity<Categoria> categoriaResponse = categoriasController.createCategoria(categoria);
        assertEquals(HttpStatus.CREATED, categoriaResponse.getStatusCode());
        assertNotNull(categoriaResponse.getBody().getId());
        id = categoriaResponse.getBody().getId();
        assertEquals(HttpStatus.ACCEPTED,categoriasController.changeStatus(id).getStatusCode());
    }

    
}
