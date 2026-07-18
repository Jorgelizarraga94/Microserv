package com.ventas.ventas.service;

import com.ventas.ventas.dto.CartResponseDTO;
import com.ventas.ventas.dto.ProductDTO;
import com.ventas.ventas.dto.SaleDTO;
import com.ventas.ventas.model.Product;
import com.ventas.ventas.model.Sale;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ISaleService{
    Sale createSale(String user_id, Long id_cart);
    Sale getSale(Long idSale);

    List<CartResponseDTO> getSalesByUserId(String userId);
}
