package com.ventas.ventas.repository;

import com.ventas.ventas.model.Cart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("cart-service")
public interface ICartAPI {
    @GetMapping("/cart/{id}")
    public Cart getCart(@PathVariable ("id") Long id);
}
