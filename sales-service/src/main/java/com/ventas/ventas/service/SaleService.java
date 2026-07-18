package com.ventas.ventas.service;

import com.ventas.ventas.dto.CartResponseDTO;
import com.ventas.ventas.dto.ProductDTO;
import com.ventas.ventas.dto.SaleDTO;
import com.ventas.ventas.model.Cart;
import com.ventas.ventas.model.Product;
import com.ventas.ventas.model.Sale;
import com.ventas.ventas.repository.ICartAPI;
import com.ventas.ventas.repository.IProductAPI;
import com.ventas.ventas.repository.ISaleRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleService implements ISaleService{
    @Autowired
    private ICartAPI cartAPI;
    @Autowired
    private IProductAPI productAPI;
    @Autowired
    private ISaleRepository saleRepository;
    @Autowired
    private ProductClient productClient;
    @Override
    public Sale createSale(String user_id, Long id_cart) {
        CartResponseDTO cart = cartAPI.getCart(id_cart);

        // Validación crítica
        if (cart == null) {
            throw new RuntimeException("No se encontró un carrito con el ID: " + id_cart);
        }

        Sale sale = new Sale();
        sale.setUserId(user_id);
        sale.setDate(LocalDate.now());
        sale.setCart_id(cart.getCartId());
        return saleRepository.save(sale);
    }

    @Override
    public Sale getSale(Long idSale) {
        return saleRepository.findById(idSale).orElse(null);
    }


    @Override
    public List<CartResponseDTO> getSalesByUserId(String userId) {
        List<Sale> sales = saleRepository.findByUserId(userId);
        List<CartResponseDTO> listCart = new ArrayList<>();

        for (Sale sale : sales){
            CartResponseDTO cart = cartAPI.getCart(sale.getCart_id());
            cart.setSaleId(sale.getId());
            cart.setDate(sale.getDate());
            listCart.add(cart);
        }

        return listCart;
    }
}
