package com.ensa.OrderMngmnt.controller;



import com.ensa.OrderMngmnt.model.OrderHistory;
import com.ensa.OrderMngmnt.service.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders/history")
public class OrderHistoryController {

    private final OrderHistoryService orderHistoryService;

    @Autowired
    public OrderHistoryController(OrderHistoryService orderHistoryService) {
        this.orderHistoryService = orderHistoryService;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<List<OrderHistory>> getOrderHistory(@PathVariable Long orderId) {
        List<OrderHistory> orderHistory = orderHistoryService.getOrderHistory(orderId);
        return new ResponseEntity<>(orderHistory, HttpStatus.OK);
    }

    // Add more endpoints as needed for additional order history operations
}

