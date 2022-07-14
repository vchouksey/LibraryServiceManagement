package com.example.libraryservicemanagement.exception;

public class BookBorrowIsNotAllowed extends RuntimeException{

    public BookBorrowIsNotAllowed(String message) {
        super(message);
    }
}
