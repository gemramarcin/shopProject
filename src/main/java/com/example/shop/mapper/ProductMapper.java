package com.example.shop.mapper;

import com.example.shop.domain.dao.Product;
import com.example.shop.domain.dto.ProductDto;

public interface ProductMapper {

    Product productDtoToProduct(ProductDto productDto);

    ProductDto productToProductDto(Product product);

}
