package com.veypo.meal_planner.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UpdateRecipePage extends BasePage{

    @FindBy(xpath = "//mppa-recipe-edit/main/form/mppa-button-panel[1]//button[normalize-space(.)='more_vert']")
    public WebElement threeDotsMenuBtn;

  //  @FindBy(xpath = "//form//mat-form-field[//mat-label[normalize-space(.)='Description']]//textarea")
    @FindBy(xpath = "//mat-form-field//textarea")
    public WebElement recipeDescriptionField;

    @FindBy(xpath = "//mppa-recipe-edit/main/form/mppa-button-panel//button//span[normalize-space(.)='Save']")
    public WebElement updateRecipeSaveBtn;

    @FindBy(xpath = "//a[.='Delete']")
    public WebElement deleteRecipeOption;
}
