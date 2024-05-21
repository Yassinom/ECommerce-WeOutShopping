package com.ensa.OrderMngmnt.service;

import com.ensa.OrderMngmnt.model.Order;

public interface OrderService {
    Order createOrder(Order order);
    Order getOrderById(Long orderId);


}

