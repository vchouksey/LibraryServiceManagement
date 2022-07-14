package com.example.libraryservicemanagement.integration;

import com.example.libraryservicemanagement.model.BookDTO;
import com.example.libraryservicemanagement.model.LibraryUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static com.example.libraryservicemanagement.TestHelper.*;
import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LibraryIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String resourceUrlForReturnBook = "/api/v1/returnbook";
    private final String resourceUrlForBorrowBook = "/api/v1/borrowbook";
    private final String resourceUrlForCreateBook = "/api/v1/createBooks";
    private final String resourceUrlForDeleteBook = "/api/v1/removeBooks";
    private final String userName = "Vishal Chouksey";
    private final String author = "jayant lele";
    private final String category = "Computer";
    private final String title = "hardware";

    @Test
    public void borrowBook() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String actual = mapper.writeValueAsString(getBooks(getBookList()));
        String actualLibraryUser = mapper.writeValueAsString(getLibraryUser("Vishal Chouksey", getBookList()));
        restTemplate.exchange(resourceUrlForCreateBook, HttpMethod.POST, getRequestHeaders(actual), BookDTO[].class);
        ResponseEntity<LibraryUser> responseEntityLibraryUser =
                restTemplate.exchange(resourceUrlForBorrowBook, HttpMethod.POST, getRequestHeaders(actualLibraryUser), LibraryUser.class);
        assertEquals(HttpStatus.CREATED, responseEntityLibraryUser.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, responseEntityLibraryUser.getHeaders().getContentType());
        LibraryUser libraryUser = responseEntityLibraryUser.getBody();
        assert libraryUser != null;
        assertEquals(userName, libraryUser.getUserName());
        BookDTO book = libraryUser.getBooks().get(0);
        assertEquals(author, book.getAuthor());
        assertEquals(category, book.getCategory());
        assertEquals(title, book.getTitle());
    }

    @Test
    public void returnBook() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String actual = mapper.writeValueAsString(getBooks(getBookList()));
        String actualLibraryUser = mapper.writeValueAsString(getLibraryUser("Vishal Chouksey", getBookList()));
        restTemplate.exchange(resourceUrlForCreateBook, HttpMethod.POST, getRequestHeaders(actual), BookDTO[].class);
        ResponseEntity<LibraryUser> responseEntityLibraryUser =
                restTemplate.exchange(resourceUrlForBorrowBook, HttpMethod.POST, getRequestHeaders(actualLibraryUser), LibraryUser.class);
        assertEquals(HttpStatus.CREATED, responseEntityLibraryUser.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, responseEntityLibraryUser.getHeaders().getContentType());
        LibraryUser libraryUser = responseEntityLibraryUser.getBody();
        assert libraryUser != null;
        assertEquals(userName, libraryUser.getUserName());
        BookDTO book = libraryUser.getBooks().get(0);
        assertEquals(author, book.getAuthor());
        assertEquals(category, book.getCategory());
        assertEquals(title, book.getTitle());
        ResponseEntity<LibraryUser> responseEntity =
                restTemplate.exchange(resourceUrlForReturnBook, HttpMethod.PUT, getRequestHeaders(actualLibraryUser), LibraryUser.class);
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
    }

    @Test
    public void CreateBook() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String actual = mapper.writeValueAsString(getBooks(getBookList()));
        ResponseEntity<BookDTO[]> responseEntity =
                restTemplate.exchange(resourceUrlForCreateBook, HttpMethod.POST, getRequestHeaders(actual), BookDTO[].class);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        BookDTO[] book = responseEntity.getBody();
        assert book != null;
        assertEquals(author, book[0].getAuthor());
        assertEquals(category, book[0].getCategory());
        assertEquals(title, book[0].getTitle());
    }

    @Test
    public void RemoveBook() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String actual = mapper.writeValueAsString(getBooks(getBookList()));
        restTemplate.exchange(resourceUrlForCreateBook, HttpMethod.POST, getRequestHeaders(actual), BookDTO[].class);
        ResponseEntity<BookDTO[]> responseEntity =
                restTemplate.exchange(resourceUrlForDeleteBook, HttpMethod.DELETE, getRequestHeaders(actual), BookDTO[].class);
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        BookDTO[] book = responseEntity.getBody();
        assert book != null;
        assertEquals(author, book[0].getAuthor());
        assertEquals(category, book[0].getCategory());
        assertEquals(title, book[0].getTitle());
    }
}
