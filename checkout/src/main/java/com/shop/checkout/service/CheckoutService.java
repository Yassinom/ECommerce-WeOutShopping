package com.shop.checkout.service;

import com.shop.checkout.model.Order;
import com.shop.checkout.model.Payment;
import com.shop.checkout.model.Receipt;
import com.shop.checkout.repository.OrderRepository;
import com.shop.checkout.repository.PaymentRepository;
import com.shop.checkout.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckoutService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ReceiptRepository receiptRepository;

    public Order placeOrder(Order order) {
        try {
            return orderRepository.save(order);
        } catch (Exception e) {
            // Handle exception
            throw new RuntimeException("Failed to place order: " + e.getMessage());
        }
    }

    public Payment makePayment(Payment payment) {
        try {
            Payment savedPayment = paymentRepository.save(payment);
            generateReceipt(savedPayment);
            return savedPayment;
        } catch (Exception e) {
            // Handle exception
            throw new RuntimeException("Failed to make payment: " + e.getMessage());
        }
    }

    private void generateReceipt(Payment payment) {
        try {
            Receipt receipt = new Receipt();
            receipt.setOrderId(payment.getOrderId());
            receipt.setPaymentId(payment.getId());
            receipt.setReceiptDetails("Receipt for Order ID: " + payment.getOrderId() + ", Payment ID: " + payment.getId() + ", Amount: " + payment.getAmount());
            receiptRepository.save(receipt);
        } catch (Exception e) {
            // Handle exception
            throw new RuntimeException("Failed to generate receipt: " + e.getMessage());
        }
    }
}
