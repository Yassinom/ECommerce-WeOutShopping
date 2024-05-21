package com.shop.checkout.controller;

import com.shop.checkout.model.Order;
import com.shop.checkout.model.Payment;
import com.shop.checkout.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    @PostMapping("/place-order")
    public Order placeOrder(@RequestBody Order order) {
        try {
            return checkoutService.placeOrder(order);
        } catch (Exception e) {
            // Handle exception
            throw new RuntimeException("Failed to place order: " + e.getMessage());
        }
    }

    @PostMapping("/make-payment")
    public Payment makePayment(@RequestBody Payment payment) {
        try {
            return checkoutService.makePayment(payment);
        } catch (Exception e) {
            // Handle exception
            throw new RuntimeException("Failed to make payment: " + e.getMessage());
        }
    }
}
