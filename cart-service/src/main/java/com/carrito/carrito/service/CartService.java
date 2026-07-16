package com.carrito.carrito.service;

import com.carrito.carrito.dto.CartItemResponseDTO;
import com.carrito.carrito.dto.CartResponseDTO;
import com.carrito.carrito.model.Cart;
import com.carrito.carrito.model.Product;
import com.carrito.carrito.dto.ProductDTO;
import com.carrito.carrito.repository.ICartRepository;
import com.carrito.carrito.repository.IProductAPI;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService implements ICartService{
    @Autowired
    ICartRepository cartRepository;
    @Autowired
    private ProductClient productClient;

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

        // 1. Verificación
        boolean existe = cart.getItems().stream()
                .anyMatch(p -> p.getProductId().equals(productId));

        if (existe) {
            throw new RuntimeException("El producto ya está en el carrito");
        }

        // 2. Crear y asignar
        Product nuevoProducto = new Product();
        nuevoProducto.setProductId(productId);
        nuevoProducto.setCant(cant);

        // 3. Importante: inicializa el totalPrice si es null antes de sumar
        if (cart.getTotalPrice() == null) cart.setTotalPrice(0.0);

        // 4. Obtener datos del microservicio
        ProductDTO productDTO = productClient.getProduct(productId);

        // 5. Calcular y sumar
        Double subtotal = productDTO.getPrice() * cant;
        cart.setTotalPrice(cart.getTotalPrice() + subtotal);

        // 6. Agregar a la lista (Hibernate detectará el nuevo objeto al guardar el padre)
        cart.getItems().add(nuevoProducto);

        // 7. Guardar el carrito. Como tienes CascadeType.ALL, guardará el nuevo Product automáticamente
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

        ProductDTO productDto = productClient.getProduct(product.getProductId());
        Double discount = productDto.getPrice() * product.getCant();
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

    @Override
    public CartResponseDTO getDetailedCart(Long id) {
            // 1. Buscas la entidad en la base de datos
            Cart cartEntity = cartRepository.findById(id).orElseThrow();

            // 2. Transformas la lista de entidades (IDs) en una lista enriquecida (DTOs)
            List<CartItemResponseDTO> detailedItems = cartEntity.getItems().stream().map(product -> {
                // Aquí llamas al otro microservicio (product-service)
                ProductDTO details = productClient.getProduct(product.getProductId());

                return new CartItemResponseDTO(
                        product.getProductId(),
                        details.getName(),
                        details.getBrand(),
                        details.getPrice(),
                        product.getCant(),
                        details.getPrice() * product.getCant() // El subtotal calculado
                );
            }).toList();

            // 3. Devuelves el DTO final que el front va a recibir
            return new CartResponseDTO(cartEntity.getId(), cartEntity.getTotalPrice(), detailedItems);
    }

    @Override
    public List<Cart> getcarts() {
        return cartRepository.findAll();
    }
}
