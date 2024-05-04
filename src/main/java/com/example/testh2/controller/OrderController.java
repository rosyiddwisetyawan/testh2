package com.example.testh2.controller;

import com.example.testh2.model.Order;
import com.example.testh2.model.OrderResponse;
import com.example.testh2.service.OrderService;
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
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService service;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    private ResponseEntity<?> save(@Valid @RequestBody Order request){

        Map<String, Object> map = new HashMap<String, Object>();
        Order order = service.save(request);
        if(order!=null){
            map.put("message","add data success");
            map.put("order_no",request.getOrderNo());
            map.put("status",200);
        }else{
            map.put("message","add data failed because itemid not valid");
            map.put("order_no",request.getOrderNo());
            map.put("status",400);
        }
        return ResponseEntity.ok(map);
    }

    @RequestMapping(value = "/delete/{orderNo}", method = RequestMethod.POST)
    private ResponseEntity<?> delete(@PathVariable String orderNo){

        Map<String, Object> map = new HashMap<String, Object>();
        Boolean status = service.delete(orderNo);
        if(status){
            map.put("message","delete data success");
            map.put("order_no",orderNo);
            map.put("status",200);
        }else{
            map.put("message","delete data failed");
            map.put("order_no",orderNo);
            map.put("status",400);
        }
        return ResponseEntity.ok(map);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    private ResponseEntity<?> update(@Valid @RequestBody Order request){

        Map<String, Object> map = new HashMap<String, Object>();
        Order order = service.update(request);
        if(order!=null){
            map.put("message","update data success");
            map.put("order_no",request.getOrderNo());
            map.put("status",200);
        }else{
            map.put("message","update data failed");
            map.put("order_no",request.getOrderNo());
            map.put("status",400);
        }
        return ResponseEntity.ok(map);
    }

    @RequestMapping(value = "/get/{orderNo}", method = RequestMethod.GET)
    private OrderResponse getById(@PathVariable String orderNo){
        OrderResponse res = new OrderResponse();
        res.setStatus(400);
        Order order = service.getByOrderNo(orderNo);
        if(order!=null){
            res.setStatus(200);
            res.setOrder(order);
        }
        return res;
    }

    @RequestMapping(value = "/get/{pageNo}/{pageSize}/{sortBy}", method = RequestMethod.GET)
    public ResponseEntity<List<Order>> getAllOrder(@PathVariable int pageNo, @PathVariable int pageSize, @PathVariable String sortBy) {
        List<Order> list = service.getAllOrder(pageNo, pageSize, sortBy);

        return new ResponseEntity<List<Order>>(list, new HttpHeaders(), HttpStatus.OK);
    }
}
