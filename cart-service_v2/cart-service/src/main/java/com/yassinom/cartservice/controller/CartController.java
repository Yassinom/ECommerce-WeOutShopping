package com.yassinom.cartservice.controller;

import com.yassinom.cartservice.dto.ProductToAddRequestDTO;
import com.yassinom.cartservice.model.CartRequestToUpdate;
import com.yassinom.cartservice.service.CartItemService;
import com.yassinom.cartservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private final CartService cartService;
    @Autowired
    private final CartItemService cartItemService;


    @PostMapping("/addCart/{userId}") //we're sending a post requset
    @ResponseStatus(HttpStatus.CREATED)
    public void addCart(@PathVariable String userId){ cartService.addCart(userId); }

    @DeleteMapping("/removeCart/{userId}")
    public void removeCart(@PathVariable String userId) { cartService.removeCart(userId); }


    @DeleteMapping("/removeFromCart/{cartItemId}")
    public void deleteCartItemById(@PathVariable Integer cartItemId) { cartItemService.deleteCartItemById(cartItemId); }

    @PostMapping("/addToCart") //we're sending a post requset
    @ResponseStatus(HttpStatus.CREATED)
    public void addProductToCart(@RequestBody ProductToAddRequestDTO product){
        cartService.addProductToCart(product);
    }


    @PutMapping("/updateCart")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCartInfo(@RequestBody CartRequestToUpdate updatedCart){ cartService.updateCartInfo(updatedCart); }

}
