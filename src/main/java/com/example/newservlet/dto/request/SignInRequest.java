package com.example.newservlet.dto.request;
import lombok.*;
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequest {
    private String email;
    private String password;
}
