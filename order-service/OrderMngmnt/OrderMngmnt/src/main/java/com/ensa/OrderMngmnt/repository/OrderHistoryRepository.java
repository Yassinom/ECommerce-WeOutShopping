package com.ensa.OrderMngmnt.repository;

import com.ensa.OrderMngmnt.model.OrderHistory;
import com.ensa.OrderMngmnt.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {

    List<OrderHistory> findByOrderId(Long orderId);
    List<OrderHistory> findByUserId(Long userId);
    List<OrderHistory> findByOrderStatus(OrderStatus orderStatus);

}

