package com.ensa.OrderMngmnt.service.impl;


import com.ensa.OrderMngmnt.model.Order;
import com.ensa.OrderMngmnt.repository.OrderRepository;
import com.ensa.OrderMngmnt.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(Order order) {
        // Add any validation or business logic here
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + orderId));
    }

    // Implement other methods as needed for updating, deleting, and other order operations
}

