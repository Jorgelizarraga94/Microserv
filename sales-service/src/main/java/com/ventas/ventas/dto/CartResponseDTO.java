package com.ventas.ventas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
public class CartResponseDTO {
    private Long saleId;
    private Long cartId;
    private LocalDate date;
    private Double totalPrice;
    private List<CartItemResponseDTO> items;
}
