package com.veypo.meal_planner.step_definitions;

import com.veypo.meal_planner.pages.BasePage;
import com.veypo.meal_planner.utilities.BrowserUI_Utils;
import com.veypo.meal_planner.utilities.ConfigurationReader;
import com.veypo.meal_planner.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

public class BasePageStepDefs extends BasePage {

//    @DataTableType
//    public Link linkEntry(Map<String, String> entry) {
//        return new Link(entry.get("expectedLinkText"), entry.get("expectedPath"));
//    }

    @Given("user is on the home page")
    public void user_is_on_the_home_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url.ui"));
    }

    @Then("user should see the app name link {string}")
    public void user_should_see_the_app_name_link(String expectedLink) {
        BrowserUI_Utils.verifyText(expectedLink, appName);
    }

    @Then("user should see the following links")
    public void user_should_see_the_following_links(List<String> expectedLinks) {
        List<String> actualLinks = BrowserUI_Utils.getElementsText(headerLinks);
        Assert.assertEquals(expectedLinks, actualLinks);
    }

    @When("user clicks login link")
    public void user_clicks_login_link() {
        loginLink.click();
    }

    @Then("user should see my recipes link {string}")
    public void user_should_see_my_recipes_link(String expectedLink) {
        BrowserUI_Utils.verifyText(expectedLink, myRecipesLink);
    }

    @Then("the following social network apps links are displayed")
    public void the_following_social_network_apps_links_are_displayed(List<String> expectedLinks) {
        List<String> actualLinks = BrowserUI_Utils.getElementsAttribute(socialNetworkLinks, "title");
        Assert.assertEquals(expectedLinks, actualLinks);
    }

    @Then("the following footer columns headers are displayed")
    public void the_following_footer_columns_headers_are_displayed(List<String> expectedHeaders) {
        List<String> actualHeaders = BrowserUI_Utils.getElementsText(footerColumnsHeaders);
        Assert.assertEquals(expectedHeaders, actualHeaders);
    }

    @Then("the following footer links are displayed and reachable")
    public void the_following_footer_links_are_displayed_and_reachable(Map<String, String> expectedLinksTextAndPath) throws MalformedURLException {

        for (Map.Entry<String, String> eachEntry : expectedLinksTextAndPath.entrySet()) {
            String expectedLinkText = eachEntry.getKey();
            //    System.out.println("expectedLinkText = " + expectedLinkText);

            String expectedPath = eachEntry.getValue();
            //    System.out.println("expectedPath = " + expectedPath);

            String locatorXpath = String.format("//footer//div//li/a[normalize-space(text())='%s']", expectedLinkText);
            //    System.out.println("locatorXpath = " + locatorXpath);

            WebElement element = Driver.getDriver().findElement(By.xpath(locatorXpath));
            Assert.assertNotEquals(locatorXpath, element, null);
            Assert.assertTrue(element.isDisplayed());

            String originalUrl = Driver.getDriver().getCurrentUrl();
            element.click();
            BrowserUI_Utils.verifyCurrentUrlPath(expectedPath);
            Driver.getDriver().get(originalUrl);
        }
    }

    @Then("after user clicks my recipes link current url matches expected my recipes page url")
    public void after_user_clicks_my_recipes_link_current_url_matches_expected_my_recipes_page_url() {
        BrowserUI_Utils.verifyCurrentUrl(myRecipesLink, ConfigurationReader.getProperty("url.ui") + ConfigurationReader.getProperty("my.recipes.page.path"));
    }

    @Then("after user clicks signup link current url matches expected signup page url")
    public void after_user_clicks_signup_link_current_url_matches_expected_signup_page_url() {
        BrowserUI_Utils.verifyCurrentUrl(
                signUpLink, ConfigurationReader.getProperty("url.ui") + ConfigurationReader.getProperty("signup.page.path"));
    }

    @Then("after clicking feedback link user should see dialog window for providing feedback")
    public void after_clicking_feedback_link_user_should_see_dialog_window_for_providing_feedback() {
        feedbackLink.click();
        feedbackTextArea.sendKeys("Awesome app");
        submitBtn.click();
        closeBtn.click();
    }

    @Then("after user clicks instagram link current url matches expected instagram home page url")
    public void after_user_clicks_instagram_link_current_url_matches_expected_instagram_home_page_url() {
        BrowserUI_Utils.getWindowHandlesAndVerifyNewWindowUrl(instagramLink,
                ConfigurationReader.getProperty("instagram.url"));
    }

    @Then("after user clicks twitter link current url matches expected twitter home page url")
    public void after_user_clicks_twitter_link_current_url_matches_expected_twitter_home_page_url() {
        BrowserUI_Utils.getWindowHandlesAndVerifyNewWindowUrl(twitterLink,
                ConfigurationReader.getProperty("twitter.url"));
    }

    @Then("after user clicks facebook link current url matches expected facebook home page url")
    public void after_user_clicks_facebook_link_current_url_matches_expected_facebook_home_page_url() {
        BrowserUI_Utils.getWindowHandlesAndVerifyNewWindowUrl(facebookLink,
                ConfigurationReader.getProperty("facebook.url"));
    }

    @Then("after user clicks youtube link current url matches expected youtube home page url")
    public void after_user_clicks_youtube_link_current_url_matches_expected_youtube_home_page_url() {
        BrowserUI_Utils.getWindowHandlesAndVerifyNewWindowUrl(youTubeLink,
                ConfigurationReader.getProperty("youtube.url"));
    }

    @Then("after user clicks buyMeACoffee link current url matches expected buyMeACoffee home page url")
    public void after_user_clicks_buyMeACoffee_link_current_url_matches_expected_buyMeACoffee_home_page_url() {
        BrowserUI_Utils.getWindowHandlesAndVerifyNewWindowUrl(buyMeACoffeeLink,
                ConfigurationReader.getProperty("buymeacoffee.url"));
    }
}
