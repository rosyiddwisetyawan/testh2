package com.example.testh2.controller;

import com.example.testh2.model.Item;
import com.example.testh2.model.ItemResponse;
import com.example.testh2.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService service;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    private ResponseEntity<?> save(@Valid @RequestBody Item itemRequest){

        Map<String, Object> map = new HashMap<String, Object>();
        Item item = service.save(itemRequest);
        if(item!=null){
            map.put("message","add data success");
            map.put("name",itemRequest.getName());
            map.put("status",200);
        }else{
            map.put("message","add data failed");
            map.put("name",itemRequest.getName());
            map.put("status",400);
        }
        return ResponseEntity.ok(map);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    private ResponseEntity<?> delete(@PathVariable String id){

        Map<String, Object> map = new HashMap<String, Object>();
        Boolean status = service.delete(id);
        if(status){
            map.put("message","delete data success");
            map.put("id",id);
            map.put("status",200);
        }else{
            map.put("message","delete data failed");
            map.put("id",id);
            map.put("status",400);
        }
        return ResponseEntity.ok(map);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    private ResponseEntity<?> update(@Valid @RequestBody Item itemRequest){

        Map<String, Object> map = new HashMap<String, Object>();
        Item item = service.update(itemRequest);
        if(item!=null){
            map.put("message","update data success");
            map.put("id",itemRequest.getId());
            map.put("status",200);
        }else{
            map.put("message","update data failed");
            map.put("id",itemRequest.getId());
            map.put("status",400);
        }
        return ResponseEntity.ok(map);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    private ItemResponse getById(@PathVariable String id){
        ItemResponse res = new ItemResponse();
        res.setStatus(400);
        Item item = service.getById(id);
        if(item!=null){
             res.setStatus(200);
             res.setItem(item);
        }
        return res;
    }

    @RequestMapping(value = "/get/{pageNo}/{pageSize}/{sortBy}", method = RequestMethod.GET)
    public ResponseEntity<List<Item>> getAllItem(@PathVariable int pageNo, @PathVariable int pageSize, @PathVariable String sortBy) {
        List<Item> list = service.getAllItem(pageNo, pageSize, sortBy);

        return new ResponseEntity<List<Item>>(list, new HttpHeaders(), HttpStatus.OK);
    }

}
