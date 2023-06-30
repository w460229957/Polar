package com.meditationzhang.catalogservice;

import com.meditationzhang.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CatalogServiceApplicationTests {
    @Autowired
    private WebTestClient webClient;

    @Test
    void contextLoads() {
    }

    @Test
    void whenPostRequestThenBookCreated(){
        var expectedBook = new Book("1234567890","Title","Author",9.99);

        webClient.post()
                .uri("/books")
                .bodyValue(expectedBook)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Book.class).value(
                        book -> {
                            assertThat(book).isNotNull();
                            assertThat(book.isbn())
                                    .isEqualTo(expectedBook.isbn());
                        }
                );

    }
}
