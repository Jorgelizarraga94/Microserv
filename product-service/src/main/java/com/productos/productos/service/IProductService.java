package com.productos.productos.service;

import com.productos.productos.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductService {
    public void createProduct(Product product);
    public Product getProductById(Long id_product);

    List<Product> getProductsList();

    void deleteProductById(Long idProduct);
}
