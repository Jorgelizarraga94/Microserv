package com.carrito.carrito.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor
public class CartResponseDTO {
    private Long cartId;
    private Double totalPrice;
    private List<CartItemResponseDTO> items;
}
