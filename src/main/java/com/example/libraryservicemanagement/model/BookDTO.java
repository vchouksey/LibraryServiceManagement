package com.example.libraryservicemanagement.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@RequiredArgsConstructor
public class BookDTO {
    @NotBlank(message = "author must not be blank")
    private String author;
    @NotBlank(message = "book title must not be blank")
    private String title;
    @NotBlank(message = "book category must not be blank")
    private String category;
}
