package com.example.testh2.service;

import com.example.testh2.model.Item;
import com.example.testh2.repository.ItemRepository;
import com.example.testh2.repository.ItemRepository1;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemService {

    @Autowired
    private ItemRepository repository;
    @Autowired
    private ItemRepository1 repo;

    public Item save(Item request){
        return repository.save(request);
    }

    public Boolean delete(String id){
        Boolean status = repository.existsById(Long.parseLong(id));
        if(status) {
            repository.deleteById(Long.parseLong(id));
            return true;
        }
        return null;
    }

    public Item update(Item request){
        Boolean status = repository.existsById(request.getId());
        if(status) {
            Item item = new Item();
            item.setId(request.getId());
            item.setName(request.getName());
            item.setPrice(request.getPrice());
            return repository.save(item);
        }
        return null;
    }

    public Item getById(String id){
        Optional<Item> item = repository.findById(Long.parseLong(id));
        if(item.isPresent()) {
            Item it = item.get();
            return it;
        }
        return null;
    }

    public List<Item> getAllItem(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Item> pagedResult = repo.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Item>();
        }
    }
}
