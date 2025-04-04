package com.example.newservlet.mapper.impl;

import com.example.newservlet.dto.response.CategoryResponse;
import com.example.newservlet.dto.response.ListCategoriesResponse;
import com.example.newservlet.mapper.CategoryMapper;
import com.example.newservlet.model.CategoryEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;



public class CategoryMapperImpl implements CategoryMapper {
    @Override
    public CategoryEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return CategoryEntity.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .build();
    }

    @Override
    public ListCategoriesResponse toDto(List<CategoryEntity> entity) {
        List<CategoryResponse> categoryResponses = entity.stream()
                .map(category -> CategoryResponse.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .build())
                .collect(Collectors.toList());
        return ListCategoriesResponse.builder()
                .categories(categoryResponses)
                .build();
    }
}





