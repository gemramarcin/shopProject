package com.example.shop.service;

import com.example.shop.domain.dao.Product;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface ProductService {

    @Cacheable(cacheNames = "product", key = "#id")
    Product getProductById(long id);

    List<Product> getProductsByIds(Set<Long> ids);

    @CachePut(cacheNames = "product", key = "#result.id")
    Product addProduct(Product product);

    @CacheEvict(cacheNames = "product", key = "#id")
    void deleteProductById(long id);

    @CachePut(cacheNames = "product", key = "#result.id")
    Product updateProduct(long id, Product product);

    Page<Product> getPage(Pageable pageable);

}
