package com.example.libraryservicemanagement.service;

import com.example.libraryservicemanagement.exception.BookBorrowIsNotAllowed;
import com.example.libraryservicemanagement.model.BookDTO;
import com.example.libraryservicemanagement.model.LibraryUser;
import com.example.libraryservicemanagement.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static com.example.libraryservicemanagement.TestHelper.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class LibraryServiceTest {

    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private LibraryService libraryService;

    @Test
    void borrowBook_whenValidRequest_shouldReturnValidResponse(){
        when(this.bookRepository.existsByTitle("Foundation of maths")).thenReturn(true);
        when(this.bookRepository.existsByTitle("best computers")).thenReturn(true);
        when(this.bookRepository.findByUserNameEquals("Vishal Chouksey")).thenReturn(actualBookList());
        LibraryUser libraryUser = this.libraryService.borrowBook(getLibraryUser("Vishal Chouksey", actualBookList()));
        Assertions.assertNotNull(libraryUser);
        Assertions.assertEquals("Vishal Chouksey", libraryUser.getUserName());
        Assertions.assertEquals("mohit jain", libraryUser.getBooks().get(0).getAuthor());
        Assertions.assertEquals("maths", libraryUser.getBooks().get(0).getCategory());
    }

    @Test
    void borrowBook_whenValidRequestButExist_shouldReturnBadRequest(){
        when(this.bookRepository.existsByUserNameEquals("Vishal Chouksey")).thenReturn(true);
        when(this.bookRepository.findByUserNameEquals("Vishal Chouksey")).thenReturn(getBookList());
        try{
            this.libraryService.borrowBook(getLibraryUser("Vishal Chouksey", getBookList()));
        } catch (BookBorrowIsNotAllowed ex){
            Assertions.assertEquals("more than 3 books not allowed to borrow", ex.getMessage());
        }
    }

    @Test
    void returnBook_whenValidRequest_shouldReturnValidResponse(){
        when(this.bookRepository.existsByTitle("Foundation of maths")).thenReturn(true);
        when(this.bookRepository.existsByTitle("best computers")).thenReturn(true);
        when(this.bookRepository.findByUserNameEquals("Vishal Chouksey")).thenReturn(getBookList());
        when(this.bookRepository.existsByUserNameEquals("Vishal Chouksey")).thenReturn(true);
        LibraryUser libraryUser = this.libraryService.returnBook(getLibraryUser("Vishal Chouksey", getBookList()));
        Assertions.assertNotNull(libraryUser);
        Assertions.assertEquals("Vishal Chouksey", libraryUser.getUserName());
        Assertions.assertEquals("mohit jain", libraryUser.getBooks().get(1).getAuthor());
        Assertions.assertEquals("maths", libraryUser.getBooks().get(1).getCategory());
    }

    @Test
    void createBook_whenValidRequest_shouldReturnValidResponse(){
        when(this.bookRepository.existsByTitle("Foundation of maths")).thenReturn(false);
        when(this.bookRepository.existsByTitle("best computers")).thenReturn(false);
        List<BookDTO> books = this.libraryService.createBook(getBookList());
        Assertions.assertNotNull(books);
        Assertions.assertEquals("jayant lele", books.get(0).getAuthor());
        Assertions.assertEquals("Computer", books.get(0).getCategory());
        Assertions.assertEquals("hardware", books.get(0).getTitle());
    }

    @Test
    void removeBook_whenValidRequest_shouldReturnValidResponse(){
        when(this.bookRepository.existsByTitle("Foundation of maths")).thenReturn(true);
        when(this.bookRepository.existsByTitle("best computers")).thenReturn(true);
        List<BookDTO> books = this.libraryService.removeBook(getBookList());
        Assertions.assertNotNull(books);
        Assertions.assertEquals("mohit jain", books.get(0).getAuthor());
        Assertions.assertEquals("maths", books.get(0).getCategory());
        Assertions.assertEquals("Foundation of maths", books.get(0).getTitle());
    }
}
