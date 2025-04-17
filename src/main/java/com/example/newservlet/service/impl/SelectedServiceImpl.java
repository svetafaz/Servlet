package com.example.newservlet.service.impl;
import com.example.newservlet.dto.response.ListBooksResponse;
import com.example.newservlet.model.BookEntity;
import com.example.newservlet.repository.SelectedRepository;
import com.example.newservlet.service.SelectedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.example.newservlet.mapper.BookMapper;
import java.util.Collections;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class SelectedServiceImpl implements SelectedService {

    private final SelectedRepository selectedRepository;

    private final BookMapper bookMapper;

    @Override
    public ListBooksResponse getAllSelected(Long readerId) {
        List<BookEntity> books = selectedRepository.getSelectedByReader(readerId);
        if (books.isEmpty()) {
            return new ListBooksResponse(Collections.emptyList());
        }
        return bookMapper.toDto(books);
    }

    @Override
    public void deleteSelected(Long readerId, Long bookId) {
        selectedRepository.removeFromSelected(readerId, bookId);
    }

    @Override
    public void addSelected(Long readerId, Long bookId) {
        selectedRepository.addToSelected(readerId, bookId);
    }
}