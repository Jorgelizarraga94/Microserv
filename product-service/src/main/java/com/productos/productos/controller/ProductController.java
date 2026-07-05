package com.productos.productos.controller;

import com.productos.productos.model.Product;
import com.productos.productos.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("/{id_product}")
    public Product getProductById(@PathVariable("id_product") Long id_product){
        return productService.getProductById(id_product);
    }

    @PostMapping("/create")
    public void create(@RequestBody Product product){
        productService.createProduct(product);
    }



}
