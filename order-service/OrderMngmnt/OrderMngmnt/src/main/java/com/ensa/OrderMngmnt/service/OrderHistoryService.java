package com.ensa.OrderMngmnt.service;

import com.ensa.OrderMngmnt.model.OrderHistory;

import java.util.List;

public interface OrderHistoryService {
    List<OrderHistory> getOrderHistory(Long orderId);
    // Add more methods as needed for additional order history operations
}

