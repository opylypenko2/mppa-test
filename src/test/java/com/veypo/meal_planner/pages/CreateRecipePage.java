package com.veypo.meal_planner.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CreateRecipePage extends BasePage {

    //mat-label[.='Name']/../following-sibling::input
    @FindBy(xpath = "//form//mat-form-field[//mat-label[normalize-space(.)='Name']]//input")
    public WebElement recipeNameField;

    //div/mat-form-field/following-sibling::mat-form-field//label/following-sibling::textarea
    @FindBy(xpath = "//form//mat-form-field[//mat-label[normalize-space(.)='Description']]//textarea")
    public WebElement recipeDescriptionField;

    @FindBy(xpath = "//mppa-section-header[@name='Ingredients']")
    public WebElement ingredientsSectionName;

    @FindBy(xpath = "//div[@class='mppa-recipe-option']")
    public List<WebElement> allergyOptionsField;

    @FindBy(xpath = "//mppa-image-edit[@alt='Recipe image']//span[@class='mat-mdc-button-touch-target']")
    public WebElement recipeSelectImageBtn;

    @FindBy(xpath = "//mppa-asset-image//img[@alt='Internet']")
    public WebElement internetOption;

    // //mppa-image-edit-dialog//mppa-image-internet//mat-form-field[//mat-label[normalize-space(.)='Image URL']]
    // //mat-form-field//div/label/following-sibling::input[@type='url']
    @FindBy(xpath = "//mppa-image-edit-dialog//mppa-image-internet//mat-form-field//input")
    public WebElement imageUrlField;

    @FindBy(xpath = "//mppa-image-edit-dialog//mppa-image-internet//button[normalize-space(.)='NEXT']")
    public WebElement imageInternetNextBtn;

    @FindBy(xpath = "//span[normalize-space(text())='CROP']")
    public WebElement cropBtn;

    //    @FindBy(xpath = "//mppa-recipe-edit//form/mppa-button-panel[1]//button[.='SAVE']/span[@class='mat-mdc-button-touch-target']")
//    public WebElement saveChangesBtn;
    @FindBy(xpath = "//span[normalize-space(text())='SAVE']")
    public WebElement saveBtn;

    @FindBy(xpath = "//mppa-recipe-tag-section-edit//input[@role='combobox']")
    public WebElement newTagField;

    @FindBy(xpath = "//mppa-recipe-ingredient-section-edit//input[@type='number']")
    public WebElement servingsField;

    @FindBy(xpath = "//mppa-recipe-ingredient-list-edit//span[normalize-space(.)='ADD INGREDIENT']")
    public WebElement addIngredientBtn;

    @FindBy(xpath = "//mppa-recipe-ingredient//input")
    public WebElement ingredientInputField;

    // //div[@fxlayoutalign='center stretch']//label/following-sibling::textarea
    @FindBy(xpath = "//mppa-recipe-ingredient-description//textarea")
    public WebElement ingredientAdditionalInfoField;

    @FindBy(xpath = "//mppa-recipe-ingredient-quantity//input")
    public WebElement ingredientQuantityField;

    @FindBy(xpath = "//mat-select")
    public WebElement ingredientMeasureMenu;

    @FindBy(xpath = "//div[@role='listbox']/mat-optgroup/mat-option")
    public List<WebElement> measureMenuOptions;

    @FindBy(xpath = "//mppa-recipe-ingredient-add//button/span[normalize-space(text())='ADD']")
    public WebElement ingredientsSectionAddBtn;

    @FindBy(xpath = "//mppa-recipe-entity-section-edit//input")
    public WebElement ingredientSectionName;

    @FindBy(xpath = "//mppa-recipe-ingredient-list-edit//button[normalize-space(.)='ADD SECTION']")
    public WebElement ingredientsAddSectionBtn;

    @FindBy(xpath = "//mppa-recipe-entity-section-edit//button[normalize-space(.)='ADD']")
    public WebElement addSectionAddBtn;

    @FindBy(xpath = "//mppa-recipe-step-list-edit//button//span[normalize-space(text())='ADD STEP']")
    public WebElement addStepBtn;

    @FindBy(xpath = "//mppa-recipe-step-edit//input")
    public List<WebElement> directionsSectionInputs;

    @FindBy(xpath = "//mppa-recipe-step-edit//textarea")
    public WebElement directionsAdditionalInfoField;

    @FindBy(xpath = "//mppa-recipe-step-edit//button//span/span[.='ADD']")
    public WebElement directionsSectionAddBtn;

    @FindBy(xpath = "//mppa-image-edit[@alt='Illustration of the recipe step']//span[@class='mat-mdc-button-touch-target']")
    public WebElement stepAddImageBtn;

    @FindBy(xpath = "//mppa-recipe-tip-section-edit//mat-icon[.='navigate_next']/following-sibling::span[.='Notes / Tips']")
    public WebElement notesTipsSection;

    //mppa-recipe-tip-section-edit//button//span[normalize-space(text())='ADD NOTE / TIP']
    @FindBy(xpath = "//mppa-recipe-tip-section-edit//mppa-recipe-tip-list-edit//button[normalize-space(.)='ADD NOTE / TIP']")
    public WebElement addNoteTipBtn;

    @FindBy(xpath = "//mppa-recipe-tip-list-edit//mppa-recipe-tip-edit//form//input") //TODO 2 of 2
    public WebElement tip;

    @FindBy(xpath = "//mppa-image-edit[@alt='Illustration of the recipe step']//span[@class='mat-mdc-button-touch-target']")
    public WebElement tipsAddPhotoBtn;

    @FindBy(xpath = "//form/mppa-button-panel[1]//button[normalize-space(.)='CREATE']")
    public WebElement createBtn;
}
