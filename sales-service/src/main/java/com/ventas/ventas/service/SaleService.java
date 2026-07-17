package com.ventas.ventas.service;

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
    public Sale createSale(Long id_cart) {
        Cart cart = cartAPI.getCart(id_cart);

        // Validación crítica
        if (cart == null) {
            throw new RuntimeException("No se encontró un carrito con el ID: " + id_cart);
        }

        Sale sale = new Sale();
        sale.setDate(LocalDate.now());
        sale.setCart_id(cart.getId());
        return saleRepository.save(sale);
    }

    @Override
    public Sale getSale(Long idSale) {
        return saleRepository.findById(idSale).orElse(null);
    }

    @Override
    public SaleDTO getSaleDetails(Long saleId) {
        // 1. Buscamos la venta en la base de datos de 'sales-service'
        Sale sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        // 2. Traemos la información completa del carrito desde 'cart-service' usando Feign
        // Como 'cart-service' ya maneja la lógica de los productos,
        // nos devolverá el objeto Cart con su lista de productos.
        Cart cart = cartAPI.getCart(sale.getCart_id());
        for (Product product : cart.getItems()){
            ProductDTO newProduct = productClient.getProductDetails(product.getProductId());
            cart.getProductDTO().add(newProduct);
        }
        // 3. Combinamos todo en nuestro DTO de respuesta
        SaleDTO response = new SaleDTO();
        response.setSaleId(sale.getId());
        response.setDate(sale.getDate());
        response.setCart(cart);

        return response;
    }
}
