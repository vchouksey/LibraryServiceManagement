package com.example.libraryservicemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LibraryUser {

    @NotBlank(message = "user name must not be blank")
    private String userName;
    @Valid
    private List<BookDTO> books;
}