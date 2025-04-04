package com.example.newservlet.mapper.impl;

import com.example.newservlet.mapper.ReaderMapper;
import com.example.newservlet.model.ReaderEntity;
import lombok.extern.slf4j.Slf4j;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.example.newservlet.dto.request.SignInRequest;
import com.example.newservlet.dto.request.SignUpRequest;
import com.example.newservlet.utils.AuthUtils;
import com.example.newservlet.dto.response.ReaderDataResponse;

@Slf4j


public class ReaderMapperImpl implements ReaderMapper {

    @Override
    public ReaderEntity toEntity(SignUpRequest request) {
        return ReaderEntity.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .hashPassword(AuthUtils.hashPassword(request.getPassword()))
                .role(request.getRole())
                .build();
    }
        @Override
    public ReaderDataResponse toDto(ReaderEntity entity) {
            return ReaderDataResponse.builder()
                    .id(entity.getId())
                    .email(entity.getEmail())
                    .username(entity.getUsername())
                    .role(entity.getRole())
                    .build();
        }

        @Override
    public ReaderEntity mapRow(ResultSet rs,int rowNum) throws SQLException{
        return ReaderEntity.builder()
                .id(rs.getLong("id"))
                .email(rs.getString("email"))
                .username(rs.getString("username"))
                .hashPassword(rs.getString("hash_password"))
                .role(rs.getString("role"))
                .build();
        }








}
