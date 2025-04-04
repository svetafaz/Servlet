package com.example.newservlet.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Data
public class BookEntity {
   long id;
    String name;
    String writer;
     int price;
    byte[] image;
    int quantity;
    private List<CategoryEntity> categories;
}
