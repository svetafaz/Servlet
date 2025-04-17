package com.example.newservlet.service.impl;

import com.example.newservlet.model.OrderEntity;
import com.example.newservlet.repository.OrderRepository;
import com.example.newservlet.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public void createOrder(OrderEntity order) {
        orderRepository.save(order);
    }

    @Override
    public List<OrderEntity> getOrdersByUserId(Long userId) {
        return orderRepository.findAllByReaderId(userId);
    }

    @Override
    public void updateOrderStatus(Long orderId, String statusCode) {
        orderRepository.updateStatus(orderId, statusCode);
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.delete(orderId);
    }
}

