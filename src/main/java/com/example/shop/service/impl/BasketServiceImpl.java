package com.example.shop.service.impl;

import com.example.shop.domain.dao.Product;
import com.example.shop.domain.dao.User;
import com.example.shop.domain.elastic.Basket;
import com.example.shop.domain.elastic.ProductBasket;
import com.example.shop.repository.elastic.BasketRepository;
import com.example.shop.service.BasketService;
import com.example.shop.service.ProductService;
import com.example.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final UserService userService;
    private final ProductService productService;

    @Override
    public void addToBasket(Long productId, Integer productQuantity) {
        User currentUser = userService.getCurrentUser();
        Product product = productService.getProductById(productId);

        Basket basket;
        try {
            basket = getUserBasket();
        } catch(EntityNotFoundException e){
            basket = new Basket();
            basket.setProducts(new HashSet<>());
            basket.setEmail(currentUser.getEmail());
        }
        basket.getProducts().removeIf(productBasket -> productBasket.getId().equals(productId));
        ProductBasket productBasket = new ProductBasket(product.getName(), product.getId(), null, product.getPrice());
        if(product.getQuantity() >= productQuantity)
            productBasket.setQuantity(productQuantity);
        else
            productBasket.setQuantity(product.getQuantity());

        basket.getProducts().add(productBasket);
        basketRepository.save(basket);


    }

    @Override
    public Basket getUserBasket() {
        return basketRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new EntityNotFoundException("Basket doesn't exist"));
    }

    @Override
    public void deleteProductById(Long productId) {
        Basket basket = getUserBasket();
        basket.getProducts().removeIf(productBasket -> productBasket.getId().equals(productId));
        basketRepository.save(basket);
    }

    @Override
    public void update(Basket userBasket) {
        basketRepository.save(userBasket);
    }
}
