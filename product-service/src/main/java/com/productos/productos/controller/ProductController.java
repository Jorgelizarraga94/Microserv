package com.productos.productos.controller;

import com.productos.productos.model.Product;
import com.productos.productos.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "https://micro-serv-front-end-react.vercel.app", allowCredentials = "true")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("/{id_product}")
    public Product getProductById(@PathVariable("id_product") Long id_product){
        return productService.getProductById(id_product);
    }
    @GetMapping("/products")
    public List<Product> getProductList(){
        return productService.getProductsList();
    }
    @PostMapping("/create")
    public void create(@RequestBody Product product){
        productService.createProduct(product);
    }
    @DeleteMapping("/delete/{id_product}")
    public String deleteProductById(@PathVariable("id_product") Long id_product){
        productService.deleteProductById(id_product);
        return "eliminado con exito";
    }



}
