package com.example.shop.service.impl;

import com.example.shop.domain.dao.Product;
import com.example.shop.repository.jpa.ProductRepository;
import com.example.shop.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product getProductById(long id) {
        log.info("Object doesn't exist in cache {}", id);
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    @Override
    public List<Product> getProductsByIds(Set<Long> ids) {
        return productRepository.findAllByIdIn(ids);
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);

    }

    @Override
    public void deleteProductById(long id) {
        productRepository.delete(getProductById(id));
    }

    @Override
    public Product updateProduct(long id, Product product) {

        Product updatedProduct = getProductById(id);

        updatedProduct.setDescription(product.getDescription());
        updatedProduct.setName(product.getName());
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setQuantity(product.getQuantity());

        return productRepository.save(updatedProduct);
    }

    @Override
    public Page<Product> getPage(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
}
