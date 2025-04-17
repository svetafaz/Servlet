package com.example.newservlet.repository.impl;
import com.example.newservlet.mapper.BookMapper;
import com.example.newservlet.model.BookEntity;
import com.example.newservlet.repository.SelectedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import com.example.newservlet.repository.CategoryRepository;


import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class SelectedRepositoryImpl implements SelectedRepository {

    private final JdbcTemplate jdbcTemplate;

    private final static String INSERT_INTO_SELECTED = "insert into selected (reader_id, book_id) values (?, ?)";

    private final static String SELECT_FROM_SELECTED = "select * from selected";

    private final static String DELETE_FROM_SELECTED = "delete from selected WHERE reader_id = ? and book_id = ?";

    private final static String SELECT_FROM_SELECTED_BY_ID_READER = "select b.id, b.name, b.writer, b.price, b.image, b.quantity " +
            "from books b " +
            "join favourites f ON b.id = f.book_id " +
            "where f.reader_id = ?";

    private final static String SELECT_IS_BOOK_IN_SELECTED = "select count(*) from selected where reader_id = ? and book_id = ?";

    private final BookMapper bookMapper;

    private final CategoryRepository categoryRepository;


    @Override
    public void addToSelected(Long readerId, Long bookId) {
        if (!isBookInSelected(readerId, bookId)) {
            jdbcTemplate.update(INSERT_INTO_SELECTED, readerId, bookId);
        }
    }

    @Override
    public void removeFromSelected(Long readerId, Long bookId) {
        jdbcTemplate.update(DELETE_FROM_SELECTED, readerId, bookId);
    }

    @Override
    public List<BookEntity> getSelectedByReader(Long readerId) {
        List<BookEntity> books = jdbcTemplate.query(SELECT_FROM_SELECTED_BY_ID_READER, bookMapper, readerId);
        for (BookEntity book : books) {
            book.setCategories(categoryRepository.findCategoriesByBookId(book.getId()));
            book.setSelected(isBookInSelected(readerId, book.getId()));
        }
        return books;
    }

    @Override
    public boolean isBookInSelected(Long readerId, Long bookId) {
        Integer count = jdbcTemplate.queryForObject(SELECT_IS_BOOK_IN_SELECTED, new Object[]{readerId, bookId}, Integer.class);
        return count > 0;
    }
}