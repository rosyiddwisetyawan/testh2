package com.example.testh2.repository;

import com.example.testh2.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {


    boolean existsByOrderNo(Long orderNo);

    long deleteByOrderNo(Long orderNo);

    Order findByOrderNo(Long orderNo);
}