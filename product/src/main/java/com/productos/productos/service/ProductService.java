package com.productos.productos.service;

import com.productos.productos.model.Product;
import com.productos.productos.repository.IProductRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductService implements IProductService{
    @Autowired
    private IProductRepo productRepo;

    @Override
    public void createProduct(Product product) {
        productRepo.save(product);
    }
}
