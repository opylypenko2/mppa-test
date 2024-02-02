package com.veypo.meal_planner.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyRecipesPage extends BasePage {

    @FindBy(xpath = "//button//span[normalize-space(text())='Add Recipe']")
    public WebElement addRecipeMenu;

    @FindBy(xpath = "//span[normalize-space(text())='Create New']")
    public WebElement createNewRecipeOption;

    @FindBy(xpath = "//mppa-recipe-workspace-active//input")
    public WebElement myRecipesSearchField;

    // works with search (for card layout --> Preferences):
    @FindBy(xpath = "//mppa-recipe-view-card//mat-card-title/a")    // unique with search option
    public WebElement savedRecipe;

    // works for card layout (--> Preferences):
    @FindBy(xpath = "//mppa-recipe-view-card//mat-card-title/a")
    public List<WebElement> allSavedRecipes;

    // works for card layout (--> Preferences):
    @FindBy(xpath = "//mppa-recipe-workspace-active-layout-card/div/div[1]//mppa-image-viewport-view/img")
    public WebElement recentlyCreatedRecipe;

    // Card Edit, View Buttons:
    @FindBy(xpath = "//mppa-recipe-workspace-active-layout-card/div/div[1]//mat-card-actions//a")
    public List<WebElement> cardActions;

    @FindBy(xpath = "//main/div/div/h1")
    public WebElement recipeTitle;

    // works with search (for gallery layout):
//    @FindBy(xpath = "//mppa-recipe-workspace-active-layout-gallery//mppa-image-view//a")
//    public WebElement savedRecipe;

    // works for gallery layout:
//    @FindBy(xpath = "//mppa-recipe-workspace-active-layout-gallery/div/div[1]//a")
//    public WebElement recentlyUpdatedRecipe;
}
