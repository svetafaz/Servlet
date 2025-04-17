package com.example.newservlet.mapper.impl;

import com.example.newservlet.dto.request.OrderRequest;
import com.example.newservlet.mapper.OrderMapper;
import com.example.newservlet.model.OrdersEntity;


import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapperImpl implements OrderMapper {
    @Override
    public OrdersEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return OrdersEntity.builder()
                .id(rs.getLong("id"))
                .readerId(rs.getLong("reader_id"))
                .bookId(rs.getLong("product_id"))
                .orderDate(rs.getTimestamp("order_date").toLocalDateTime())
                .statusCode(rs.getString("status_code"))
                .build();
    }

    @Override
    public OrdersEntity toEntity(OrderRequest request) {
        return OrdersEntity.builder()
                .readerId(request.getReaderId())
                .bookId(request.getBookId())
                .orderDate(request.getOrderDate())
                .statusCode(request.getStatusCode())
                .build();
    }
}

