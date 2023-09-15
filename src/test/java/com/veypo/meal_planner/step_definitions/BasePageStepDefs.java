package com.veypo.meal_planner.step_definitions;

import com.veypo.meal_planner.pages.BasePage;
import com.veypo.meal_planner.utilities.BrowserUI_Utils;
import com.veypo.meal_planner.utilities.ConfigurationReader;
import com.veypo.meal_planner.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class BasePageStepDefs extends BasePage {

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

    @Then("the following footer links are displayed")
    public void the_following_footer_links_are_displayed(List<String> expectedFooterLinks) {
        List<String> actualFooterLinks = BrowserUI_Utils.getElementsText(footerLinks);
        Assert.assertEquals(expectedFooterLinks, actualFooterLinks);
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

    @Then("after user clicks terms and conditions link current url matches expected terms and conditions page url")
    public void after_user_clicks_terms_and_conditions_link_current_url_matches_expected_terms_and_conditions_page_url() {
        BrowserUI_Utils.verifyCurrentUrl(
                termsLink, ConfigurationReader.getProperty("url.ui") + ConfigurationReader.getProperty("terms.page.path"));
    }

    @Then("after user clicks privacy policy link current url matches expected privacy policy page url")
    public void after_user_clicks_privacy_policy_link_current_url_matches_expected_privacy_policy_page_url() {
        BrowserUI_Utils.verifyCurrentUrl(
                privacyLink, ConfigurationReader.getProperty("url.ui") + ConfigurationReader.getProperty("privacy.page.path"));
    }

    @Then("after user clicks cookies policy link current url matches expected cookies policy page url")
    public void after_user_clicks_cookies_policy_link_current_url_matches_expected_cookies_policy_page_url() {
        BrowserUI_Utils.verifyCurrentUrl(
                cookiesLink, ConfigurationReader.getProperty("url.ui") + ConfigurationReader.getProperty("cookies.page.path"));
    }

    @Then("after user clicks third party licenses link current url matches expected third party licenses page url")
    public void after_user_clicks_third_party_licenses_link_current_url_matches_expected_third_party_licenses_page_url() {
        BrowserUI_Utils.verifyCurrentUrl(
                thirdPartyLink, ConfigurationReader.getProperty("url.ui") + ConfigurationReader.getProperty("thirdparty.page.path"));
    }

    @Then("after user clicks demo link current url matches expected demo page url")
    public void after_user_clicks_demo_link_current_url_matches_expected_demo_page_url() {
        BrowserUI_Utils.verifyCurrentUrl(
                demoLink, ConfigurationReader.getProperty("url.ui") + ConfigurationReader.getProperty("demo.page.path"));
    }

    @Then("after user clicks help center link current url matches expected help center page url")
    public void after_user_clicks_help_center_link_current_url_matches_expected_help_center_page_url() {
        BrowserUI_Utils.verifyCurrentUrl(
                helpCenterLink, ConfigurationReader.getProperty("url.ui") + ConfigurationReader.getProperty("help.page.path"));
    }

    @Then("after user clicks developer hub link current url matches expected developers page url")
    public void after_user_clicks_developer_hub_link_current_url_matches_expected_developers_page_url() {
        BrowserUI_Utils.verifyCurrentUrl(
                developerHubLink, ConfigurationReader.getProperty("url.ui") + ConfigurationReader.getProperty("developers.page.path"));
    }

    @Then("after clicking feedback link user should see dialog window for providing feedback")
    public void after_clicking_feedback_link_user_should_see_dialog_window_for_providing_feedback() {
        feedbackLink.click();
        textArea.sendKeys("Awesome app");
        submitBtn.click();
        closeBtn.click();
   }

    @Then("after user clicks about us link current url matches expected about us page url")
    public void after_user_clicks_about_us_link_current_url_matches_expected_about_us_page_url() {
        BrowserUI_Utils.verifyCurrentUrl(
                aboutUsLink, ConfigurationReader.getProperty("url.ui") + ConfigurationReader.getProperty("about.page.path"));
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
