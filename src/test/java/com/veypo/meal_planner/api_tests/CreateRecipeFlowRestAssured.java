package com.veypo.meal_planner.api_tests;

import com.veypo.meal_planner.pojo.Content;
import com.veypo.meal_planner.pojo.Recipe;
import com.veypo.meal_planner.utilities.MealPlannerTestBase;
import io.cucumber.java.sl.In;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CreateRecipeFlowRestAssured extends MealPlannerTestBase {

    /* In Rest Assured we declare global variables as static to be shared by all instances of this class (every @Test method is an instance of this class)
     */

    protected static String recordURI;
    protected static String recipeId;
    protected static String recordVersion;
    protected static String lastModified;

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

    @Disabled
    @Order(value = 1)
    @DisplayName("POST new recipe with String body")
    @Test
    public void postRecipeWithStringBody() {
        String requestBody = "{\n" +
                "  \"name\": \"Chocolate Cake\",\n" +
                "  \"content\": {\n" +
                "    \"description\": \"Best chocolate desert ever!\"\n" +
                "}\n" +
                "}";

        recordURI =
                given().accept(ContentType.JSON)
                        .log().body().contentType(ContentType.JSON)
                        .body(requestBody)
                        .header("X-Mppa-Auth-Token", accessToken).
                when().post("/api/v1/recipes").//prettyPeek().
                then().statusCode(201)
                        .extract().response().header("Location");
        System.out.println("Record URI = " + recordURI);

        recipeId = recordURI.substring(recordURI.lastIndexOf("/") + 1);
        System.out.println("recipeId = " + recipeId);
    }

    @Disabled
    @Order(value = 1)
    @DisplayName("POST new recipe with Map body")
    @Test
    public void postRecipeWithMapBody() {
        Map<String, Object> requestBody = new LinkedHashMap<>();
        Map<String, String> content = new LinkedHashMap<>();
        content.put("description", "Best chocolate desert ever!");
        requestBody.put("name", "Chocolate Cake");
        requestBody.put("content", content);
        System.out.println("requestBody = " + requestBody);

        recordURI =
                given().accept(ContentType.JSON)
                        .log().body().contentType(ContentType.JSON)
                        .body(requestBody)
                        .header("X-Mppa-Auth-Token", accessToken).
                when().post("/api/v1/recipes").//prettyPeek().
                then().statusCode(201)
                        .extract().response().header("Location");
        System.out.println("Record URI = " + recordURI);

        recipeId = recordURI.substring(recordURI.lastIndexOf("/") + 1);
        System.out.println("recipeId = " + recipeId);
    }

    @Order(value = 1)
    @DisplayName("POST new recipe with POJO body")
    @Test
    public void postRecipeWithPOJOBody() {
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

//        recipeId = recordURI.substring(recordURI.lastIndexOf("/") + 1);
//        System.out.println("Created recipeId = " + recipeId);
//        System.out.println(this);

        //TODO Practice POJOs
    }

  //  @Disabled
    @Order(value = 2)
    @DisplayName("GET created recipe")
    @Test
    public void getCreatedRecipe() {
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

 //   @Disabled
    @Order(value = 3)
    @DisplayName("PATCH update recipe")
    @Test
    public void updateRecipe() {
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

 //   @Disabled
    @Order(value = 4)
    @DisplayName("GET updated recipe")
    @Test
    public void getUpdatedRecipe() {
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
                when().get(recordURI).prettyPeek().
                then().statusCode(200)
                        .log().body().extract().response();

        recordVersion = response.header("ETag");
        System.out.println("Record Version = " + recordVersion);

        lastModified = response.header("Last-Modified");
        System.out.println("lastModified = " + lastModified);
    }

 //   @Disabled
    @Order(value = 5)
    @DisplayName("DELETE recipe")
    @Test
    public void deleteRecipe() {
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

 //   @Disabled
    @Order(value = 6)
    @DisplayName("GET recipe")
    @Test
    public void getRecipe() {
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
}
