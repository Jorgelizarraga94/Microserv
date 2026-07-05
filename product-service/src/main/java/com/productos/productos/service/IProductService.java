package com.productos.productos.service;

import com.productos.productos.model.Product;
import org.springframework.stereotype.Service;

@Service
public interface IProductService {
    public void createProduct(Product product);
    public Product getProductById(Long id_product);
}
