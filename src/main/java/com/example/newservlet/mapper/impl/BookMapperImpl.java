package com.example.newservlet.mapper.impl;

import com.example.newservlet.dto.request.NewBookRequest;
import com.example.newservlet.dto.response.BookResponse;
import com.example.newservlet.dto.response.ListBooksResponse;
import com.example.newservlet.model.BookEntity;
import com.example.newservlet.mapper.BookMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class BookMapperImpl implements BookMapper {

    @Override
    public BookEntity toEntity(NewBookRequest request) {
        return BookEntity.builder()
                .name(request.getName())
                .writer(request.getWriter())
                .price(request.getPrice())
                .image(request.getImage())
                .quantity(request.getQuantity())
                .build();
    }
    @Override
public ListBooksResponse toDto(List<BookEntity> entity) {
    List<BookResponse> bookResponses = entity.stream()
            .map(book -> BookResponse.builder()
                    .id(book.getId())
                    .name(book.getName())
                    .writer(book.getWriter())
                    .price(book.getPrice())
                    .quantity(book.getQuantity())
                    .price(book.getPrice())
                    .build())
            .collect(Collectors.toList());
    return ListBooksResponse.builder()
            .books(bookResponses)
            .build();
}

    @Override
    public BookEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return BookEntity.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .writer(rs.getString("writer"))
                .price(rs.getInt("price"))
                .image(rs.getBytes("image"))
                .build();
    }
}
