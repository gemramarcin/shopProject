package com.example.shop.mapper.impl;

import com.example.shop.domain.dao.Product;
import com.example.shop.domain.dto.ProductDto;
import com.example.shop.domain.elastic.ProductBasket;
import com.example.shop.mapper.ProductMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductMapperImpl implements ProductMapper {
    @Override
    public Product productDtoToProduct(ProductDto productDto) {
        return Product.builder()
                .description(productDto.getDescription())
                .id(productDto.getId())
                .name(productDto.getName())
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .version(productDto.getVersion())
                .build();
    }

    @Override
    public ProductDto productToProductDto(Product product) {
        return ProductDto.builder()
                .description(product.getDescription())
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .version(product.getVersion())
                .build();
    }

    @Override
    public List<ProductDto> productsBasketToProductsDto(Set<ProductBasket> productsBasket) {
        return productsBasket.stream()
                .map(productBasket -> ProductDto.builder()
                        .name(productBasket.getName())
                        .price(productBasket.getPrice())
                        .quantity(productBasket.getQuantity())
                        .id(productBasket.getId())
                        .build())
                .collect(Collectors.toList());
    }
}
