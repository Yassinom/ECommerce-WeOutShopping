package com.shop.checkout.model;

import jakarta.persistence.*;

@Entity
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;
    private Long paymentId;
    private String receiptDetails;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public String getReceiptDetails() {
        return receiptDetails;
    }

    public void setReceiptDetails(String receiptDetails) {
        this.receiptDetails = receiptDetails;
    }
}
