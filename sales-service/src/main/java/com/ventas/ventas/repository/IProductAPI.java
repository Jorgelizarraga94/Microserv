package com.ventas.ventas.repository;

import com.ventas.ventas.dto.ProductDTO;
import com.ventas.ventas.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("product-service")
public interface IProductAPI {
    @GetMapping("product/{id_product}")
    public ProductDTO getProductById(@PathVariable ("id_product") Long id_product);
}
