package com.veypo.meal_planner.pages;

import com.veypo.meal_planner.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public abstract class BasePage {

    @FindBy(xpath = "//span[normalize-space(text())='Meal Planner']")
    public WebElement appName;

    @FindBy(xpath = "//a[normalize-space(text())='My Recipes']")
    public WebElement myRecipesLink;

    @FindBy(xpath = "//a[normalize-space(text())='Developers']")
    public WebElement developersLink;

    @FindBy(xpath = "//a[normalize-space(text())='Log in']")
    public WebElement loginLink;

    @FindBy(xpath = "//span[normalize-space(text())='Sign up']")
    public WebElement signUpLink;

//    @FindBy(xpath = "//div[@class='mat-menu-trigger']/preceding-sibling::a")
//    public List<WebElement> headerLinks;
    @FindBy(xpath = "//div[@fxlayoutalign='start center']/a/following-sibling::div/a")
    public List<WebElement> headerLinks;

    @FindBy(xpath = "//h2[normalize-space(text())='Find Meal Planner on']")
    public WebElement socialSectionHeader;

    @FindBy(xpath = "//a[@title='Instagram']")
    public WebElement instagramLink;

    @FindBy(xpath = "//a[@title='Twitter']")
    public WebElement twitterLink;

    @FindBy(xpath = "//a[@title='Facebook']")
    public WebElement facebookLink;

    @FindBy(xpath = "//a[@title='YouTube']")
    public WebElement youTubeLink;

    @FindBy(xpath = "//a[@title='Buy Me a Coffee']")
    public WebElement buyMeACoffeeLink;

    @FindBy(xpath = "//footer//div/div/div/mppa-link")
    public List<WebElement> socialNetworkLinks;

    @FindBy(xpath = "//footer//div/h3")
    public List<WebElement> footerColumnsHeaders;

    @FindBy(xpath = "//footer//div//li/a")
    public List<WebElement> footerLinks;

    @FindBy(xpath = "//footer//div//li/a[normalize-space(text())='Terms and Conditions']")
    public WebElement termsLink;

    @FindBy(xpath = "//footer//div//li/a[normalize-space(text())='Privacy Policy']")
    public WebElement privacyLink;

    @FindBy(xpath = "//footer//div//li/a[normalize-space(text())='Cookies Policy']")
    public WebElement cookiesLink;

    @FindBy(xpath = "//footer//div//li/a[normalize-space(text())='Third Party Licenses']")
    public WebElement thirdPartyLink;

    @FindBy(xpath = "//footer//div//li/a[normalize-space(text())='Demo']")
    public WebElement demoLink;

    @FindBy(xpath = "//footer//div//li/a[normalize-space(text())='Help Center']")
    public WebElement helpLink;

    @FindBy(xpath = "//footer//div//li/a[normalize-space(text())='Feedback']")
    public WebElement feedbackLink;

    @FindBy(xpath = "//div/textarea")
    public WebElement textArea;

    @FindBy(xpath = "//button/span[normalize-space(text())='CLOSE']")
    public WebElement closeBtn;

    @FindBy(xpath = "//button/span[normalize-space(text())='SUBMIT']")
    public WebElement submitBtn;

    @FindBy(xpath = "//footer//div//li/a[normalize-space(text())='About Us']")
    public WebElement aboutUsLink;

    public BasePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
}

