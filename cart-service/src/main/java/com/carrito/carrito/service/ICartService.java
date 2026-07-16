package com.carrito.carrito.service;

import com.carrito.carrito.dto.CartResponseDTO;
import com.carrito.carrito.model.Cart;

import java.util.List;

public interface ICartService {
    Cart createNewCart();
    Cart addProduct(Long cartId, Long productId, Integer cant);
    String deleteProduct(Long cartId, Long productId);
    Cart getCart(Long id);

    void deleteCart(Long cartId);
    CartResponseDTO getDetailedCart(Long id);
    List<Cart> getcarts();
}
