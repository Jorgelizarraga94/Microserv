package com.carrito.carrito.service;

import com.carrito.carrito.model.Cart;
import com.carrito.carrito.model.Product;
import com.carrito.carrito.repository.ICartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CartService implements ICartService{
    @Autowired
    ICartRepository cartRepository;
    @Override
    public Cart createNewCart() {
        Cart cart = new Cart();
        cart.setItems(new ArrayList<>());
        cart.setTotalPrice(0.0);
        return cartRepository.save(cart);
    }

    @Override
    public Cart addProduct(Long cartId, Long productId, Integer cant) {
        Cart cart = cartRepository.findById(cartId).orElse(null);

        Product product = new Product();
        product.setProductId(productId);
        product.setCant(cant);

        assert cart != null;
        cart.getItems().add(product);
        return cartRepository.save(cart);
    }

    @Override
    public Cart getCart(Long id) {
        return cartRepository.findById(id).orElse(null);
    }
}
