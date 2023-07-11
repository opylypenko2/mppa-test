package com.veypo.meal_planner.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ViewRecipePage extends BasePage {

    @FindBy(xpath = "//mppa-recipe-view//main/mppa-button-panel[1]/div/*")
    public List<WebElement> viewRecipeBtnPanel;

    @FindBy(xpath = "//main/mppa-button-panel[1]//button[normalize-space(.)='SHARE']")
    public WebElement shareBtn;

    @FindBy(xpath = "//mppa-recipe-view/main/div/mppa-button-panel//button/span[@class='mat-mdc-button-touch-target']")
    public WebElement threeDotsMenuBtn;

    @FindBy(xpath = "//mppa-recipe-view//main/mppa-button-panel[1]//a[normalize-space(.)='CLOSE']")
    public WebElement closeBtn;

    @FindBy(xpath = "//div[@class='cdk-overlay-pane']//a")
    public List<WebElement> threeDotsMenuAllOptions;

    @FindBy(xpath = "//div[@class='cdk-overlay-pane']//a[normalize-space(.)='EDIT']")
    public WebElement editRecipeOption;

    @FindBy(xpath = "//div[@class='cdk-overlay-pane']//a[normalize-space(.)='DELETE']")
    public WebElement deleteRecipeOption;

    @FindBy(xpath = "//mppa-entity-action-confirmation/mat-dialog-actions//span[.='DELETE']")
    public WebElement confirmationDeleteOption;
}
