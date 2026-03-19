package com.example.SpringRestBookStore.controller;

import com.example.SpringRestBookStore.dto.BookDto;
import com.example.SpringRestBookStore.dto.PageResponse;
import com.example.SpringRestBookStore.entity.Book;
import com.example.SpringRestBookStore.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @PostMapping("/addBook")
    public ResponseEntity<BookDto> createBook(@RequestBody @Valid BookDto bookDto){
        BookDto createdBook=bookService.createBook(bookDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }
    @PutMapping("/updateBook/{id}")
    @Operation(summary = "update book", description = "updates on existing book by id")
    @ApiResponse(responseCode = "200", description = "Book updated successfully")
    public ResponseEntity<BookDto> updateBook(@PathVariable("id") Long id, @RequestBody  @Valid BookDto bookDto){
        BookDto updatedBook=bookService.updateBook(id, bookDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedBook);
    }

    @GetMapping("/getBook/{id}")
    @Operation(summary = "Delete book", description = "Deletes a book by id")
    @ApiResponse(responseCode = "200", description = "Book deleted successfully")
    public ResponseEntity<BookDto> getBook(@PathVariable("id") Long id){
        BookDto getBook=bookService.getBook(id);
        return ResponseEntity.status(HttpStatus.PROCESSING).body(getBook);
    }

    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<BookDto> deleteBook(@PathVariable("id") Long id){
        BookDto deleteBook=bookService.deleteBook(id);
        return ResponseEntity.ok(deleteBook);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<BookDto>> getAllBooks(){
        List<BookDto> all=bookService.getAllBooks();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/getAllCache")
    public ResponseEntity<PageResponse<BookDto>> getBooks(
           @Parameter(description = "give page no u want but defaukt is 0") @RequestParam(defaultValue = "0") int page,
           @Parameter(description = "give size in each page but deafult is 2") @RequestParam(defaultValue = "2") int size,
           @Parameter(description = "give by which u want to sort but default is id") @RequestParam(defaultValue = "id") String sortBy,
           @Parameter(description = "do you want asc or desc but defautl is asc") @RequestParam(defaultValue = "asc") String direction
    ){
        PageResponse<BookDto> books=bookService.getBooks(page, size, sortBy, direction);
        return ResponseEntity.ok(books);
    }
}
