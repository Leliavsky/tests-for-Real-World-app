package com.epam.mentoring.taf.api;

import com.epam.mentoring.taf.AbstractTest;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

public class UserSignInApiTest extends AbstractTest {
    private final String email = "tom_marvolo@example.com";
    private final String password = "Voldemort";

    @BeforeClass
    public void setUp() {
        setShouldRunBrowser(false);
    }

    @Test
    public void apiVerification() {
        given()
                .baseUri(API_URL)
                .when()
                .contentType(ContentType.JSON)
                .body(String.format("{\"user\":{\"email\":\"%s\",\"password\":\"%s\"}}", email, password))
                .post("/api/users/login")
                .then()
                .statusCode(200)
                .body("user.email", is(email));
    }

    @Test
    public void apiNegativeVerification() {
        given()
                .baseUri(API_URL)
                .when()
                .contentType(ContentType.JSON)
                .body(String.format("{\"user\":{\"email\":\"%s\",\"password\":\"%s\"}}", email, "wrong_password"))
                .post("/api/users/login")
                .then()
                .statusCode(403)
                .body("errors.'email or password'", hasItem("is invalid"));
    }
}
