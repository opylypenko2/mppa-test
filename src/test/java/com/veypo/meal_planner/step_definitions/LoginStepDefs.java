package com.veypo.meal_planner.step_definitions;

import com.veypo.meal_planner.pages.HomePage;
import com.veypo.meal_planner.pages.LoginPage;
import com.veypo.meal_planner.utilities.BrowserUtils;
import com.veypo.meal_planner.utilities.ConfigurationReader;
import com.veypo.meal_planner.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginStepDefs {

    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 20);

    @Given("user is on the login page")
    public void user_is_on_the_login_page() {
        String url = ConfigurationReader.getProperty("url.ui")
                + ConfigurationReader.getProperty("login.page.path");

        Driver.getDriver().get(url);
    }

    @When("user logs in with valid credentials")
    public void user_logs_in_with_valid_credentials() {
        loginPage.login_with_click(
                ConfigurationReader.getProperty("user1.email.valid"),
                ConfigurationReader.getProperty("user1.password.valid"));
        String expectedCurrentUrl = ConfigurationReader.getProperty("url.ui") + "/";
        wait.until(ExpectedConditions.urlToBe(expectedCurrentUrl));
    }

    @When("user enters valid credentials")
    public void user_enters_valid_credentials() {
        loginPage.login(
                ConfigurationReader.getProperty("user1.email.valid"),
                ConfigurationReader.getProperty("user1.password.valid"));
    }

    @Then("login button is displayed")
    public void login_button_is_displayed() {
        Assert.assertTrue(loginPage.loginBtn.isDisplayed());
    }

    @Then("login button is enabled")
    public void login_button_is_enabled() {
        Assert.assertTrue(loginPage.loginBtn.isEnabled());
    }

    @Then("user clicks login button")
    public void user_clicks_login_button() {
        loginPage.loginBtn.click();
    }

    @Then("the current url matches expected url")
    public void the_current_url_matches_expected_url() {
        // since actualCurrentUrl = https://meal2cook.dev/ --> gives a single slash at the end of url during the test,
        // and expectedCurrentUrl (which we read from configuration.properties file) does NOT have a single slash
        // at the end of url, we concatenate a single slash at the end:
        String expectedCurrentUrl = ConfigurationReader.getProperty("url.ui") + "/";
        wait.until(ExpectedConditions.urlToBe(expectedCurrentUrl));
        //-----------------------------------------------------------------------------
        //  Example:
        //  driver.get("https://example.com");
        //  wait.until(ExpectedConditions.urlToBe("https://example.com")); --> to wait
        //  until the URL is exactly "https://example.com"
        //  Code to be executed after the expected URL is matched
        // ----------------------------------------------------------------------------
        //  Can also use:
        //  wait.until(ExpectedConditions.urlMatches("https://example\\.com/.*")); -->
        //  which will match any URL that starts with "https://example.com/"
        //  Code to be executed after the expected URL is matched
        //-----------------------------------------------------------------------------
        String actualCurrentUrl = Driver.getDriver().getCurrentUrl();
        Assert.assertEquals(expectedCurrentUrl, actualCurrentUrl);
    }

    @Then("user should see the account menu link {string}")
    public void user_should_see_the_account_menu_link(String expectedLink) {
        BrowserUtils.verifyText(expectedLink, homePage.accountMenu);
    }

    @When("user enters invalid credentials")
    public void user_enters_invalid_credentials() {
        loginPage.login(
                ConfigurationReader.getProperty("user2.email.invalid"),
                ConfigurationReader.getProperty("user2.password.invalid"));
    }

    @Then("alert message {string} is displayed")
    public void alert_message_is_displayed(String expectedAlertMsg) {
        BrowserUtils.verifyText(expectedAlertMsg, loginPage.alertMsg);
    }

    @When("user enters incorrect email address format")
    public void user_enters_incorrect_email_address_format() {
        loginPage.login(
                ConfigurationReader.getProperty("user1.email.incorrect.format"),
                ConfigurationReader.getProperty("user1.password.valid"));
    }

    @Then("field error message {string} is displayed")
    public void field_error_message_is_displayed(String expectedErrorMsg) {
        BrowserUtils.verifyText(expectedErrorMsg, loginPage.emailErrorMsg);
    }

    @Then("login button is disabled")
    public void login_button_is_disabled() {
        Assert.assertFalse(loginPage.loginBtn.isEnabled());
    }
}
