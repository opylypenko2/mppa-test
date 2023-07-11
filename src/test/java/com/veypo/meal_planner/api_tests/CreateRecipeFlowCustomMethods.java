package com.veypo.meal_planner.api_tests;

public class CreateRecipeFlowCustomMethods {

    public void testRecipeCreationFlow() {
        String recipeId = createRecipe();
        updstrRecipe(recipeId);
        deleteRecipe(recipeId);
    }

    private String createRecipe() {
        return "sbc";
    }

    private String getRecipeVersion(String recipeId) {
        return "2";
    }

    private void updstrRecipe(String recipeId) {

    }

    private void deleteRecipe(String recipeId) {

    }
    //TODO
}
