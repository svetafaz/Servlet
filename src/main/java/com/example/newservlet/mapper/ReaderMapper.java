package com.example.newservlet.mapper;

import com.example.newservlet.dto.request.SignUpRequest;
import com.example.newservlet.dto.response.ReaderDataResponse;
import com.example.newservlet.model.ReaderEntity;
import org.springframework.jdbc.core.RowMapper;


public interface ReaderMapper extends RowMapper<ReaderEntity> {
    ReaderEntity toEntity(SignUpRequest request);
    ReaderDataResponse toDto(ReaderEntity entity);
}
