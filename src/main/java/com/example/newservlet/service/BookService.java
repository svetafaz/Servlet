package com.example.newservlet.service;

import com.example.newservlet.dto.request.CategoryRequest;
import com.example.newservlet.dto.request.NewBookRequest;
import com.example.newservlet.dto.response.ListBooksResponse;

import java.util.List;

public interface BookService {
    ListBooksResponse getAllBooks();

    ListBooksResponse getAllBooks(Long readerId);

    void saveNewBook(NewBookRequest request, List<CategoryRequest> requestList);
}
