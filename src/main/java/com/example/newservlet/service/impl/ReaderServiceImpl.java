package com.example.newservlet.service.impl;
import com.example.newservlet.dto.request.SignInRequest;
import com.example.newservlet.dto.response.ReaderDataResponse;

import com.example.newservlet.model.ReaderEntity;
import com.example.newservlet.utils.AuthUtils;
import lombok.*;
import com.example.newservlet.dto.request.SignUpRequest;
import com.example.newservlet.dto.response.AuthResponse;
import com.example.newservlet.mapper.ReaderMapper;
import com.example.newservlet.repository.ReaderRepository;
import com.example.newservlet.service.ReaderService;
import lombok.extern.slf4j.Slf4j;
import java.util.Optional;

import static com.example.newservlet.model.ReaderEntity.ADMIN_ROLE;


@Slf4j
@RequiredArgsConstructor
public class ReaderServiceImpl implements ReaderService {
    private final ReaderRepository readerRepository;
    private final ReaderMapper readerMapper;

    @Override
    public AuthResponse signUp(SignUpRequest request) {
        if (request.getEmail() == null || request.getEmail().isBlank())
            return response(1, "Empty email", null);

        if (request.getPassword() == null || request.getPassword().isBlank())
            return response(2, "Empty password", null);

        if (request.getUsername() == null || request.getUsername().isBlank())
            return response(3, "Empty username", null);

        if (!AuthUtils.checkEmail(request.getEmail()))
            return response(4, "Invalid email", null);

        if (readerRepository.findReaderByEmail(request.getEmail()).isPresent())
            return response(6, "Email taken", null);

        if (readerRepository.findReaderByUsername(request.getUsername()).isPresent())
            return response(7, "Nickname taken", null);

        Optional<ReaderEntity> optionalReader = readerRepository.saveNewReader(readerMapper.toEntity(request));
        if (optionalReader.isEmpty())
            return response(50, "Database process error", null);
        return response(0, "Ok", readerMapper.toDto(optionalReader.get()));
    }

    @Override
    public AuthResponse signIn(SignInRequest request) {
        if (request.getEmail() == null || request.getEmail().isBlank())
            return response(1, "Empty email", null);

        if (request.getPassword() == null || request.getPassword().isBlank())
            return response(2, "Empty password", null);

        if (!AuthUtils.checkEmail(request.getEmail()))
            return response(4, "Invalid email", null);
        Optional<ReaderEntity> optionalReader = readerRepository.findReaderByEmail(request.getEmail());
        if (optionalReader.isEmpty())
            return response(8, "Email not found", null);
        ReaderEntity reader = optionalReader.get();
        if (!AuthUtils.verifyPassword(request.getPassword(), reader.getHashPassword()))
            return response(9, "Password mismatch", null);
        return response(0, "Ok", readerMapper.toDto(reader));
    }

    private AuthResponse response(int status, String statusDesc, ReaderDataResponse reader) {
        return AuthResponse.builder()
                .status(status)
                .statusDesc(statusDesc)
                .reader(reader)
                .build();
    }

    @Override
    public AuthResponse checkAdmin(ReaderDataResponse reader) {
        if (reader.getRole().equals(ADMIN_ROLE)) {
            return response(0, "OK", reader);
        } else {
            return response(10, "You are not admin :(", null);
        }
    }
}