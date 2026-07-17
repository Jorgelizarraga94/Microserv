package com.carrito.carrito.controller;

import com.carrito.carrito.dto.CartRequestDTO;
import com.carrito.carrito.dto.CartResponseDTO;
import com.carrito.carrito.model.Cart;
import com.carrito.carrito.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @PostMapping("/create")
    public ResponseEntity<Cart> createCart() {
        return ResponseEntity.ok(carritoService.createNewCart());
    }


    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody CartRequestDTO request) {
        System.out.println("ID del producto recibido: " + request.getProductId());
        carritoService.addProduct(request.getCartId(), request.getProductId(), request.getCant());
        return ResponseEntity.ok("producto agregado correctamente");
    }

    @DeleteMapping("/delete/{cart_id}/{product_id}")
    public String deleteProduct(@PathVariable Long cart_id,@PathVariable Long product_id){
        carritoService.deleteProduct(cart_id,product_id);
        return "eliminado con exito";
    }

    @GetMapping("/{id}")
    public CartResponseDTO getCart(@PathVariable Long id) {
        return carritoService.getDetailedCart(id);
    }

    @GetMapping("/getInfoCartById/{id}")
    public Cart getInfoCart(@PathVariable Long id){
        return carritoService.getCart(id);
    }

    @GetMapping("/getcarts")
    public List<Cart> getCarts(){
        return carritoService.getcarts();
    }
}