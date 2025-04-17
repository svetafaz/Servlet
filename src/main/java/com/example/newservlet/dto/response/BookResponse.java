package com.example.newservlet.dto.response;
import com.example.newservlet.model.CategoryEntity;
import lombok.*;

import java.util.List;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {
    private Long id;
    private String name;
    private String writer;
    private double price;
    private String image;
    private int quantity;
    private List<CategoryEntity> category;
    private boolean isSelected;
}