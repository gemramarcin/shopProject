package com.example.shop.controller;

import com.example.shop.domain.dao.Orders;
import com.example.shop.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class OrdersController {

    private final OrdersService ordersService;

    @PostMapping
    public void createOrder() {
        ordersService.createOrder();
    }

    @GetMapping("{orderNumber}")
    public List<Orders> getOrdersDetails(@PathVariable String orderNumber) {
        return ordersService.getOrderDetails(orderNumber);
    }
}
