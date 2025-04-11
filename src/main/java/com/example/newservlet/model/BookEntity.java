package com.example.newservlet.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Data
public class BookEntity {
    private long id;
    private String name;
    private String writer;
    private int price;
    private byte[] image;
    private int quantity;
    private List<CategoryEntity> categories;
}
