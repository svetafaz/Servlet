package com.example.newservlet.repository;

import com.example.newservlet.dto.request.CategoryRequest;
import com.example.newservlet.model.BookEntity;
import com.example.newservlet.model.CategoryEntity;

import java.util.Optional;
import java.util.List;

public interface CategoryRepository {
    Integer addCategoryAndGetID(String categoryName);
    Optional<Integer> findCategoryByName(String categoryName);
    List<CategoryEntity> findCategoriesByBookId(Long bookId);

    void saveBookCategories(Long bookId, List<CategoryRequest>categories);
    Optional<CategoryEntity> findCategoryById(Long id);
    List<CategoryEntity> getAllCategories();
}
