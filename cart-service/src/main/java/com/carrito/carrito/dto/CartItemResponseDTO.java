package com.carrito.carrito.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
public class CartItemResponseDTO {
    private Long productId;
    private String name;
    private String brand;
    private Double unitPrice;
    private Integer quantity;
    private Double subtotal;
}
