package com.example.articlemangementsystem.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
public class NewsArticle {

    @NotNull(message = "id can't be null!")
    private int id;

    @NotEmpty(message = "title can't be empty!")
    @Size(max = 100, message = "Maximum length of title is 100 characters")
    private String title;

    @NotEmpty(message = "author can't be empty!")
    @Size(min = 4, max = 20, message = "author must be more than 4 characters and leas than 20!")
    private String author;

    @NotEmpty(message = "content can't be empty!")
    @Size(min = 200, message = "content must be more than 200 characters!")
    private String content;

    @NotEmpty(message = "category can't be empty!")
    @Pattern(regexp = "^(politics|sports|technology)$")
    private String category;

    @NotEmpty(message = "imageURL can't be empty!!")
    private String imageUrl;

    @AssertFalse(message = "isPublished must be false")
    private boolean isPublished;

    private LocalDateTime publishDate;

}
