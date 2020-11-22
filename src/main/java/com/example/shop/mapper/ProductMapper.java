package com.example.shop.mapper;

import com.example.shop.domain.dao.Product;
import com.example.shop.domain.dto.ProductDto;
import com.example.shop.domain.elastic.ProductBasket;

import java.util.List;
import java.util.Set;

public interface ProductMapper {

    Product productDtoToProduct(ProductDto productDto);

    ProductDto productToProductDto(Product product);

    List<ProductDto> productsBasketToProductsDto(Set<ProductBasket> productsBasket);

}
