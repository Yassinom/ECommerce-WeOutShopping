package com.yassinom.productservice.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(value = "product")
@Data //getters and setters
@Builder // to create the builder method
@AllArgsConstructor //obvious no ?
@NoArgsConstructor //same
public class Product {

    @Id
    private String id;

    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    private String seller;
    private int stockQuantity;
    private List<String> images;
}
