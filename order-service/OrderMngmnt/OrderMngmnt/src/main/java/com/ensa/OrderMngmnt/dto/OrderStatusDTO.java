package com.ensa.OrderMngmnt.dto;

public class OrderStatusDTO {
    private String currentStatus;

    // Constructors, getters, and setters

    public OrderStatusDTO() {
    }

    public OrderStatusDTO(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }
}
