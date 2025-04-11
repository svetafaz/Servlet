package com.example.newservlet.repository;

import com.example.newservlet.model.OrderEntity;

import java.util.List;

public interface OrderRepository {
    void save(OrderEntity order);
    List<OrderEntity> findAllByReaderId(Long readerId);
    void updateStatus(Long orderId, String statusCode);
    void delete(Long orderId);
}
