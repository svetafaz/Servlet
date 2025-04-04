package com.example.newservlet.dto.response;

import java.util.List;
import lombok.*;
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ListCategoriesResponse {
    List<CategoryResponse> categories;
}
