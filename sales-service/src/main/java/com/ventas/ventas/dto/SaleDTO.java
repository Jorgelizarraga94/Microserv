package com.ventas.ventas.dto;

import com.ventas.ventas.model.Cart;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SaleDTO {
    private Long saleId;
    private LocalDate date;
    private Cart cart; // Aquí incluimos el objeto Cart completo
}
