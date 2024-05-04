package com.example.testh2.service;

import com.example.testh2.model.Order;
import com.example.testh2.repository.ItemRepository;
import com.example.testh2.repository.OrderRepository;
import com.example.testh2.repository.OrderRepository1;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository repository;
    @Autowired
    private OrderRepository1 repo;
    @Autowired
    private ItemRepository itemRepository;

    public Order save(Order request){
        Boolean status = itemRepository.existsById(request.getItemId());
        if(status){
            return repository.save(request);
        }
        return null;
    }

    public Boolean delete(String orderNo){
        Boolean status = repository.existsByOrderNo(Long.parseLong(orderNo));
        if(status) {
            repository.deleteByOrderNo(Long.parseLong(orderNo));
            return true;
        }
        return null;
    }

    public Order update(Order request){
        Boolean status = repository.existsByOrderNo(request.getOrderNo());
        if(status) {
            Order order = new Order();
            order.setOrderNo(request.getOrderNo());
            order.setQty(request.getQty());
            order.setItemId(request.getItemId());
            return repository.save(order);
        }
        return null;
    }

    public Order getByOrderNo(String orderNo){
        Order order = repository.findByOrderNo(Long.parseLong(orderNo));
        if(order!=null) {
            return order;
        }
        return null;
    }

    public List<Order> getAllOrder(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Order> pagedResult = repo.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Order>();
        }
    }

}
