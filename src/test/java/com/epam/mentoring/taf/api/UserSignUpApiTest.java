package com.epam.mentoring.taf.api;

import com.epam.mentoring.taf.AbstractTest;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;

public class UserSignUpApiTest extends AbstractTest {
    private final String username = "Test User";
    private final String email = "test_user@example.com";
    private final String password = "test_password";

    @BeforeClass
    public void setUp() {
        setShouldRunBrowser(false);
    }

    @Test
    public void apiVerification() {
        int uniqueId = (int) (Math.random() * 10000);
        String username = this.username + uniqueId;
        String email = this.email.replace("@", "." + uniqueId + "@");

        given()
                .baseUri(API_URL)
                .when()
                .contentType(ContentType.JSON)
                .body(String.format("{\"user\":{\"email\":\"%s\",\"password\":\"%s\",\"username\":\"%s\"}}", email, password, username))
                .post("/api/users")
                .then()
                .statusCode(201);
    }

    @Test
    public void apiAlreadyRegisteredVerification() {
        int uniqueId = (int) (Math.random() * 100);
        String username = this.username + uniqueId;
        given()
                .baseUri(API_URL)
                .when()
                .contentType(ContentType.JSON)
                .body(String.format("{\"user\":{\"email\":\"%s\",\"password\":\"%s\",\"username\":\"%s\"}}", email, password, username))
                .post("/api/users")
                .then()
                .statusCode(422)
                .body("errors.email", hasItem("has already been taken"));
    }
/*
 https://api.realworld.io/api/users - Accepts any value in the email field
 */
    @Test
    public void apiEmptyEmailVerification() {
        given()
                .baseUri(API_URL)
                .when()
                .contentType(ContentType.JSON)
                .body(String.format("{\"user\":{\"email\":\"%s\",\"password\":\"%s\",\"username\":\"%s\"}}", "", password, username))
                .post("/api/users")
                .then()
                .statusCode(422)
                .body("errors.email", hasItem("can't be blank"));
    }
}
