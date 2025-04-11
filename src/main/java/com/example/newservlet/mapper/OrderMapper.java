package com.example.newservlet.mapper;

import com.example.newservlet.dto.request.OrderRequest;
import com.example.newservlet.model.OrderEntity;
import org.springframework.jdbc.core.RowMapper;

    public interface OrderMapper extends RowMapper<OrderEntity> {

        OrderEntity toEntity(OrderRequest request);
    }

