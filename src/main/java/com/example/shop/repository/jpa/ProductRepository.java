package com.example.shop.repository.jpa;

import com.example.shop.domain.dao.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByIdIn(Set<Long> ids);
}
