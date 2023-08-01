package com.ust.order.Service;

import com.ust.order.Model.Order;
import com.ust.order.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private  OrderRepository orderRepository;


    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Optional<List<Order>> findByUserId(Integer userId) {
        return orderRepository.findByUserid(userId);
    }

    public Order updateOrder(Integer userId, Order order) {
        if (orderRepository.existsByUserid(userId)) {
            order.setUserid(userId);
            return orderRepository.save(order);
        } else {
            return null;
        }
    }

    public  boolean deleteOrder(Integer userId, Integer orderId) {
        if (orderRepository.existsByUserid(userId)) {
            Optional<List<Order>> cartsOptional = orderRepository.findByUserid(userId);
            List<Order> orders = cartsOptional.orElse(null);
            if (orders != null) {
                for (Order order : orders) {
                    if (order.getOrderid() == orderId) {
                        orderRepository.deleteById(orderId);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

}
