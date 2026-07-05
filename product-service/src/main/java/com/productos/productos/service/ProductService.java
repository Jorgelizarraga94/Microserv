package com.productos.productos.service;

import com.productos.productos.model.Product;
import com.productos.productos.repository.IProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService{
    @Autowired
    private IProductRepo productRepo;

    @Override
    public void createProduct(Product product) {
        productRepo.save(product);
    }

    @Override
    public Product getProductById(Long id_product) {
        return productRepo.findById(id_product).orElse(null);
    }
}
