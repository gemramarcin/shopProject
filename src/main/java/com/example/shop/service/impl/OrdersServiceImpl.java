package com.example.shop.service.impl;

import com.example.shop.domain.dao.Orders;
import com.example.shop.domain.dao.Product;
import com.example.shop.domain.dao.User;
import com.example.shop.domain.elastic.Basket;
import com.example.shop.domain.elastic.ProductBasket;
import com.example.shop.repository.jpa.OrdersRepository;
import com.example.shop.service.BasketService;
import com.example.shop.service.OrdersService;
import com.example.shop.service.ProductService;
import com.example.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;
    private final BasketService basketService;
    private final ProductService productService;
    private final UserService userService;

    @Override
    public List<Orders> createOrder() {
        User currentUser = userService.getCurrentUser();


        Basket userBasket = basketService.getUserBasket();
        Map<Long, ProductBasket> productBasketMap = userBasket.getProducts().stream()
                .collect(Collectors.toMap(ProductBasket::getId, Function.identity()));

        List<Product> products = productService.getProductsByIds(productBasketMap.keySet());

        String orderNumbers = UUID.randomUUID().toString();

        List<Orders> orders = new LinkedList<>();

        products.forEach(product -> {
            ProductBasket productBasket = productBasketMap.get(product.getId());
            if (productBasket.getQuantity() <= product.getQuantity())
                orders.add(Orders.builder()
                        .quantity(productBasket.getQuantity())
                        .user(currentUser)
                        .product(product)
                        .orderNumber(orderNumbers)
                        .build());

            else
                orders.add(Orders.builder()
                        .quantity(product.getQuantity())
                        .user(currentUser)
                        .product(product)
                        .orderNumber(orderNumbers)
                        .build());

        });

        userBasket.setProducts(Collections.emptySet());
        basketService.update(userBasket);

        return ordersRepository.saveAll(orders);
    }

    @Override
    public void getOrders() {

    }

    @Override
    public List<Orders> getOrderDetails(String orderNumber) {

        //findBy
        return ordersRepository.findAllByOrderNumber(orderNumber);

    }
}
