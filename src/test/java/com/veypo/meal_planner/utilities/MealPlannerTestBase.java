package com.veypo.meal_planner.utilities;

import com.veypo.meal_planner.pojo.Login;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public abstract class MealPlannerTestBase {

    protected static String accessToken;

    /*
     - @BeforeAll
        - is used to signal that the annotated method should be executed BEFORE ALL TESTS in the current test class.
        - In contrast to @BeforeEach methods, @BeforeAll methods are only executed once for a given test class.
        - @BeforeAll methods must have a void return type, must not be private, and must be static by default.
    */
    @BeforeAll
    public static void init() {
        baseURI = ConfigurationReader.getProperty("url.api");

        /**
         Given Accept type and Content type is JSON
         And request json body is:
         {
         "email": "first.last@veypo.com",
          "password": "#YummyFood4me$"
         }
         When user sends POST request to "/api/v1/auth/app/login"
         Then Status code 200
         And Content type should be application/json
         Then response header's "X-Mppa-Auth-Token" value is access token
         */

        Login requestBody = new Login();
        requestBody.setEmail(ConfigurationReader.getProperty("user1.email.valid"));
        requestBody.setPassword(ConfigurationReader.getProperty("user1.password.valid"));

        accessToken =
        given().accept(ContentType.JSON).log().body()
                .contentType(ContentType.JSON)
                .body(requestBody).
        when().post("/api/v1/auth/app/login").//prettyPeek().
        then().statusCode(200)
                .contentType("application/json")
                .extract().response().header("X-Mppa-Auth-Token");

        System.out.println("accessToken = " + accessToken);
    }

   /*
    - @AfterAll
        - is used to signal that the annotated method should be executed AFTER ALL TESTS in the current test class.
        - In contrast to @AfterEach methods, @AfterAll methods are only executed once for a given test class.
        - @AfterAll methods must have a void return type, must not be private, and must be static by default.
    */
    @AfterAll
    public static void logout() {
        /**
        Given header "X-MPPA-Auth-Token" = accessToken
        When user sends DELETE request to "/api/v1/auth/sessions/current"
        Then Status code is 204
        */
        given().header("X-Mppa-Auth-Token", accessToken).
        when().delete("/api/v1/auth/sessions/current").
        then().statusCode(204);
    }
}
