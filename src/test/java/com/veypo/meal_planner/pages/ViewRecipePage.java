package com.veypo.meal_planner.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ViewRecipePage extends BasePage {

    @FindBy(xpath = "//mppa-recipe-view//main/mppa-button-panel[1]/div/*")
    public List<WebElement> viewRecipeBtnPanel;

    @FindBy(xpath = "//main/mppa-button-panel[1]//button[normalize-space(.)='Share']")
    public WebElement shareBtn;

    @FindBy(xpath = "//main/div/div/h1")
    public WebElement recipeTitle;

    @FindBy(xpath = "//mppa-recipe-view/main//p/mppa-readmore")
    public WebElement recipeDescription;

    @FindBy(xpath = "//mppa-recipe-view/main/div/mppa-button-panel//button/span[@class='mat-mdc-button-touch-target']")
    public WebElement threeDotsMenuBtn;

    @FindBy(xpath = "//mppa-recipe-view//main/mppa-button-panel[1]//a[normalize-space(.)='Close']")
    public WebElement closeBtn;

    @FindBy(xpath = "//div[@class='cdk-overlay-pane']//a")
    public List<WebElement> threeDotsMenuAllOptions;

    @FindBy(xpath = "//div[@class='cdk-overlay-pane']//a[normalize-space(.)='Edit']")
    public WebElement editRecipeOption;

    @FindBy(xpath = "//div[@class='cdk-overlay-pane']//a[normalize-space(.)='Delete']")
    public WebElement deleteRecipeOption;

    @FindBy(xpath = "//mppa-entity-action-confirmation/mat-dialog-actions//span[.='Delete']")
    public WebElement confirmationDeleteOption;
}
