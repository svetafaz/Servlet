package com.example.newservlet.service.impl;

import com.example.newservlet.model.OrdersEntity;
import com.example.newservlet.repository.OrdersRepository;
import com.example.newservlet.service.OrdersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;

    @Override
    public void createOrder(OrdersEntity order) {
        ordersRepository.save(order);
    }

    @Override
    public List<OrdersEntity> getOrdersByReaderId(Long readerId) {
        return ordersRepository.findAllByReaderId(readerId);
    }

    @Override
    public void updateOrderStatus(Long orderId, String statusCode) {
        ordersRepository.updateStatus(orderId, statusCode);
    }

    @Override
    public void deleteOrder(Long orderId) {
        ordersRepository.delete(orderId);
    }
}

