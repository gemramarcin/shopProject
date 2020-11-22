package com.example.shop.repository.elastic;

import com.example.shop.domain.elastic.Basket;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface BasketRepository extends ElasticsearchRepository<Basket, String> {

    Optional<Basket> findByEmail(String email);
}
