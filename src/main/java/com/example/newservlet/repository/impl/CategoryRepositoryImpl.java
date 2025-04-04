package com.example.newservlet.repository.impl;
import com.example.newservlet.dto.request.CategoryRequest;
import com.example.newservlet.mapper.CategoryMapper;
import com.example.newservlet.model.CategoryEntity;
import com.example.newservlet.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.sql.PreparedStatement;
@Slf4j
@RequiredArgsConstructor

public class CategoryRepositoryImpl implements CategoryRepository {
    private final JdbcTemplate jdbcTemplate;
    private static final String SQL_INSERT_CATEGORY = "insert into category(name) values(?)";
    private static final String SQL_SELECT_BY_ID = "select * from category where id = ?";
    private static final String SQL_CATEGORY_BY_NAME = "select *from category where name =?";
    private static final String SQL_INSERT_BOOK_CATEGORY = "insert into book_category(book id, category id) values (?,?)";
    private static final String SQL_SELECT_ALL_CATEGORY = "select* from category";
    private static final String SQL_SELECT_CATEGORIES_BY_BOOK_ID =
            "select c.id,c.name FROM category c" +
                    "join book_category bc on c.id= bc.category_id" +
                    "where bc.book_id=?";
    private final CategoryMapper categoryMapper;

    @Override
    public Integer addCategoryAndGetID(String categoryName) {
        KeyHolder keyHolder=new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(
                    SQL_INSERT_CATEGORY, new String[]{"id"});
            ps.setString(1, categoryName);
            return ps;
        },keyHolder);
        return Objects.requireNonNull(KeyHolder.getKey()).intValue();
          }

@Override
    public Optional<Integer>findCategoryByName(String categoryName) {
    return jdbcTemplate.query(SQL_CATEGORY_BY_NAME,
            (rs, rowNum) -> rs.getInt("id"), categoryName).stream().findFirst();
}
@Override
    public List<CategoryEntity> findCategoriesByBookId(Long bookId) {
    return jdbcTemplate.query(SQL_SELECT_CATEGORIES_BY_BOOK_ID, categoryMapper, bookId);
}

@Override
    public void saveBookCategories(Long bookId, List<CategoryRequest> categories){
        categories.forEach(category -> {
            Integer categoryId = findCategoryByName(category.getName())
                    .orElseGet(() -> addCategoryAndGetID(category.getName()));
            jdbcTemplate.update(SQL_INSERT_BOOK_CATEGORY,
                    bookId, categoryId);
        });
    }

@Override
    public Optional<CategoryEntity> findCategoryById(Long id) {
    try {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, categoryMapper, id));
    } catch (EmptyResultDataAccessException e) {
        return Optional.empty();
    }
}
    @Override
    public List<CategoryEntity> getAllCategories() {
        return jdbcTemplate.query(SQL_SELECT_ALL_CATEGORY,categoryMapper);
    }

}

