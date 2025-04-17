package com.example.newservlet.repository;

import com.example.newservlet.model.OrdersEntity;

import java.util.List;

public interface OrdersRepository {
    void save(OrdersEntity order);
    List<OrdersEntity> findAllByReaderId(Long readerId);
    void updateStatus(Long orderId, String statusCode);
    void delete(Long orderId);
}
