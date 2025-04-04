package com.example.newservlet.dto.response;
import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReaderDataResponse {
    private Long id;
    private String email;
    private String username;
    private String role;
}
