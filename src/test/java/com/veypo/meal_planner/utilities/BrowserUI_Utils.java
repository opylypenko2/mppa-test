package com.veypo.meal_planner.utilities;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BrowserUI_Utils {
    // Methods are static --> Because we do not want to create an object to call those methods.
    // We just want to call those methods by the class name. That is why they are static type.

    /**
     * This method is used to pause the code for given seconds
     * It is static method we can call by class name
     * BrowserUtils.sleep(3);
     */
    public static void sleep(int seconds) {
        // 1 second = 1000 millis
        // millis = seconds*1000
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Exception happened in sleep method!");
        }
    }

    public static void verifyTitle(String expectedTitle) {
        String actualTitle = Driver.getDriver().getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    public static void verifyText(String expected, WebElement webElement) {
//        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(3L));
//        wait.until(ExpectedConditions.visibilityOf(webElement));
        Assert.assertTrue(webElement.isDisplayed());
        Assert.assertEquals(expected, webElement.getText());
    }

    public static void verifyCurrentUrl(WebElement webElement, String expectedCurrentUrl) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(3L));
        webElement.click();
        wait.until(ExpectedConditions.urlToBe(expectedCurrentUrl));
        String actualCurrentUrl = Driver.getDriver().getCurrentUrl();
        Assert.assertEquals(expectedCurrentUrl, actualCurrentUrl);
    }

    public static void verifyWebElementsTextAndCurrentUrlPath(List<WebElement> list, String expectedLinkText, String expectedPath) throws MalformedURLException {
        for (WebElement webElement : list) {
            Assert.assertTrue(webElement.isDisplayed());
            String actualLinkText = webElement.getText();
            Assert.assertEquals("Wrong Text", expectedLinkText, actualLinkText);
            webElement.click();
            verifyCurrentUrlPath(expectedPath);
        }
    }

    public static void verifyCurrentUrlPath(String expectedPath) throws MalformedURLException {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(3L));
        String host = ConfigurationReader.getProperty("url.ui");
        String expectedCurrentUrl = host + expectedPath;
        //    System.out.println("URL: " + expectedCurrentUrl);
        wait.until(ExpectedConditions.urlToBe(expectedCurrentUrl));
        String actualCurrentUrl = Driver.getDriver().getCurrentUrl();
        URL url = new URL(actualCurrentUrl);
        // Get path
        String actualPath = url.getPath();
        //    System.out.println("Path: " + actualPath);
        Assert.assertEquals(expectedPath, actualPath);
    }

    /**
     * return a list of string from a list of elements
     *
     * @param webElements of webElements
     * @return list of string
     */
    public static List<String> getElementsText(List<WebElement> webElements) {
        List<String> elemTexts = new ArrayList<>();

        for (WebElement element : webElements) {
            Assert.assertTrue(element.isDisplayed());
            elemTexts.add(element.getText());
        }

        return elemTexts;
    }

    public static void selectMenuOption(String expectedOption, List<WebElement> allOptions) {
        for (WebElement eachOption : allOptions) {
            if (eachOption.getText().equals(expectedOption)) {
                //            System.out.println(eachOption.getText());
                WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(3L));
                wait.until(ExpectedConditions.elementToBeClickable(eachOption));
                eachOption.click();
                break;
            }
        }
    }

    /**
     * Extracts text from list of elements matching the provided locator into new List<String>
     *
     * @return list of strings
     */
    public static List<String> getElementsText(By locator) {
        List<WebElement> elems = Driver.getDriver().findElements(locator);
        List<String> elemTexts = new ArrayList<>();

        for (WebElement el : elems) {
            elemTexts.add(el.getText());
        }
        return elemTexts;
    }

    /**
     * return a list of string from a list of elements
     *
     * @param list of webElements, String
     * @return list of string
     */
    public static List<String> getElementsAttribute(List<WebElement> list, String attribute) {
        List<String> elementAttributes = new ArrayList<>();

        for (WebElement el : list) {
            Assert.assertTrue(el.isDisplayed());
            elementAttributes.add(el.getAttribute(attribute));
        }
        return elementAttributes;
    }

    public static void getWindowHandlesAndVerifyNewWindowUrl(WebElement webElement, String expectedUrl) {
        String parentWindowHandle = Driver.getDriver().getWindowHandle();
        webElement.click();
        Set<String> allWindowHandles = Driver.getDriver().getWindowHandles();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(3L));

        for (String windowHandle : allWindowHandles) {
            if (!windowHandle.equals(parentWindowHandle)) {
                Driver.getDriver().switchTo().window(windowHandle);
                wait.until(ExpectedConditions.urlToBe(expectedUrl));
                Assert.assertEquals(expectedUrl, Driver.getDriver().getCurrentUrl());
                break;
            }
        }
        // Switch back to the parent window
        Driver.getDriver().switchTo().window(parentWindowHandle);
//--------------------------------------------------------------------------------
//  Another way:
//        String[] allWindowHandles =
//        allWindowHandles.toArray(new String[allWindowHandles.size()]);
//        String newWindow = allWindowHandles[allWindowHandles.length - 1];
//        Driver.getDriver().switchTo().window(newWindow);
//        wait.until(ExpectedConditions.urlToBe(expectedUrl));
//        Assert.assertEquals(expectedUrl, Driver.getDriver().getCurrentUrl());

        // Switch back to the parent window
//        Driver.getDriver().switchTo().window(parentWindowHandle);
//---------------------------------------------------------------------------------
    }

    public static void waitForInvisibilityOf(WebElement element) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(3L));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    /**
     * This method will accept dropdown as a WebElement
     * and return all the options' text in a List of String
     *
     * @return List<String>
     */
    public static List<String> dropdownOptionsAsString(WebElement dropdownElement) {
        Select select = new Select(dropdownElement);

        //List of all ACTUAL month <options> as a WebElement
        List<WebElement> actualOptionsAsWebElement = select.getOptions();

        //List of all ACTUAL month options as a String
        List<String> actualOptionsAsString = new ArrayList<>();

        // with using for loop we will convert each WebElement of options to String wit using getText() method
        // with using add() method we will add each String option in List<String> actual options as String
        for (WebElement each : actualOptionsAsWebElement) {
            actualOptionsAsString.add(each.getText());
        }

        return actualOptionsAsString;
    }

    /**
     * This method will accept a group of radio buttons as a List<WebElement>
     * it will loop through the List, and click to the radio button with provided attribute value
     */
    public static void clickRadioButton(List<WebElement> radioButtons, String attributeValue) {
        for (WebElement each : radioButtons) {
            if (each.getAttribute("value").equalsIgnoreCase(attributeValue)) {
                each.click();
            }
        }
    }

    /**
     * This method will accept a String as expected value and verify actual URL CONTAINS the value.
     */
    public static void verifyURLContains(String expectedInURL) {
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains(expectedInURL));
    }

    /*
    This method accepts 3 arguments.
    Arg1: webdriver
    Arg2: expectedInUrl : to verify if the url contains given String.
        - If condition matches, will break loop.
    Arg3: expectedInTitle to be compared against actualTitle
     */
    public static void switchWindowAndVerify(String expectedInUrl, String expectedInTitle) {
        Set<String> allWindowsHandles = Driver.getDriver().getWindowHandles();

        for (String each : allWindowsHandles) {
            Driver.getDriver().switchTo().window(each);
            System.out.println("Current URL: " + Driver.getDriver().getCurrentUrl());

            if (Driver.getDriver().getCurrentUrl().contains(expectedInUrl)) {
                break;
            }
        }
        //5. Assert:Title contains “expectedInTitle”
        String actualTitle = Driver.getDriver().getTitle();
        Assert.assertTrue(actualTitle.contains(expectedInTitle));
    }

    /**
     * Switches to new window by the exact title. Returns to original window if target title not found
     */
    public static void switchToWindow(String targetTitle) {
        String origin = Driver.getDriver().getWindowHandle();
        for (String handle : Driver.getDriver().getWindowHandles()) {
            Driver.getDriver().switchTo().window(handle);

            if (Driver.getDriver().getTitle().equals(targetTitle)) {
                return;
            }
        }
        Driver.getDriver().switchTo().window(origin);
    }

    /**
     * Moves the mouse to given element
     */
    public static void hover(WebElement element) {
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).perform();
    }

    /**
     * Performs a pause
     */
    public static void waitFor(long seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Waits for the provided element to be visible on the page
     */
    public static WebElement waitForVisibility(WebElement element, long timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeToWaitInSec));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits for element matching the locator to be visible on the page
     */
    public static WebElement waitForVisibility(By locator, long timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits for provided element to be clickable
     */
    public static WebElement waitForClickability(WebElement element, long timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits for element matching the locator to be clickable
     */
    public static WebElement waitForClickability(By locator, long timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * waits for backgrounds processes on the browser to complete
     */
    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeOutInSeconds));
            wait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }

    /**
     * Verifies whether the element matching the provided locator is displayed on page
     * throws AssertionError if the element matching the provided locator is not found or not displayed
     */
    public static void verifyElementDisplayed(By by) {
        try {
            Assert.assertTrue("Element not visible: " + by, Driver.getDriver().findElement(by).isDisplayed());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            Assert.fail("Element not found: " + by);
        }
    }

    /**
     * Verifies whether the element matching the provided locator is NOT displayed on page
     * throws AssertionError the element matching the provided locator is displayed
     */
    public static void verifyElementNotDisplayed(By by) {
        try {
            Assert.assertFalse("Element should not be visible: " + by, Driver.getDriver().findElement(by).isDisplayed());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    /**
     * Verifies whether the element is displayed on page
     * throws AssertionError if the element is not found or not displayed
     */
    public static void verifyElementDisplayed(WebElement element) {
        try {
            Assert.assertTrue("Element not visible: " + element, element.isDisplayed());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            Assert.fail("Element not found: " + element);

        }
    }

    /**
     * Waits for element to be not stale
     */
    public static void waitForStaleElement(WebElement element) {
        int y = 0;
        while (y <= 15) {
            if (y == 1)
                try {
                    element.isDisplayed();
                    break;
                } catch (StaleElementReferenceException st) {
                    y++;
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (WebDriverException we) {
                    y++;
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }
    }

    /**
     * Clicks on an element using JavaScript
     */
    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", element);
    }

    /**
     * Scrolls down to an element using JavaScript
     */
    public static void scrollToElement(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Performs double click action on an element
     */
    public static void doubleClick(WebElement element) {
        new Actions(Driver.getDriver()).doubleClick(element).build().perform();
    }

    /**
     * Changes the HTML attribute of a Web Element to the given value using JavaScript
     */
    public static void setAttribute(WebElement element, String attributeName, String attributeValue) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", element, attributeName, attributeValue);
    }

    /**
     * Highlights an element by changing its background and border color
     */
    public static void highlight(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
        waitFor(1);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].removeAttribute('style', 'background: yellow; border: 2px solid red;');", element);
    }

    /**
     * Checks or unchecks given checkbox
     */
    public static void selectCheckBox(WebElement element, boolean check) {
        if (check) {
            if (!element.isSelected()) {
                element.click();
            }
        } else {
            if (element.isSelected()) {
                element.click();
            }
        }
    }

    /**
     * attempts to click on provided element until given time runs out
     */
    public static void clickWithTimeOut(WebElement element, int timeout) {
        for (int i = 0; i < timeout; i++) {
            try {
                element.click();
                return;
            } catch (WebDriverException e) {
                waitFor(1);
            }
        }
    }

    /**
     * executes the given JavaScript command on given web element
     */
    public static void executeJSCommand(WebElement element, String command) {
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();
        jse.executeScript(command, element);
    }

    /**
     * executes the given JavaScript command on given web element
     */
    public static void executeJSCommand(String command) {
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();
        jse.executeScript(command);
    }

    /**
     * This method will recover in case of exception after unsuccessful the click,
     * and will try to click on element again.
     */
    public static void clickWithWait(By by, int attempts) {
        int counter = 0;
        //click on element as many as you specified in attempts parameter
        while (counter < attempts) {
            try {
                //selenium must look for element again
                clickWithJS(Driver.getDriver().findElement(by));
                //if click is successful - then break
                break;
            } catch (WebDriverException e) {
                //if click failed
                //print exception
                //print attempt
                e.printStackTrace();
                ++counter;
                //wait for 1 second, and try to click again
                waitFor(1);
            }
        }
    }

    /**
     * checks that an element is present on the DOM of a page. This does not
     * necessarily mean that the element is visible.
     */
    public static void waitForPresenceOfElement(By by, long time) {
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(time)).until(ExpectedConditions.presenceOfElementLocated(by));
    }
}
