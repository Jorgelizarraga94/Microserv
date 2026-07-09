package com.ventas.ventas.service;

import com.ventas.ventas.dto.ProductDTO;
import com.ventas.ventas.repository.IProductAPI;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductClient {
    @Autowired
    private IProductAPI productAPI;

    @CircuitBreaker(name = "cart-service", fallbackMethod = "fallbackGetCart")
    public ProductDTO getProductDetails(Long productId) {
        return productAPI.getProductById(productId);
    }

    public ProductDTO fallbackGetCart(Long productId, Throwable t) {
        System.out.println("El servicio falló: " + t.getMessage());
        return new ProductDTO(); // Retorna un objeto vacío/seguro
    }
}
