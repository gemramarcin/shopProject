package com.example.shop.domain.elastic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductBasket {
    private String name;
    private Long id;
    private Integer quantity;
    private Double price;
}
