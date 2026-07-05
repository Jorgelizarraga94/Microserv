package com.carrito.carrito.repository;

import com.carrito.carrito.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("product-service")
public interface IProductAPI {
    @GetMapping("/product/{id_product}")
    public ProductDTO getPrice(@PathVariable("id_product") Long id_product);
}
