package com.veypo.meal_planner.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UpdateRecipePage extends BasePage{

    @FindBy(xpath = "//mppa-recipe-edit/main/form/mppa-button-panel[1]//button[normalize-space(.)='more_vert']")
    public WebElement threeDotsMenuBtn;

    @FindBy(xpath = "//a[.='DELETE']")
    public WebElement deleteRecipeOption;
}
