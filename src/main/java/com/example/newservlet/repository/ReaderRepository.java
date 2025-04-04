package com.example.newservlet.repository;

import com.example.newservlet.model.ReaderEntity;

import java.util.Optional;

public interface ReaderRepository {
    Optional<ReaderEntity> findReaderById(Long id);
    Optional<ReaderEntity> findReaderByEmail(String email);
    Optional<ReaderEntity> findReaderByUsername(String username);
    Optional<ReaderEntity> saveNewReader(ReaderEntity reader);
}
