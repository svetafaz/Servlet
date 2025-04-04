package com.example.newservlet.model;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Builder
public class ReaderEntity {
   public final static String READER_ROLE = "reader";
   public final static String ADMIN_ROLE = "admin";

   private Long id;
   private String  username;
   private String hashPassword;
    private String email;
    private String role;
}
