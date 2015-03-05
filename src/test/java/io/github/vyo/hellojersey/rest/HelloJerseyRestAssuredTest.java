package io.github.vyo.hellojersey.rest;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeTrue;

/**
 * Created by Manuel Weidmann on 03.03.2015.
 */
public class HelloJerseyRestAssuredTest {

    static boolean connectionExists = false;

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/app";

        try {
            Response response = expect().statusCode(200).contentType(ContentType.JSON).when()
                    .get("/rest/hellojersey/hello");

            String responseString = new JsonPath(response.getBody().asString()).getString("message");
            connectionExists = "Hello Jersey!".equals(responseString);
        } catch (Exception e) {
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testHello() {
        assumeTrue(connectionExists);
        Response response = expect().statusCode(200).contentType(ContentType.JSON).when()
                .get("/rest/hellojersey/hello");

        assertEquals("Hello Jersey!", new JsonPath(response.getBody().asString()).getString("message"));
    }

    @Test
    public void testHelloEcho() {
        assumeTrue(connectionExists);
        Response response = given().pathParam("echo", "echo").expect().statusCode(200).when().get
                ("/rest/hellojersey/echo/{echo}");

        assertEquals("ohce", new JsonPath(response.getBody().asString()).getString("message"));
    }
}
