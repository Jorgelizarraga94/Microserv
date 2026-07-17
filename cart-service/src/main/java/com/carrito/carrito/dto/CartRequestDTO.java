package com.carrito.carrito.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
public class CartRequestDTO {
    private Long cartId;
    private Long productId;
    private Integer cant;
}