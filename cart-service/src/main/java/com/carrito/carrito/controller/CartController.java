package com.carrito.carrito.controller;

import com.carrito.carrito.model.Cart;
import com.carrito.carrito.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ICartService carritoService;

    @Value("${server.port}")
    public int serverPort;

    // Endpoint para crear un carrito vacío
    @PostMapping("/create")
    public Cart createCart() {
        return carritoService.createNewCart();
    }

    @DeleteMapping("/delete/{cart_id}")
    public String deleteCart(@PathVariable ("cart_id") Long cart_id){
        carritoService.deleteCart(cart_id);
        return "delete cart correctly";
    }

    // Endpoint para agregar un producto al carrito
    // URL ejemplo: http://localhost:8083/cart/add?cartId=1&productId=2&cant=3
    @PostMapping("/add")
    public Cart addProduct(
            @RequestParam Long cartId,
            @RequestParam Long productId,
            @RequestParam Integer cant) {
        return carritoService.addProduct(cartId, productId, cant);
    }
    @DeleteMapping("/delete/{cart_id}/{product_id}")
    public String deleteProduct(@PathVariable Long cart_id,@PathVariable Long product_id){
        carritoService.deleteProduct(cart_id,product_id);
        return "eliminado con exito";
    }

    @GetMapping("/{id}")
    public Cart getCart(@PathVariable Long id) {
        return carritoService.getCart(id);
    }
}