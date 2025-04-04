package com.example.newservlet.mapper;
import com.example.newservlet.dto.response.ListCategoriesResponse;
import com.example.newservlet.model.CategoryEntity;
import org.springframework.jdbc.core.RowMapper;
import java.util.List;

public interface CategoryMapper extends RowMapper<CategoryEntity>{
    ListCategoriesResponse toDto(List<CategoryEntity> entity);
}
