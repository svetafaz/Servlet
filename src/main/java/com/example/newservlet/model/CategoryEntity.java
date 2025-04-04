package com.example.newservlet.model;
import lombok.*;
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {
    private Long id;
    private String name;
}
