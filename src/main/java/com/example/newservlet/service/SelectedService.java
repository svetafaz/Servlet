package com.example.newservlet.service;

import com.example.newservlet.dto.response.ListBooksResponse;

public interface SelectedService {
    ListBooksResponse getAllSelected(Long readerId);

    void deleteSelected(Long readerId, Long bookId);

    void addSelected(Long readerId, Long bookId);
}