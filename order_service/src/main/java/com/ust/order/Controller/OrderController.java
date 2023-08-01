package com.ust.order.Controller;
import com.ust.order.Model.Order;
import com.ust.order.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order savedOrder = orderService.createOrder(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    @GetMapping("/{userid}")
    public ResponseEntity<List<Order>> getUser(@PathVariable("userid") Integer userid) {
        List<Order> orders = orderService.findByUserId(userid).orElse(null);
        if (orders != null) {
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{userid}")
    public ResponseEntity<Order> updateUser(@PathVariable("userid") Integer userid, @RequestBody Order order) {
        Order updatedOrder = orderService.updateOrder(userid, order);
        if (updatedOrder != null) {
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userid}/{orderid}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userid") Integer userid, @PathVariable("orderid") Integer orderid) {
        boolean deleted = orderService.deleteOrder(userid, orderid);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        List<Order> orders = orderService.findAllOrders();
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }



}
