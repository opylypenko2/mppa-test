package com.veypo.meal_planner.pages;

import com.veypo.meal_planner.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public abstract class BasePage {

    @FindBy(xpath = "//span[normalize-space(text())='Meal Planner']")
    public WebElement appName;

    @FindBy(xpath = "//span[normalize-space(text())='My Recipes']")
    public WebElement myRecipesLink;

    @FindBy(xpath = "//span[normalize-space(text())='Log in']")
    public WebElement loginLink;

    @FindBy(xpath = "//span[normalize-space(text())='Sign up']")
    public WebElement signUpLink;

    @FindBy(xpath = "//header//a")
    public List<WebElement> headerLinks;

    @FindBy(xpath = "//mat-toolbar//a//span[normalize-space(.)='Account']")
    public WebElement accountDropdown;

    @FindBy(xpath = "//div[@role='menu']//a[@role='menuitem']")
    public List<WebElement> accountMenu;

    @FindBy(xpath = "//div[@role='menu']//a[@role='menuitem']/span[normalize-space(.)='Log out']")
    public WebElement logOutOption;

    @FindBy(xpath = "//a[@title='Instagram']")
    public WebElement instagramLink;

    @FindBy(xpath = "//a[@title='X']")
    public WebElement twitterLink;

    @FindBy(xpath = "//a[@title='Facebook']")
    public WebElement facebookLink;

    @FindBy(xpath = "//a[@title='YouTube']")
    public WebElement youTubeLink;

    @FindBy(xpath = "//a[@title='Buy Me a Coffee']")
    public WebElement buyMeACoffeeLink;

    @FindBy(xpath = "//footer//section/mppa-link")
    public List<WebElement> socialNetworkLinks;

    @FindBy(xpath = "//footer//div/div/section/h3")
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
    public WebElement helpCenterLink;

    @FindBy(xpath = "//footer//div//li/a[normalize-space(text())='Feedback']")
    public WebElement feedbackLink;

    @FindBy(xpath = "//footer//div//li/a[normalize-space(text())='About Us']")
    public WebElement aboutUsLink;

    @FindBy(xpath = "//footer//div//li/a[normalize-space(text())='Developer Hub']")
    public WebElement developerHubLink;

    @FindBy(xpath = "//mat-dialog-content//mat-form-field//div/textarea")
    public WebElement feedbackTextArea;

    @FindBy(xpath = "//button/span[normalize-space(text())='Close']")
    public WebElement closeBtn;

    @FindBy(xpath = "//button/span[normalize-space(text())='Submit']")
    public WebElement submitBtn;

    public BasePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
}
