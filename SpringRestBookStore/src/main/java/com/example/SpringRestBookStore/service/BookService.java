package com.example.SpringRestBookStore.service;

import com.example.SpringRestBookStore.dto.BookDto;
import com.example.SpringRestBookStore.dto.PageResponse;
import com.example.SpringRestBookStore.entity.Book;
import com.example.SpringRestBookStore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @CacheEvict(value = "books", allEntries = true)
    public BookDto createBook(BookDto bookDto){
        Book book=modelMapper.map(bookDto, Book.class);
        Book savedBook=bookRepository.save(book);
        return modelMapper.map(savedBook, BookDto.class);
    }

    public BookDto updateBook(Long id, BookDto bookDto){
        Book existingBook=bookRepository.findById(id).orElseThrow(()->new RuntimeException("Book id not found"));
        existingBook.setTitle(bookDto.getTitle());
        existingBook.setAuthor(bookDto.getAuthor());
        existingBook.setPrice(bookDto.getPrice());
        Book updatedBook=bookRepository.save(existingBook);
        return modelMapper.map(updatedBook, BookDto.class);
    }

    public BookDto getBook(Long id){
        Book getBook=bookRepository.findById(id).orElseThrow(()->new RuntimeException("Book id not found"));
        return modelMapper.map(getBook, BookDto.class);
    }

    public BookDto deleteBook(Long id){
        Book book=bookRepository.findById(id).orElseThrow(()->new RuntimeException("Book not found"));
        bookRepository.deleteById(book.getId());
        return modelMapper.map(book, BookDto.class);
    }

    public List<BookDto> getAllBooks(){
        List<Book> bookList=bookRepository.findAll();
        return bookList.stream().map(i->modelMapper.map(i, BookDto.class)).toList();
    }

    @Cacheable(value = "books", key = "#page + '_' + #size + '_' + #sortBy + '_' + #direction")
    public PageResponse<BookDto> getBooks(int page, int size, String sortBy, String direction){

        System.out.println("DB CALL HAPPENING!!!");
        Sort sort=direction.equalsIgnoreCase("desc") ?  Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable= PageRequest.of(page, size, sort);//pagination configuration
        Page<Book> bookPage=bookRepository.findAll(pageable);
        List<BookDto> dtoList=bookPage.getContent().stream().map(i->modelMapper.map(i, BookDto.class)).toList();
        return new PageResponse<>(dtoList, bookPage.getNumber(), bookPage.getSize(), bookPage.getTotalElements(), bookPage.getTotalPages());
    }
}
