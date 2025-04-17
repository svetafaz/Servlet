package com.example.newservlet.dto.response;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ListBooksResponse {
    List<BookResponse> books;
}
