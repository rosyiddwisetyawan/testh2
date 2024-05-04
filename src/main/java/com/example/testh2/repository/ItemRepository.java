package com.example.testh2.repository;

import com.example.testh2.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {


    Optional<Item> findById(Long id);

    @Override
    boolean existsById(Long aLong);

}