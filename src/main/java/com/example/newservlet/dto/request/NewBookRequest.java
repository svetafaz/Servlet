package com.example.newservlet.dto.request;
import lombok.*;
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NewBookRequest {
    private String name;
    private String writer;
    private double price;
    private byte[] image;
    private int quantity;
    }


