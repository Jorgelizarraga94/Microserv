package com.ventas.ventas.service;

import com.ventas.ventas.dto.ProductDTO;
import com.ventas.ventas.dto.SaleDTO;
import com.ventas.ventas.model.Product;
import com.ventas.ventas.model.Sale;
import org.springframework.stereotype.Service;

@Service
public interface ISaleService{
    Sale createSale(Long id_cart);
    Sale getSale(Long idSale);
    SaleDTO getSaleDetails(Long saleId);
}
