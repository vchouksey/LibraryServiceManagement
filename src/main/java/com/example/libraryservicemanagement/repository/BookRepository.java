package com.example.libraryservicemanagement.repository;

import com.example.libraryservicemanagement.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByTitle(String bookTitle);
    List<Book> findByUserNameEquals(String userName);
    boolean existsByUserNameEquals(String userName);
    @Modifying
    @Query("update Book u set u.userName = ?1 where u.title = ?2")
    void setBookByTitle(String userName, String title);
    @Transactional
    void deleteBookByTitleEquals(String bookTitle);
}
