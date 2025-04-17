package com.example.newservlet.repository;


import com.example.newservlet.model.BookEntity;

import java.util.List;

public interface SelectedRepository {

    void addToSelected(Long readerId, Long bookId);

    void removeFromSelected(Long readerId, Long bookId);

    List<BookEntity> getSelectedByReader(Long readerId);

    boolean isBookInSelected(Long userId, Long bookId);
}

