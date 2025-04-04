package com.example.newservlet.repository;

import com.example.newservlet.dto.request.CategoryRequest;
import com.example.newservlet.model.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    List<BookEntity> getAllBooks(Long userId);
    Optional<BookEntity> findBookByName(String name);

    List<BookEntity>getAllBooks();

    Optional<BookEntity> saveNewBook(BookEntity book, List<CategoryRequest>category);
}
