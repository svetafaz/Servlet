package com.example.newservlet.service;

import com.example.newservlet.model.OrderEntity;

import java.util.List;

public interface OrderService {

    void createOrder(OrderEntity order);

    List<OrderEntity> getOrdersByUserId(Long userId);

    void updateOrderStatus(Long orderId, String statusCode);

    void deleteOrder(Long orderId);
}

