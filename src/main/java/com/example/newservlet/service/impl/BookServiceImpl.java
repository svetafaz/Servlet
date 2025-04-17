package com.example.newservlet.service.impl;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import com.example.newservlet.dto.request.CategoryRequest;
import com.example.newservlet.dto.request.NewBookRequest;
import com.example.newservlet.dto.response.ListBooksResponse;
import com.example.newservlet.mapper.BookMapper;
import com.example.newservlet.model.BookEntity;
import com.example.newservlet.repository.BookRepository;
import com.example.newservlet.service.BookService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Slf4j
@RequiredArgsConstructor

public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public ListBooksResponse getAllBooks() {
        return null;
    }

    @Override
    public ListBooksResponse getAllBooks(Long readerId){
        List<BookEntity> books = bookRepository.getAllBooks(readerId);
        log.info("Get all books");
        if (books.isEmpty()) {
            return new ListBooksResponse(Collections.emptyList());
        }
        return bookMapper.toDto(books);
        }
       @Override
    public void saveNewBook(NewBookRequest request, List<CategoryRequest>requestList){
           Optional<BookEntity> optionalBook=bookRepository.saveNewBook(bookMapper.toEntity(request),requestList);

          }
}
