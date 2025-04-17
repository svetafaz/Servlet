package com.example.newservlet.repository;

import com.example.newservlet.dto.request.CategoryRequest;
import com.example.newservlet.model.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    List<BookEntity> getAllBooks(long readerId);

    List<BookEntity> getAllBooks(Long readerId);

    Optional<BookEntity> findBookById(Long id);
    Optional<BookEntity> saveNewBook(BookEntity book, List<CategoryRequest>category);
}
