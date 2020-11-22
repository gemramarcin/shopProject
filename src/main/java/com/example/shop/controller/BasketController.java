package com.example.shop.controller;

import com.example.shop.domain.dto.ProductDto;
import com.example.shop.domain.elastic.Basket;
import com.example.shop.mapper.ProductMapper;
import com.example.shop.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/basket")
@RequiredArgsConstructor
//@PreAuthorize("isAuthenticated()")
public class BasketController {

    private final BasketService basketService;
    private final ProductMapper productMapper;

    @GetMapping
    public List<ProductDto> getBasket(){
       return productMapper.productsBasketToProductsDto(basketService.getUserBasket().getProducts());
    }


    @PostMapping
    public void addToBasket(@RequestBody ProductDto productDto){
        basketService.addToBasket(productDto.getId(), productDto.getQuantity());
    }

    @DeleteMapping("{productId}")
    public void deleteFromBasket(@PathVariable Long productId){
        basketService.deleteProductById(productId);
    }


}
