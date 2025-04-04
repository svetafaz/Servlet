package com.example.newservlet.mapper;

import com.example.newservlet.dto.request.NewBookRequest;
import com.example.newservlet.dto.response.ListBooksResponse;
import com.example.newservlet.model.BookEntity;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public interface BookMapper extends RowMapper<BookEntity> {
    BookEntity toEntity(NewBookRequest request);
    ListBooksResponse toDto (List<BookEntity> entity);
}
