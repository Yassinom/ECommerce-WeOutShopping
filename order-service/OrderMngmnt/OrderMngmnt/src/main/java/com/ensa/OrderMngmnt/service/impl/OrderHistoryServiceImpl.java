package com.ensa.OrderMngmnt.service.impl;


import com.ensa.OrderMngmnt.model.OrderHistory;
import com.ensa.OrderMngmnt.model.OrderStatus;
import com.ensa.OrderMngmnt.repository.OrderHistoryRepository;
import com.ensa.OrderMngmnt.service.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {

    private final OrderHistoryRepository orderHistoryRepository;

    @Autowired
    public OrderHistoryServiceImpl(OrderHistoryRepository orderHistoryRepository) {
        this.orderHistoryRepository = orderHistoryRepository;
    }

    @Override
    public List<OrderHistory> getOrderHistory(Long orderId) {
        return orderHistoryRepository.findByOrderId(orderId);
    }
    public List<OrderHistory> findByOrderStatus(OrderStatus orderStatus) {
        return orderHistoryRepository.findByOrderStatus(orderStatus);
    }
    public List<OrderHistory> findByUserId(Long userId) {
        return orderHistoryRepository.findByUserId(userId);
    }

}
