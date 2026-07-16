package com.carrito.carrito.service;

import com.carrito.carrito.dto.ProductDTO;
import com.carrito.carrito.repository.IProductAPI;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductClient {
    @Autowired
    private IProductAPI productAPI;

    @CircuitBreaker(name="product-service" , fallbackMethod = "fallbackGetProduct")
    public ProductDTO getProduct(Long productId){
        return productAPI.getProduct(productId);
    }

    public ProductDTO fallbackGetProduct(Long productId, Throwable t){
        System.out.println("error de llamada a microservicio product, error "  +  t.getMessage());
        ProductDTO productDTO = new ProductDTO();
        productDTO.setPrice(0.0);
        productDTO.setName("producto no disponible");
        productDTO.setBrand("marca no disponible");
        return productDTO;
    }
}
