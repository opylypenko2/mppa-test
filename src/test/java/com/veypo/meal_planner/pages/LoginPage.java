package com.veypo.meal_planner.pages;

import com.veypo.meal_planner.utilities.BrowserUI_Utils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@type='email']")
    public WebElement inputEmail;

    @FindBy(xpath = "//input[@type='password']")
    public WebElement inputPassword;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement loginBtn;

    @FindBy(xpath = "//mppa-alert/div/span[normalize-space(.)='Incorrect username or password']")
    public WebElement alertMsg;

    @FindBy(xpath = "//span[normalize-space(text())='Incorrect email address format']")
    public WebElement emailErrorMsg;

    public void login(String email, String password) {
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
    }

    public void login_with_click(String email, String password) {
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
        loginBtn.click();
    }

    public void logout() {
        accountDropdown.click();
        logOutOption.click();
    }
}
