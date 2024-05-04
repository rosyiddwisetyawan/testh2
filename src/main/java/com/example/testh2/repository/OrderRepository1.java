package com.example.testh2.repository;

import com.example.testh2.model.Order;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderRepository1 extends PagingAndSortingRepository<Order, Long> {
}