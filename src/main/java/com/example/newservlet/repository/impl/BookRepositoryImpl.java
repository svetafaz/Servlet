package com.example.newservlet.repository.impl;
import com.example.newservlet.dto.request.CategoryRequest;
import com.example.newservlet.mapper.BookMapper;
import com.example.newservlet.model.BookEntity;
import com.example.newservlet.repository.BookRepository;
import com.example.newservlet.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.util.Objects;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {
    private final JdbcTemplate jdbcTemplate;
    private static final String SQL_SELECT_BY_ID = "select * from books where id = ?";
    private static final String SQL_SELECT_ALL_BOOKS = "select * from books";
    private static final String SQL_INSERT_BOOKS = "insert into books (name,writer,price,quantity,image) values (?,?,?,?,?);";
    private final CategoryRepository categoryRepository;
    private final BookMapper bookMapper;

    @Override
    public List<BookEntity> getAllBooks() {
        List<BookEntity> books = jdbcTemplate.query(SQL_SELECT_ALL_BOOKS, bookMapper);
        for (BookEntity book : books) {
            book.setCategories(categoryRepository.findCategoriesByBookId(book.getId()));
        }
        return books;
    }
    @Override
    public Optional<BookEntity> findBookById(Long id){
        try{
            BookEntity book = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID,bookMapper,id);
            if (book != null) {
                book.setCategories(categoryRepository.findCategoriesByBookId(id));
            }
            return Optional.ofNullable(book);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<BookEntity> saveNewBook(BookEntity book, List<CategoryRequest> category) {
        try {
            KeyHolder holder = new GeneratedKeyHolder();
          jdbcTemplate.update(con ->  {
                PreparedStatement ps = con.prepareStatement(SQL_INSERT_BOOKS, new String[]{"id"});
                ps.setString(1, book.getName());
                ps.setString(2, book.getWriter());
                ps.setDouble(3, book.getPrice());
                ps.setInt(4, book.getQuantity());
                ps.setBytes(5, book.getImage());
                return ps;
            }, holder);
            Long id = Objects.requireNonNull(holder.getKey()).longValue();
            if (book.getCategories() == null) {
                categoryRepository.saveBookCategories(id, category);
            }
            return findBookById(id);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}