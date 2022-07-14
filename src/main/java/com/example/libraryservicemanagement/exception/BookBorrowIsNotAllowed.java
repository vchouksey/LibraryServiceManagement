package com.example.libraryservicemanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BookBorrowIsNotAllowed extends RuntimeException{

    public BookBorrowIsNotAllowed(String message) {
        super(message);
    }
}
