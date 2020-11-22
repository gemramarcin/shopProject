package com.example.shop.repository.jpa;


import com.example.shop.domain.dao.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    List<Orders> findAllByOrderNumber(String orderNumber);
}
