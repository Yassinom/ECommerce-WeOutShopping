package com.yassinom.cartservice.service;


import com.yassinom.cartservice.model.CartItem;
import com.yassinom.cartservice.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CartItemService {


    private final CartItemRepository cartItemRepository;
    @Autowired
    public CartService cartService;

    public void deleteCartItemById(Integer cartItemId) {
        Optional<CartItem> optionalCartItem = cartItemRepository.findById(cartItemId);
        if (optionalCartItem.isPresent()) {
            CartItem cartItem = optionalCartItem.get();
            cartItemRepository.deleteById(cartItem.getCartItemId());
            System.out.println(cartItem.getCartItemId());
            cartService.updateCartTotalPrice(cartItem.getCart().getCartId());

        } else { throw new RuntimeException("Cart item not found with id: " + cartItemId); }
    }
}
