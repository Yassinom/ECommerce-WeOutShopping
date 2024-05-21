package com.yassinom.cartservice.service;

import com.yassinom.cartservice.dto.ProductToAddRequestDTO;
import com.yassinom.cartservice.model.Cart;
import com.yassinom.cartservice.model.CartItem;
import com.yassinom.cartservice.model.CartRequestToUpdate;
import com.yassinom.cartservice.repository.CartItemRepository;
import com.yassinom.cartservice.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    @Autowired
    private final CartRepository cartRepository;
    private final WebClient.Builder webClientBuilder;
    @Autowired
    private CartItemRepository cartItemRepository;

    public void addProductToCart(ProductToAddRequestDTO product) {
        Optional<Cart> cartOptional = cartRepository.findAll().stream() // Fetch all carts and stream
                .filter(cart -> product.getUserId().equals(cart.getUserId())) // Filter by userId
                .findFirst();
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();                 //getting the cart
            CartItem cartItem = CartItem.builder()         // Create a new CartItem
                    .productId(product.getProductId())
                    .quantity(product.getQuantity())
                    .cart(cart)
                    .build();
            cart.getItems().add(cartItem);                  // Add the CartItem to the Cart

            updateCartTotalPrice(cart.getCartId());             // Update the total price of the cart
            cartItemRepository.save(cartItem);                          // Save the updated cart back to the repository

        } else { throw new RuntimeException("Cart not found with user id: " + product.getUserId()); }
    }

    public void addCart(String userId) {
        // Fetch all carts and filter by userId
        List<Cart> carts = cartRepository.findAll().stream()        // check if user has already a cart
                .filter(cart -> cart.getUserId().equals(userId))
                .collect(Collectors.toList());
        if (!carts.isEmpty()) { throw new IllegalStateException("User already has a cart"); }
        else {
            Cart cart1 = Cart.builder()  // Create a new cart if none exists
                    .userId(userId)
                    .build();
            cartRepository.save(cart1);  // Save the cart to the database
        }
    }

    public void updateCartInfo(CartRequestToUpdate updatedCart) {
        Optional<Cart> optionalCart = cartRepository.findById(updatedCart.getCartId());
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            cart.setUserId(updatedCart.getUserId());
            cart.setItems(updatedCart.getItems());
            cart.setTotalPrice(updatedCart.getTotalPrice());
            cart.setCheckedOut(updatedCart.isCheckedOut());
            cart.setCheckoutDate(updatedCart.getCheckoutDate());
        } else { throw new RuntimeException("Cart not found with id: " + updatedCart.getCartId()); }
    }


    public void updateCartTotalPrice(Integer cartId) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            List<CartItem> cartItems = cart.getItems();
            BigDecimal totalPrice = BigDecimal.ZERO;

            for (CartItem cartItem : cartItems) {
                System.out.println("listing cart items for cart : " + cartItem.getCartItemId());
                String productId = cartItem.getProductId();
                Integer quantity = cartItem.getQuantity();

                WebClient webClient = webClientBuilder.build();
                BigDecimal price = webClient.get()
                        .uri("http://localhost:8080/api/product/{productId}/price", productId)
                        .retrieve()
                        .bodyToMono(BigDecimal.class)
                        .block();  // Blocking call to get the price synchronously

//                if (price != null) {
                    BigDecimal cartItemTotalPrice = price.multiply(BigDecimal.valueOf(quantity));
                    totalPrice = totalPrice.add(cartItemTotalPrice);
                    System.out.println("total price for cart item : " + cartItem.getCartItemId() + " est : " + totalPrice);
//                }
            }
            cart.setTotalPrice(totalPrice);
        } else { throw new RuntimeException("Cart not found with id: " + cartId); }
    }


    public void removeCart(String userId) {
        Optional<Cart> optionalCart = cartRepository.findAll().stream() // Fetch all carts and stream
                .filter(cart -> userId.equals(cart.getUserId())) // Filter by userId
                .findFirst();
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            cartRepository.delete(cart);
        } else { throw new RuntimeException("Cart not found with user id: " + userId); }
    }
}
