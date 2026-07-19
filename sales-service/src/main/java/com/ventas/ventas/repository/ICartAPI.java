package com.ventas.ventas.repository;

import com.ventas.ventas.config.FeignClientConfig;
import com.ventas.ventas.dto.CartResponseDTO;
import com.ventas.ventas.model.Cart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
@FeignClient(name = "cart-service", configuration = FeignClientConfig.class)
public interface ICartAPI {
    @GetMapping("cart/{id}")
    public CartResponseDTO getCart(@PathVariable("id") Long id);
}
