package com.carrito.carrito.service;

import com.carrito.carrito.model.Cart;
import com.carrito.carrito.model.Product;
import com.carrito.carrito.dto.ProductDTO;
import com.carrito.carrito.repository.ICartRepository;
import com.carrito.carrito.repository.IProductAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class CartService implements ICartService{
    @Autowired
    ICartRepository cartRepository;

    @Autowired
    private IProductAPI productAPI;

    @Override
    public Cart createNewCart() {
        Cart cart = new Cart();
        cart.setItems(new ArrayList<>());
        cart.setTotalPrice(0.0);
        return cartRepository.save(cart);
    }

    @Override
    public Cart addProduct(Long cartId, Long productId, Integer cant) {
        Cart cart = getCart(cartId);

        boolean existe = cart.getItems().stream()
                .anyMatch(p -> p.getProductId().equals(productId));

        if (existe) {
            throw new RuntimeException("El producto ya está en el carrito");
        }

        Product nuevoProducto = new Product();
        nuevoProducto.setProductId(productId);
        nuevoProducto.setCant(cant); // Asegurate de setear la cantidad

        cart.getItems().add(nuevoProducto);

        ProductDTO productDTO = productAPI.getPrice(productId);
        Double total = productDTO.getPrecio() * cant;
        cart.setTotalPrice(cart.getTotalPrice() + total);

        return cartRepository.save(cart);
    }

    @Override
    @Transactional
    public String deleteProduct(Long cartId, Long productId) {
        Cart cart = getCart(cartId);
        Product product = cart.getItems().stream()
                .filter(p -> p.getProductId().equals(productId))
                .findFirst()
                .orElseThrow(()-> new RuntimeException("producto no encontrado"));

        ProductDTO productDto = productAPI.getPrice(product.getProductId());
        Double discount = productDto.getPrecio() * product.getCant();
        cart.setTotalPrice(cart.getTotalPrice() - discount);

        cart.getItems().removeIf(p -> p.getProductId().equals(productId));
        cartRepository.save(cart);
        return "eliminado correctamente";
    }

    @Override
    public Cart getCart(Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }
}
