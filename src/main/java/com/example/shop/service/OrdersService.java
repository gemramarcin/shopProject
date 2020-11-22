package com.example.shop.service;

import com.example.shop.domain.dao.Orders;

import java.util.List;

public interface OrdersService {

    List<Orders> createOrder();

    void getOrders();

    List<Orders> getOrderDetails(String orderNumber);


}
