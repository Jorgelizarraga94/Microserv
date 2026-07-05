package com.carrito.carrito.service;

import com.carrito.carrito.model.Cart;

public interface ICartService {
    Cart createNewCart();
    Cart addProduct(Long cartId, Long productId, Integer cant);
    Cart getCart(Long id);
}
