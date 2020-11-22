package com.example.shop.service;

import com.example.shop.domain.dto.ProductDto;
import com.example.shop.domain.elastic.Basket;

public interface BasketService {
    void addToBasket(Long productId, Integer productQuantity);
    Basket getUserBasket();
    void deleteProductById(Long productId);
    void update(Basket userBasket);
}
