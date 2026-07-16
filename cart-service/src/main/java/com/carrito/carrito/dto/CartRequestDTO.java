package com.carrito.carrito.dto;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class CartRequestDTO {
    private Long cartId;
    private Long productId;
    private Integer cant;
}