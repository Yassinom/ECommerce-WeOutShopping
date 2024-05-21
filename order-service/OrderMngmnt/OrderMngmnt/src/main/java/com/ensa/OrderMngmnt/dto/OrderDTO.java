package com.ensa.OrderMngmnt.dto;

import java.util.Date;

public class OrderDTO {
    private String customerName;
    private Date orderDate;
    private String status;

    // Constructors, getters, and setters

    public OrderDTO() {
    }

    public OrderDTO(String customerName, Date orderDate, String status) {
        this.customerName = customerName;
        this.orderDate = orderDate;
        this.status = status;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

