package com.carrito.carrito.dto;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ProductDTO {
    private Long id;
    private String name;
    private String marca;
    private Double precio;
}
