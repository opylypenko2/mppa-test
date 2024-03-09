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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.*;

public class BasePageStepDefs extends BasePage {

    @Given("user is on the home page")
    public void user_is_on_the_home_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url.ui"));
    }

    @Then("user should see the app name link {string}")
    public void user_should_see_the_app_name_link(String expectedLink) {
        BrowserUI_Utils.verifyText(expectedLink, appName);
    }

    @Then("the following account menu items are displayed and reachable")
    public void the_following_account_menu_items_are_displayed_and_reachable() {
        //TODO
    }

    @Then("the following header links are displayed and reachable")
    public void the_following_header_links_are_displayed_and_reachable(List<String> expectedLinks) {
        List<String> actualLinks = BrowserUI_Utils.getElementsText(headerLinks);
        Assert.assertEquals(expectedLinks, actualLinks);
        //TODO
    }

    @When("user clicks login link")
    public void user_clicks_login_link() {
        loginLink.click();
    }

    @Then("user should see my recipes link {string}")
    public void user_should_see_my_recipes_link(String expectedLink) {
        BrowserUI_Utils.verifyText(expectedLink, myRecipesLink);
    }

    @Then("the following social network apps links are displayed and reachable")
    public void the_following_social_network_apps_links_are_displayed_and_reachable(Map<String, String> expectedLinksTextAndUrl) {
        Map<String, String> actualLinksTextAndUrl = new LinkedHashMap<>();

        List<String> actualLinksUrls = new LinkedList<>();
        List<String> expectedLinksUrls = new LinkedList<>(expectedLinksTextAndUrl.values());
        System.out.println("expectedLinksUrls = " + expectedLinksUrls);

        for (WebElement link : socialNetworkLinks) {
            Assert.assertTrue(link.isDisplayed());
            actualLinksTextAndUrl.put(link.getAttribute("title"), link.getAttribute("href"));

            // Get the current window handle:
            String originalWindowHandle = Driver.getDriver().getWindowHandle();
            // Open a new window:
            link.click();
            // Get all window handles:
            Set<String> allWindowHandles = Driver.getDriver().getWindowHandles();
            System.out.println("allWindowHandles = " + allWindowHandles);

            // Switch to the new window:
            for (String windowHandle : allWindowHandles) {
                if (!originalWindowHandle.equals(windowHandle)) {
                    Driver.getDriver().switchTo().window(windowHandle);
                    break;
                }
            }

            // Perform actions on the new window:
            String actualLinkUrl = Driver.getDriver().getCurrentUrl();
            actualLinksUrls.add(actualLinkUrl);

            // Close the current window
            Driver.getDriver().close();

            // Switch back to the original window:
            Driver.getDriver().switchTo().window(originalWindowHandle);
        }

        System.out.println("actualLinksTextAndUrl = " + actualLinksTextAndUrl);
        Assert.assertEquals(expectedLinksTextAndUrl, actualLinksTextAndUrl);
        System.out.println("actualLinksUrls = " + actualLinksUrls);
        Assert.assertEquals(expectedLinksUrls, actualLinksUrls);


//        // Find Broken Links
//        // Iterate through the links and check their HTTP status:
//        for (WebElement link : socialNetworkLinks) {
//            String url = link.getAttribute("href");
//            try {
//                HttpURLConnection connection = (HttpURLConnection) (new URL(url).openConnection());
//
//                // The HTTP HEAD method lets you submit a request and receive a response with the response body omitted,
//                // making it efficient for checking resource existence or status:
//                // httpURLConnection.setRequestMethod("HEAD");
//                // However, some sites (e.g., Twitter/X) block HEAD requests, returning a 403 Forbidden error.
//                // In these cases, fallback to the GET method, which downloads the full response body but
//                // ensures compatibility across all websites (cons-can impact performance, server load, may encounter
//                // rate limiting issues). Some websites block requests from unknown User-Agents, so this approach might
//                // not work for all links that you're trying to check.
//                connection.setRequestMethod("GET");
//                connection.setConnectTimeout(10000);
//                connection.connect();
//                int responseCode = connection.getResponseCode();
//
//                if (responseCode >= 400) {
//                    System.out.printf("%s is a broken link (status code: %d)%n", url, responseCode);
//                } else {
//                    System.out.println(url + " is a valid link.");
//                }
//            } catch (Exception e) {
//                System.out.println("Error in checking link: " + url);
//            }
//        }
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
}

//  To wait for the page to load when navigating to a new page via URL:
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
//        WebDriver driver = new ChromeDriver(chromeOptions);
