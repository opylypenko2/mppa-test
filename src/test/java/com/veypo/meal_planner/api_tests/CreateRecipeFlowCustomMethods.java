package com.veypo.meal_planner.api_tests;

import com.veypo.meal_planner.pojo.Content;
import com.veypo.meal_planner.pojo.EntityVersion;
import com.veypo.meal_planner.pojo.Recipe;
import com.veypo.meal_planner.utilities.MealPlannerTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class CreateRecipeFlowCustomMethods extends MealPlannerTestBase {

    @Test
    public void recipeCreationFlow() {
        EntityVersion recipeVersion = createRecipe();
        loadRecipe(recipeVersion);
        updateRecipe(recipeVersion);
        loadUpdatedRecipe(recipeVersion);
        deleteRecipe(recipeVersion.getEntityId());
        loadRecipe(recipeVersion.getEntityId());
    }

    private EntityVersion createRecipe() {
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

        String location = response.header("Location");
        System.out.println("location = " + location);

        String recordVersion = response.header("ETag");
        System.out.println("Record Version = " + recordVersion);

        String lastModified = response.header("Last-Modified");
        System.out.println("lastModified = " + lastModified);

        String entityId = location.substring(location.lastIndexOf("/") + 1);
        System.out.println("Created recipeId = " + entityId);

        return new EntityVersion(entityId, recordVersion, lastModified);
    }

    private void loadRecipe(EntityVersion recipeVersion) {
        /**
         Given Accept header is "application/hal+json"
         And header "X-MPPA-Auth-Token" = accessToken
         And header "If-None-Match" = recordVersion
         And header "If-Modified-Since" = lastModified
         When user sends GET request to "/api/v1/recipes" +
         "/" + recipeVersion.getEntityId()
         Then Status code 304 (Not Modified)
         */

        Response response =
                given().accept("application/hal+json")
                        .header("X-MPPA-Auth-Token", accessToken)
                        .header("If-None-Match", recipeVersion.getRecordVersion())
                        .header("If-Modified-Since", recipeVersion.getLastModified()).
                when().get("/api/v1/recipes" +
                                "/" + recipeVersion.getEntityId()).//prettyPeek().
                then().statusCode(304)
                        .extract().response();

        String recordVersion = response.header("ETag");
        System.out.println("Record Version = " + recordVersion);

        String lastModified = response.header("Last-Modified");
        System.out.println("lastModified = " + lastModified);
    }

    private EntityVersion updateRecipe(EntityVersion recipeVersion) {
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
         When user sends PATCH request to "/api/v1/recipes" +
         "/" + recipeVersion.getEntityId()
         Then Status code 204 (No Content)
         */

        Map<String, Object> recipe = new LinkedHashMap<>();
        Map<String, String> content = new LinkedHashMap<>();
        content.put("description", "Updated! Best chocolate desert ever! Delicious!");
        recipe.put("content", content);

        Response response =
                given().contentType("application/merge-patch+json")
                        .header("X-MPPA-Auth-Token", accessToken)
                        .header("If-Match", recipeVersion.getRecordVersion())
                        .header("If-Unmodified-Since", recipeVersion.getLastModified())
                        .body(recipe).log().body().
                when().patch("/api/v1/recipes" +
                                "/" + recipeVersion.getEntityId()).//prettyPeek().
                then().statusCode(204).extract().response();

        String recordVersion = response.header("ETag");
        System.out.println("Record Version = " + recordVersion);

        String lastModified = response.header("Last-Modified");
        System.out.println("lastModified = " + lastModified);

        return new EntityVersion(recipeVersion.getEntityId(), recordVersion, lastModified);
    }

    private void loadUpdatedRecipe(EntityVersion recipeVersion) {
        /**
         Given Accept header is "application/hal+json"
         And header "X-MPPA-Auth-Token" = accessToken
         When user sends GET request to "/api/v1/recipes" +
         "/" + recipeVersion.getEntityId()
         Then Status code 200
         */

        Response response =
                given().accept("application/hal+json")
                        .header("X-MPPA-Auth-Token", accessToken).
                when().get("/api/v1/recipes" + "/" + recipeVersion.getEntityId())
                        .//prettyPeek().
                then().statusCode(200)
                        .log().body().extract().response();

        String recordVersion = response.header("ETag");
        System.out.println("Record Version = " + recordVersion);

        String lastModified = response.header("Last-Modified");
        System.out.println("lastModified = " + lastModified);
    }

    private void deleteRecipe(String entityId) {
        /**
         Given header "X-MPPA-Auth-Token" = accessToken
         And Content type is "application/json"
         When user sends DELETE request to "/api/v1/recipes" +
         "/" + entityId
         Then Status code 204 (No Content)
         */

        given().contentType(ContentType.JSON)
                .header("X-MPPA-Auth-Token", accessToken).
        when().delete("/api/v1/recipes" +
                        "/" + entityId).//prettyPeek().
        then().statusCode(204);
    }
    private void loadRecipe(String entityId) {
        /**
         Given header "X-MPPA-Auth-Token" = accessToken
         When user sends GET request to "/api/v1/recipes" +
         "/" + entityId
         Then Status code 200
         Then "status": "DELETED"
         */

        Response response =
                given().contentType(ContentType.JSON)
                        .header("X-MPPA-Auth-Token", accessToken).
                        when().get("/api/v1/recipes" +
                                "/" + entityId).prettyPeek().
                        then().statusCode(200)
                        .body("status", is("DELETED")).extract().response();

        String recordVersion = response.header("ETag");
        System.out.println("Record Version = " + recordVersion);

        String lastModified = response.header("Last-Modified");
        System.out.println("lastModified = " + lastModified);
    }
    //TODO
}
