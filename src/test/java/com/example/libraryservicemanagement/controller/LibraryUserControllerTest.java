package com.example.libraryservicemanagement.controller;

import com.example.libraryservicemanagement.service.LibraryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.libraryservicemanagement.TestHelper.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LibraryUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibraryService libraryService;

    @Test
    void borrowBooks_whenValidRequest_shouldReturnValidResponse() throws Exception {
        var mapper = new ObjectMapper();
        when(this.libraryService.borrowBook(getLibraryUser("Vishal Chouksey", actualBookList())))
                .thenReturn(getLibraryUser("Vishal Chouksey", actualBookList()));
        String jsonString = mapper.writeValueAsString(getLibraryUser("Vishal Chouksey", actualBookList()));

        mockMvc.perform(post("/api/v1/borrowbook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(jsonString));
    }

    @Test
    void borrowBooks_whenInValidRequest_shouldReturnBadRequest() throws Exception {
        var mapper = new ObjectMapper();
        when(libraryService.borrowBook(getLibraryUser("Vishal Chouksey", getBookList())))
                .thenReturn(getLibraryUser("Vishal Chouksey", getBookList()));
        var jsonString = mapper.writeValueAsString(getLibraryUser("Vishal Chouksey", getBookList()));

        mockMvc.perform(post("/api/v1/borrowbook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(jsonString));
    }

    @Test
    void returnBooks_whenValidRequest_shouldReturnValidResponse() throws Exception {
        var mapper = new ObjectMapper();
        when(libraryService.returnBook(getLibraryUser("Vishal Chouksey", actualBookList())))
                .thenReturn(getLibraryUser("Vishal Chouksey", actualBookList()));
        var jsonString = mapper.writeValueAsString(getLibraryUser("Vishal Chouksey", actualBookList()));

        mockMvc.perform(put("/api/v1/returnbook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(jsonString));
    }
}
