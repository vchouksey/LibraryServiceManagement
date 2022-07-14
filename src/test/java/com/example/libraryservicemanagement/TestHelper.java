package com.example.libraryservicemanagement;

import com.example.libraryservicemanagement.model.Book;
import com.example.libraryservicemanagement.model.BookDTO;
import com.example.libraryservicemanagement.model.LibraryUser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TestHelper {

    public static HttpEntity getRequestHeaders(String jsonBody) {
        List<MediaType> acceptTypes = new ArrayList<>();
        acceptTypes.add(MediaType.APPLICATION_JSON);

        HttpHeaders reqHeaders = new HttpHeaders();
        reqHeaders.setContentType(MediaType.APPLICATION_JSON);
        reqHeaders.setAccept(acceptTypes);

        return new HttpEntity<>(jsonBody, reqHeaders);
    }

    public static LibraryUser getLibraryUser(String userName, List<Book> books){
        LibraryUser libraryUser = new LibraryUser();
        libraryUser.setUserName(userName);
        libraryUser.setBooks(getBooks(books));
        return libraryUser;
    }

    public static List<Book> actualBookList(){
        List<Book> bookList = new ArrayList<>();
        bookList.add(getBook("mohit jain", "maths", "Foundation of maths"));
        bookList.add(getBook("alan tuco", "computer", "best computers"));
        return bookList;
    }



    public static List<Book> getBookList(){
        List<Book> bookList = new ArrayList<>();
        bookList.add(getBook("jayant lele", "Computer", "hardware"));
        bookList.add(getBook("mohit jain", "maths", "Foundation of maths"));
        bookList.add(getBook("alan tuco", "computer", "best computers"));
        return bookList;
    }

    public static Book getBook(String author, String category, String title) {
        Book book = new Book();
        book.setAuthor(author);
        book.setTitle(title);
        book.setCategory(category);
        return book;
    }

    public static List<BookDTO> getBooks(List<Book> bookList){
        List<BookDTO> bookDTOList = new ArrayList<>();
        for (Book book: bookList){
            BookDTO bookDTO = new BookDTO();
            bookDTO.setAuthor(book.getAuthor());
            bookDTO.setCategory(book.getCategory());
            bookDTO.setTitle(book.getTitle());
            bookDTOList.add(bookDTO);
        }
        return bookDTOList;
    }
}
