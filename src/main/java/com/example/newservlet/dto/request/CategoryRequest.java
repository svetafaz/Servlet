package com.example.newservlet.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Data
public class CategoryRequest {
    private String name;
}
