package com.ventas.ventas.controller;

import com.ventas.ventas.dto.SaleDTO;
import com.ventas.ventas.model.Sale;
import com.ventas.ventas.repository.ISaleRepository;
import com.ventas.ventas.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sale")
public class SaleController {
    @Autowired
    private ISaleService saleService;

    @PostMapping("/{id_cart}")
    public String createSale(@PathVariable ("id_cart") Long id_cart){
        saleService.createSale(id_cart);
        return "the cart has been correctly create";
    }
    @GetMapping("/getSale/{id_sale}")
    public Sale getSaleById(@PathVariable ("id_sale") Long id_sale){
        return saleService.getSale(id_sale);
    }

    @GetMapping("getSaleDetail/{id_sale}")
    public SaleDTO getSaleDetail(@PathVariable ("id_sale") Long id_sale){
        return saleService.getSaleDetails(id_sale);
    }
}
