package com.example.newservlet.dto.response;
import lombok.*;
import com.example.newservlet.mapper.BookMapper;
import com.example.newservlet.mapper.impl.BookMapperImpl;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ListBooksResponse {
    List<BookResponse> books;
}
