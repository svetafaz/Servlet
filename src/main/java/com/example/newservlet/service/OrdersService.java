package com.example.newservlet.service;

import com.example.newservlet.model.OrdersEntity;

import java.util.List;

public interface OrdersService {

    void createOrder(OrdersEntity order);

    List<OrdersEntity> getOrdersByReaderId(Long readerId);

    void updateOrderStatus(Long orderId, String statusCode);

    void deleteOrder(Long orderId);
}

