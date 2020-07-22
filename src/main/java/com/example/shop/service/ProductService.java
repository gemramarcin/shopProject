package com.example.shop.service;

import com.example.shop.domain.dao.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Product getProductById(long id);

    void addProduct(Product product);

    void deleteProductById(long id);

    void updateProduct(long id, Product product);

    Page<Product> getPage(Pageable pageable);

}
