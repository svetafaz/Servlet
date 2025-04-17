package com.example.newservlet.service;

import com.example.newservlet.dto.request.SignInRequest;
import com.example.newservlet.dto.request.SignUpRequest;
import com.example.newservlet.dto.response.AuthResponse;
import com.example.newservlet.dto.response.ReaderDataResponse;

public interface ReaderService {
    AuthResponse signUp(SignUpRequest request);
    AuthResponse signIn(SignInRequest request);
    AuthResponse checkAdmin(ReaderDataResponse reader);
}