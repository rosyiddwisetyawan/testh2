package com.example.testh2.repository;

import com.example.testh2.model.Item;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ItemRepository1 extends PagingAndSortingRepository<Item, Long> {
}