package io.github.vyo.hello_jersey.rest;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.given;

/**
 * Created by Manuel Weidmann on 03.03.2015.
 */
public class HelloJerseyRestAssuredTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/app";
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testHello() {
        expect().statusCode(200).contentType(ContentType.JSON).when()
                .get("/rest/hellojersey/hello");
    }

    @Test
    public void testHelloEcho() {
        given().pathParam("echo", "echo.").expect().statusCode(200).when().get("/rest/hellojersey/echo/{echo}");
    }
}
