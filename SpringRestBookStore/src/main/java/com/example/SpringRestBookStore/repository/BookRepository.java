package com.example.SpringRestBookStore.repository;

import com.example.SpringRestBookStore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
