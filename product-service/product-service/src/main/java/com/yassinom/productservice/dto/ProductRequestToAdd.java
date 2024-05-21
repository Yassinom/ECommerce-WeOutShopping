package com.yassinom.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data //getters and setters
@Builder // to create the builder method
@AllArgsConstructor //obvious no ?
@NoArgsConstructor //same
public class ProductRequestToAdd {
    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    private String seller;
    private int stockQuantity;
    private List<String> images;
}
