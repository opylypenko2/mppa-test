package com.veypo.meal_planner.pages;

import com.veypo.meal_planner.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    @FindBy(xpath = "//span[normalize-space(text())='Meal Planner']")
    public WebElement appName;
    @FindBy(xpath = "//a[.='My Recipes']")
    public WebElement myRecipesModule;
    @FindBy(xpath = "//a[.='Developers']")
    public WebElement developersModule;
    @FindBy(xpath = "//a[.='Log in']")
    public WebElement loginLink;
    @FindBy(xpath = "//span[.='Sign up']")
    public WebElement signUpLink;
    @FindBy(xpath = "//h2[.='Find Meal Planner on']")
    public WebElement socialSectionTitle;
    @FindBy(xpath = "//img[@alt='Instagram logo']")
    public WebElement instagramLogo;
    @FindBy(xpath = "//img[@alt='Twitter logo']")
    public WebElement twitterLogo;
    @FindBy(xpath = "//img[@alt='Facebook logo']")
    public WebElement facebookLogo;

    public BasePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
}

