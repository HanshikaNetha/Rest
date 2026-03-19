package com.example.SpringRestBookStore.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class BookDto {
    @Schema(description = "Book title", example = "Spring Boot")
    @NotBlank(message = "title must not be blank")
    private String title;
    @Schema(description = "Book author", example = "Hanshika")
    @NotBlank(message = "somebody must have wrote the book, enter their bane")
    private String author;
    @Schema(description = "Book price", example = "500")
    @Positive(message = "should be greater than 0")
    @Min(value=100, message = "minimum price must be 100")
    private double price;
}
