package com.veypo.meal_planner.step_definitions;

import com.veypo.meal_planner.pojo.Content;
import com.veypo.meal_planner.pojo.Login;
import com.veypo.meal_planner.pojo.Recipe;
import com.veypo.meal_planner.utilities.ConfigurationReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.sl.In;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class CreateRecipeFlowAPIStepDefs {

    /* In Cucumber there is no need to make global variables static, unlike in Rest Assured */

    protected String accessToken;
    protected String recordURI;
    protected String recipeId;
    protected String recordVersion;
    protected String lastModified;

    @When("user logs in with valid credentials over API")
    public void user_logs_in_with_valid_credentials_over_api() {
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

    @When("user creates new recipe over API")
    public void user_creates_new_recipe_over_API() {
        /**
         Given Content type is "application/json"
         And header "X-MPPA-Auth-Token" = accessToken
         And request json body is:
         {
         "name": "Chocolate Cake",
         "content": {
         "description": "Best chocolate desert ever!"
         }
         }
         When user sends POST request to "/api/v1/recipes"
         Then Status code 201 (Created)
         Then response header's "Location" value contains recipeId
         */

        Recipe recipe = new Recipe();
        recipe.setName("Chocolate Cake");

        Content content = new Content();
        content.setDescription("Best chocolate desert ever!");
        recipe.setContent(content);

        System.out.println("recipe = " + recipe);

        Response response =
                given().accept(ContentType.JSON)
                        .log().body().contentType(ContentType.JSON)
                        .body(recipe)
                        .header("X-Mppa-Auth-Token", accessToken).
                        when().post("/api/v1/recipes").//prettyPeek().
                        then().statusCode(201)
                        .extract().response();

        recordURI = response.header("Location");
        System.out.println("Record URI = " + recordURI);

        recordVersion = response.header("ETag");
        System.out.println("Record Version = " + recordVersion);

        lastModified = response.header("Last-Modified");
        System.out.println("lastModified = " + lastModified);

        recipeId = recordURI.substring(recordURI.lastIndexOf("/") + 1);
//        System.out.println("Created recipeId = " + recipeId);
    }

    @And("user checks if new recipe is created over API")
    public void user_checks_if_new_recipe_is_created_over_API() {
        /**
         Given Accept header is "application/hal+json"
         And header "X-MPPA-Auth-Token" = accessToken
         And header "If-None-Match" = recordVersion
         And header "If-Modified-Since" = lastModified
         When user sends GET request to recordURI
         Then Status code 304 (Not Modified)
         */

        Response response =
                given().accept("application/hal+json")
                        .header("X-MPPA-Auth-Token", accessToken)
                        .header("If-None-Match", recordVersion)
                        .header("If-Modified-Since", lastModified).
                        when().get(recordURI).//prettyPeek().
                        then().statusCode(304)
                        .extract().response();

        recordVersion = response.header("ETag");
        System.out.println("Record Version = " + recordVersion);

        lastModified = response.header("Last-Modified");
        System.out.println("lastModified = " + lastModified);
    }

    @When("user updates created recipe over API")
    public void user_updates_created_recipe_over_API() {
        /**
         Given Content type is "application/merge-patch+json"
         And header "X-MPPA-Auth-Token" = accessToken
         And header "If-Match" = recordVersion
         And header "If-Unmodified-Since" = lastModified
         And request json body is:
         {
         "content": {
         "description": "Updated! Best chocolate desert ever! Delicious!"
         }
         }
         When user sends PATCH request to recordURI
         Then Status code 204 (No Content)
         */

        Map<String, Object> recipe = new LinkedHashMap<>();
        Map<String, String> content = new LinkedHashMap<>();
        content.put("description", "Updated! Best chocolate desert ever! Delicious!");
        recipe.put("content", content);

        Response response =
                given().contentType("application/merge-patch+json")
                        .header("X-MPPA-Auth-Token", accessToken)
                        .header("If-Match", recordVersion)
                        .header("If-Unmodified-Since", lastModified)
                        .body(recipe).log().body().
                        when().patch(recordURI).//prettyPeek().
                        then().statusCode(204).extract().response();

        recordVersion = response.header("ETag");
        System.out.println("Record Version = " + recordVersion);

        lastModified = response.header("Last-Modified");
        System.out.println("lastModified = " + lastModified);
    }

    @And("user checks if new recipe is updated over API")
    public void user_checks_if_new_recipe_is_updated_over_API() {
        /**
         Given Accept header is "application/hal+json"
         And header "X-MPPA-Auth-Token" = accessToken
         And header "If-None-Match" = recordVersion
         When user sends GET request to recordURI
         Then Status code 200
         */
        recordVersion = "\"0\"";

        Response response =
                given().accept("application/hal+json")
                        .header("X-MPPA-Auth-Token", accessToken)
                        .header("If-None-Match", recordVersion).//recordVersion = "\"0\""; in this case (initial version of created recipe) --> we hard code this part in order to get Status code 200, otherwise we expect to get Status code 304 (Not Modified)
                        when().get(recordURI).//prettyPeek().
                        then().statusCode(200)
                        .log().body().extract().response();

        recordVersion = response.header("ETag");
        System.out.println("Record Version = " + recordVersion);

        lastModified = response.header("Last-Modified");
        System.out.println("lastModified = " + lastModified);
    }

    @Then("user deletes created recipe over API")
    public void user_deletes_created_recipe_over_API() {
        /**
         Given header "X-MPPA-Auth-Token" = accessToken
         And Content type is "application/json"
         When user sends DELETE request to recordURI
         Then Status code 204 (No Content)
         */

        given().contentType(ContentType.JSON)
                .header("X-MPPA-Auth-Token", accessToken).
                when().delete(recordURI).//prettyPeek().
                then().statusCode(204);
    }

    @And("user checks if recipe is deleted over API")
    public void user_checks_if_recipe_is_deleted_over_API() {
        /**
         Given header "X-MPPA-Auth-Token" = accessToken
         When user sends GET request to recordURI
         Then Status code 200
         Then "status": "DELETED"
         */

        Response response =
                given().contentType(ContentType.JSON)
                        .header("X-MPPA-Auth-Token", accessToken).
                        when().get(recordURI).//prettyPeek().
                        then().statusCode(200)
                        .body("status", is("DELETED")).extract().response();

        recordVersion = response.header("ETag");
        System.out.println("Record Version = " + recordVersion);

        lastModified = response.header("Last-Modified");
        System.out.println("lastModified = " + lastModified);
    }

    @Then("user logs out over API")
    public void user_logs_out_over_API() {
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
