package com.example.libraryservicemanagement.controller;

import com.example.libraryservicemanagement.model.Book;
import com.example.libraryservicemanagement.model.BookDTO;
import com.example.libraryservicemanagement.model.LibraryUser;
import com.example.libraryservicemanagement.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class LibraryUserController {

    private final LibraryService libraryService;

    @Autowired
    public LibraryUserController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @PostMapping(path = "/borrowbook")
    public ResponseEntity<LibraryUser> borrowBooks(@Valid @RequestBody LibraryUser libraryUser){
        LibraryUser stud = libraryService.borrowBook(libraryUser);
        return new ResponseEntity<>(stud, HttpStatus.CREATED);
    }

    @PutMapping(path = "/returnbook")
    public ResponseEntity<LibraryUser> returnBooks(@Valid @RequestBody LibraryUser libraryUser){
        LibraryUser stud = libraryService.returnBook(libraryUser);
        return new ResponseEntity<>(stud, HttpStatus.ACCEPTED);
    }

    @PostMapping(path = "/createBooks")
    public ResponseEntity<List<BookDTO>> createBooks(@Valid @RequestBody List<Book> bookList){
        return new ResponseEntity<>(libraryService.createBook(bookList), HttpStatus.CREATED);
    }

    @DeleteMapping (path = "/removeBooks")
    public ResponseEntity<List<BookDTO>> removeBooks(@Valid @RequestBody List<Book> bookList){
        return new ResponseEntity<>(libraryService.removeBook(bookList), HttpStatus.ACCEPTED);
    }
}
