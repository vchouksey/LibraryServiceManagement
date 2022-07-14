package com.example.libraryservicemanagement.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "author must not be blank")
    private String author;
    @NotBlank(message = "book title must not be blank")
    private String title;
    @NotBlank(message = "book category must not be blank")
    private String category;
    private String userName;
}
