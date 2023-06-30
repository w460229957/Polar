package com.meditationzhang.catalogservice.web;


import com.meditationzhang.catalogservice.domain.BookNotFoundException;
import com.meditationzhang.catalogservice.domain.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(BookController.class)
public class BookControllerMvcTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    void whenGetBookNotExistingThenShouldReturn404() throws Exception {
        String isbn = "1234567890";
        given(bookService.viewBookDetails(isbn))
                .willThrow(new BookNotFoundException(isbn));
        mockMvc.perform(get("/books/" + isbn))
                .andExpect(status().isNotFound());
    }
}
