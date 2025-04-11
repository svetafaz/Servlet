package com.example.newservlet.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private Long readerId;

    private Long bookId;

    private LocalDateTime orderDate;

    private String statusCode;
}

