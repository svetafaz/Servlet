package com.example.newservlet.mapper;

import com.example.newservlet.dto.request.OrderRequest;
import com.example.newservlet.model.OrdersEntity;
import org.springframework.jdbc.core.RowMapper;

    public interface OrderMapper extends RowMapper<OrdersEntity> {

        OrdersEntity toEntity(OrderRequest request);
    }

