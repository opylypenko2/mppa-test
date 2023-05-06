package com.veypo.meal_planner.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@id='mat-input-0']")
    public WebElement inputEmail;

    @FindBy(xpath = "//input[@id='mat-input-1']")
    public WebElement inputPassword;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement loginBtn;

    @FindBy(xpath = "//div[normalize-space(text())='Incorrect username or password']")
    public WebElement alertMsg;

    @FindBy(xpath = "//mat-form-field//mat-error/mppa-error-field/div")
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
//TODO
    }
}

