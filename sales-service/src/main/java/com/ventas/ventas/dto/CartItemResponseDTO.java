package com.ventas.ventas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor @AllArgsConstructor
public class CartItemResponseDTO {
    private Long productId;
    private String name;
    private String brand;
    private Double unitPrice;
    private Integer quantity;
}
