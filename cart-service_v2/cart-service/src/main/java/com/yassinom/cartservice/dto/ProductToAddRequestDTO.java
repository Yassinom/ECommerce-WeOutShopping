package com.yassinom.cartservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data //getters and setters
@Builder // to create the builder method
@AllArgsConstructor //obvious no ?
@NoArgsConstructor
public class ProductToAddRequestDTO {
    private String userId;
    private String productId;
    private Integer quantity;
}
