package com.example.SpringRestBookStore.dto;

import com.example.SpringRestBookStore.entity.Book;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private String username;
    private String email;
    private String password;
    private List<Book> books;
}
