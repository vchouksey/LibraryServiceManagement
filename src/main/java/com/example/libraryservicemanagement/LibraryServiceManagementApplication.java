package com.example.libraryservicemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootApplication
public class LibraryServiceManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryServiceManagementApplication.class, args);
    }

}
