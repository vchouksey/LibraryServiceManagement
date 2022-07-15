package com.example.libraryservicemanagement.service;

import com.example.libraryservicemanagement.exception.BookBorrowIsNotAllowed;
import com.example.libraryservicemanagement.model.Book;
import com.example.libraryservicemanagement.model.BookDTO;
import com.example.libraryservicemanagement.model.LibraryUser;
import com.example.libraryservicemanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class LibraryService {

    private final BookRepository bookRepository;

    @Autowired
    public LibraryService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public LibraryUser borrowBook(LibraryUser libraryUser){
        List<Book> books = null;
        int librarySize = bookRepository.findByUserNameEquals(libraryUser.getUserName()).size();
        if (librarySize >= 3){
            throw new BookBorrowIsNotAllowed("more than 3 books not allowed to borrow");
        }
        for (Book book: libraryUser.getBooks()){
            if (bookRepository.existsByTitle(book.getTitle()) && librarySize < 3){
                    bookRepository.setBookByTitle(libraryUser.getUserName(), book.getTitle());
                    librarySize++;
            }
        }
        books = bookRepository.findByUserNameEquals(libraryUser.getUserName());
        return getLibraryUser(libraryUser.getUserName(), books);
    }

    public LibraryUser returnBook(LibraryUser libraryUser){
        LibraryUser user = null;
        for (Book book: libraryUser.getBooks()){
            if (bookRepository.existsByTitle(book.getTitle())){
                bookRepository.setBookByTitle("",book.getTitle());
            }
        }
        if (bookRepository.existsByUserNameEquals(libraryUser.getUserName())){
            user = getLibraryUser(libraryUser.getUserName(), bookRepository.findByUserNameEquals(libraryUser.getUserName()));
        }
        return user;
    }

    public List<BookDTO> createBook(List<Book> bookList){
        List<Book> returnList = new ArrayList<>();
        for (Book book: bookList){
            if (!bookRepository.existsByTitle(book.getTitle())) {
                bookRepository.save(book);
                returnList.add(book);
            }
        }
        return getBooks(returnList);
    }

    public List<BookDTO> removeBook(List<Book> bookList){
        List<Book> returnList = new ArrayList<>();
        for (Book book: bookList){
            if (bookRepository.existsByTitle(book.getTitle())) {
                bookRepository.deleteBookByTitleEquals(book.getTitle());
                returnList.add(book);
            }
        }
        return getBooks(returnList);
    }

    private LibraryUser getLibraryUser(String userName, List<Book> books){
        LibraryUser libraryUser = new LibraryUser();
        libraryUser.setUserName(userName);
        libraryUser.setBooks(books);
        return libraryUser;
    }

    private List<BookDTO> getBooks(List<Book> bookList){
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
