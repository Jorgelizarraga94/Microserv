package com.ventas.ventas.dto;

import com.ventas.ventas.model.Cart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@AllArgsConstructor @NoArgsConstructor
@Getter
@Setter
public class SaleDTO {
    private Long saleId;
    private LocalDate date;
    private Cart cart;
}
