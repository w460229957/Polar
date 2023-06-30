package com.meditationzhang.catalogservice.web;


import com.meditationzhang.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class BookJsonTests {

    @Autowired
    private JacksonTester<Book> json;

    @Test
    void testSerialize() throws Exception{
        var book = new Book("1234567890","Title","Author",9.99);
        var result = json.write(book);
        assertThat(result).extractingJsonPathStringValue("@.isbn")
                .isEqualTo("1234567890");
        assertThat(result).extractingJsonPathStringValue("@.title")
                .isEqualTo("Title");
        assertThat(result).extractingJsonPathStringValue("@.author")
                .isEqualTo("Author");
        assertThat(result).extractingJsonPathNumberValue("@.price")
                .isEqualTo(9.99);
    }

    @Test
    void testDeserialize() throws Exception {
        var content = """
                {
                    "isbn":"1234567890",
                    "title":"Title",
                    "author":"Author",
                    "price":9.99
                 }
                 """;
        assertThat(json.parse(content))
                .usingRecursiveComparison()
                .isEqualTo(new Book("1234567890", "Title", "Author", 9.99));
    }
}
