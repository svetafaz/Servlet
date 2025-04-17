package com.example.newservlet.repository.impl;
import com.example.newservlet.mapper.ReaderMapper;
import com.example.newservlet.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import com.example.newservlet.model.ReaderEntity;
import java.sql.PreparedStatement;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor

public class ReaderRepositoryImpl implements ReaderRepository {
    private final JdbcTemplate jdbcTemplate;
    private static final String SQL_SELECT_BY_ID = "select * from readers where id =?";
    private static final String SQL_SELECT_BY_EMAIL = "select * from readers where email =?";
    private static final String SQL_SELECT_BY_USERNAME = "select * from readers where username=?";
    private static final String SQL_INSERT ="insert into readers(email,hash_password,username,role) values(?,?,?,?)";
    private final ReaderMapper readerRowMapper;

    @Override
public Optional<ReaderEntity> findReaderById(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, readerRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
               
        @Override

        public Optional<ReaderEntity> findReaderByEmail (String email){
            try {
                return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL, readerRowMapper, email));
            } catch (EmptyResultDataAccessException e) {
                return Optional.empty();
            }
        }
        @Override
        public Optional<ReaderEntity> findReaderByUsername (String username){
            try {
                return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_USERNAME, readerRowMapper, username));
            } catch (EmptyResultDataAccessException e) {
                return Optional.empty();
            }
        }
        @Override
        public Optional<ReaderEntity> saveNewReader (ReaderEntity reader){
            try {
                KeyHolder holder = new GeneratedKeyHolder();
                jdbcTemplate.update(con -> {
                    PreparedStatement ps = con.prepareStatement(SQL_INSERT, new String[]{"id"});
                    ps.setString(1, reader.getEmail());
                    ps.setString(2, reader.getHashPassword());
                    ps.setString(3, reader.getUsername());
                    ps.setString(4, reader.getRole());
                    return ps;
                }, holder);
                Long id = Objects.requireNonNull(holder.getKey()).longValue();
                return findReaderById(id);
            } catch (Exception e) {
                return Optional.empty();
            }
        }
    }

