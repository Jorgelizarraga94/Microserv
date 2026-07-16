package com.carrito.carrito.dto;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ProductDTO {
    private Long ProductId;
    private String name;
    private String brand;
    private Double price;
}
