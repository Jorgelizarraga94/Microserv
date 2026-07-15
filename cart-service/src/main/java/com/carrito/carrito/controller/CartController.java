package com.carrito.carrito.controller;

import com.carrito.carrito.model.Cart;
import com.carrito.carrito.model.Product;
import com.carrito.carrito.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ICartService carritoService;

    @Value("${server.port}")
    public int serverPort;

    @DeleteMapping("/delete/{cart_id}")
    public String deleteCart(@PathVariable ("cart_id") Long cart_id){
        carritoService.deleteCart(cart_id);
        return "delete cart correctly";
    }


    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        carritoService.createNewCart();
        carritoService.addProduct(product.getId(), product.getProductId(), product.getCant());
        return ResponseEntity.ok("producto agregado correctamente");
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

    @GetMapping("/getcarts")
    public List<Cart> getCarts(){
        return carritoService.getcarts();
    }
}