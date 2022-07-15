package com.example.libraryservicemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryUser {

    @NotBlank(message = "user name must not be blank")
    private String userName;
    @Valid
    private List<Book> books;
}
