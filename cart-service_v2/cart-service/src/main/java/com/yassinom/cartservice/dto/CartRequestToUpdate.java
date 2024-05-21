package com.yassinom.cartservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartRequestToUpdate {
    private Integer cartId;
    private String userId;             // User ID associated with the cart
    private List<CartItem> items;      // All Items in cart
    private BigDecimal totalPrice;    // Total price of all items in the cart
    private boolean isCheckedOut;      // Flag indicating whether the cart has been checked out
    private Date checkoutDate;       // Date and time when the cart was checked out
}
