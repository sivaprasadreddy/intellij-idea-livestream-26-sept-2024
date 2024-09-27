package com.sivalabs.bookmarks;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import static io.restassured.RestAssured.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@Sql("classpath:/test-data.sql")
class BookmarkControllerTest {
    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void shouldGetAllBookmarks() {
        given()
                .when()
                .get("/api/bookmarks")
                .then()
                .statusCode(200)
                .body("size()", CoreMatchers.equalTo(5));
    }

    @Test
    void shouldGetBookmarkById() {
        given()
                .when()
                .get("/api/bookmarks/1")
                .then()
                .statusCode(200)
                .body("title", CoreMatchers.equalTo("SivaLabs Blog"));
    }

    @Test
    void shouldCreateBookmarkSuccessfully() {
        String payload = """
                {
                 "title": "SivaLabs Website",
                 "url": "https://sivalabs.com"
                }
                """;
        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/api/bookmarks")
                .then()
                .statusCode(201);
    }
}