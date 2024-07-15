package com.epam.mentoring.taf.api;

import com.epam.mentoring.taf.AbstractTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class SearchingByTagApiTest extends AbstractTest {

    @BeforeClass
    public void setUp() {
        setShouldRunBrowser(false);
    }

    @Test
    public void apiVerification1() {
        String tag = "eos";
        given()
                .baseUri(API_URL)
                .when()
                .get("/api/articles?tag={tag}&limit=10&offset=0", tag)
                .then().statusCode(200)
                .body("articles[0].tagList", hasItem("eos"));
    }

    @Test
    public void apiVerification2() {
        String tag = "enim";
        given()
                .baseUri(API_URL)
                .when()
                .get("/api/articles?tag={tag}&limit=10&offset=0", tag)
                .then()
                .statusCode(200)
                .body("articles[0].tagList", hasItem("enim"));
    }

    @Test
    public void apiVerification3() {
        String tag = "repellat";
        given()
                .baseUri(API_URL)
                .when()
                .get("/api/articles?tag={tag}&limit=10&offset=0", tag)
                .then().statusCode(200)
                .body("articles[0].tagList", hasItem("repellat"));
    }

    @Test
    public void apiNegativeVerification() {
        String tag = "invalid_tag_name";
        given()
                .baseUri(API_URL)
                .when()
                .get("/api/articles?tag={tag}&limit=10&offset=0", tag)
                .then()
                .statusCode(200)
                .body("articlesCount", equalTo(0));
    }

}
