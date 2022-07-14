package com.example.libraryservicemanagement.repository;

import com.example.libraryservicemanagement.TestHelper;
import com.example.libraryservicemanagement.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
class LibraryServiceRepositoriesTest {

    @Autowired
    private TestHelper testHelper;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testBookRepository(){
        bookRepository.save(testHelper.getBookList().get(0));
        bookRepository.save(testHelper.getBookList().get(1));
        bookRepository.save(testHelper.getBookList().get(2));
        Assertions.assertTrue(bookRepository.existsByTitle("Foundation of maths"));
        bookRepository.setBookByTitle("Vishal Chouksey", "Foundation of maths");
        Assertions.assertTrue(bookRepository.existsByUserNameEquals("Vishal Chouksey"));
        List<Book> books = bookRepository.findByUserNameEquals("Vishal Chouksey");
        Assertions.assertFalse(books.isEmpty());
        bookRepository.deleteBookByTitleEquals("Foundation of maths");
    }
}
