package com.veypo.meal_planner.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(xpath = "//span[.='Account']")
    public WebElement accountMenu;
}

