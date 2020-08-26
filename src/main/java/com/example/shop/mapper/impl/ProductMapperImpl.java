package com.example.shop.mapper.impl;

import com.example.shop.domain.dao.Product;
import com.example.shop.domain.dto.ProductDto;
import com.example.shop.mapper.ProductMapper;
import org.springframework.stereotype.Component;

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
}
