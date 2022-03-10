package com.activity1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.activity1.controllers.ProductosController;
import com.activity1.models.Producto;


import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class ProductosTest {
    @Autowired
    private ProductosController productController;

    @Test
    @Order(2)
    public void findByIdProductoTest(){
        //Test de producto existente que es encontrado
        ResponseEntity<Producto> producto;
        long id = 4L;
        producto = productController.getProducto(id);
        assertEquals(HttpStatus.OK,producto.getStatusCode());
        //Test de producto no existente que intenta ser encontrado
        id = -1;
        producto = productController.getProducto(id);
        assertEquals(HttpStatus.NOT_FOUND,producto.getStatusCode());
    }

    @Test
    @Order(3)
    public void createProductTest(){
        Producto producto = new Producto(
            "Producto 1",
            "Descripcion del producto 1",
            15,
            10,
            1
        );
        ResponseEntity<Producto> product = productController.createProducto(producto);
        assertEquals(HttpStatus.CREATED, product.getStatusCode());
        assertNotNull(product.getBody().getId());
    }

    @Test
    @Order(4)
    public void updateProductoTest(){
        //Test de producto no existente que es actualizado
        Long id = -1L;
        Producto producto = new Producto(
            "Producto 1 tests",
            "Descripcion del producto 1",
            15,
            10,
            1
        );
        ResponseEntity<Producto> product = productController.updateProducto(id,producto);
        assertEquals(HttpStatus.NOT_FOUND,product.getStatusCode());
        //Test de producto existente que es actualizado
        ResponseEntity<Producto> productoResponse = productController.createProducto(producto);
        assertEquals(HttpStatus.CREATED, productoResponse.getStatusCode());
        assertNotNull(productoResponse.getBody().getId());
        id = productoResponse.getBody().getId();
        producto = new Producto(
            "Producto 1 test actualizado",
            "Descripcion del producto 1",
            15,
            10,
            1
        );
        product = productController.updateProducto(id,producto);
        
    }

    @Test
    @Order(5)
    public void changeStatusTest(){
        //Test de producto no existente que es actualizado
        Long id = -1L;
        ResponseEntity<Producto> product = productController.changeStatus(id);
        assertEquals(HttpStatus.NOT_FOUND,product.getStatusCode());
        //Test de producto existente que es actualizado
        ResponseEntity<Producto> productoResponse = productController.createProducto(new Producto(
            "Producto 1 test actualizado",
            "Descripcion del producto 1",
            15,
            10,
            1
        ));
        assertEquals(HttpStatus.CREATED, productoResponse.getStatusCode());
        assertNotNull(productoResponse.getBody().getId());
        id = productoResponse.getBody().getId();
        product = productController.changeStatus(id);
        assertEquals(HttpStatus.ACCEPTED,product.getStatusCode());
    }



    @Test
    @Order(6)
    public void deleteProductTest(){
        //Test de producto no existente que intenta ser eliminado
        long id = -1;
        assertEquals(HttpStatus.NOT_FOUND,productController.deleteProducto(id).getStatusCode());

        //Test de producto existente que es eliminado.
        Producto producto = new Producto(
            "Producto 1 Test",
            "Descripcion del producto 1",
            15,
            10,
            1
        );

        ResponseEntity<Producto> productoSaved = productController.createProducto(producto);
        assertEquals(HttpStatus.ACCEPTED,productController.deleteProducto(productoSaved.getBody().getId()).getStatusCode());
    }

}
