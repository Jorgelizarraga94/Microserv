package com.carrito.carrito.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
public class CartResponseDTO {
    private Long cartId;
    private Double totalPrice;
    private List<CartItemResponseDTO> items;
}
