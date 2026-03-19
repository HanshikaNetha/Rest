package com.example.SpringRestBookStore.dto;

import com.example.SpringRestBookStore.entity.Book;
import lombok.Data;

import java.util.List;

@Data
public class UserResponseDto {
    private String username;
    private String email;
    private List<Book> books;
}
