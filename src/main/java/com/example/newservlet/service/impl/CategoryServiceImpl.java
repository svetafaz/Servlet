package com.example.newservlet.service.impl;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import com.example.newservlet.dto.response.ListCategoriesResponse;
import com.example.newservlet.mapper.CategoryMapper;
import com.example.newservlet.model.CategoryEntity;
import com.example.newservlet.repository.CategoryRepository;
import com.example.newservlet.service.CategoryService;

import java.util.Collections;
import java.util.List;
@Slf4j
@RequiredArgsConstructor

public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public ListCategoriesResponse getAllCategories(){
        List<CategoryEntity> categories = categoryRepository.getAllCategories();
        if(categories.isEmpty()){
            return new ListCategoriesResponse(Collections.emptyList());
            }
        return categoryMapper.toDto(categories);
    }
}

