package com.veypo.meal_planner.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Driver {
    /* InheritableThreadLocal  --> this is like a container, bag, pool.
       In this pool we can have separate objects for each thread.
       For each thread in InheritableThreadLocal we can have separate object for that thread.
       Driver class will provide separate webdriver object per thread.
     */
    private static final InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();

    private Driver() {
    }

    public static WebDriver getDriver() {
        //if this thread doesn't have driver - create it and add to pool
        if (driverPool.get() == null) {

            // if we pass the driver from terminal, then use that one
            // if we do not pass the driver from terminal, then use the one from properties file
            String browser = System.getProperty("browser") != null ? browser = System.getProperty("browser") :
                    ConfigurationReader.getProperty("browser");

            switch (browser) {
                case "remote-chrome" -> {
                    try {
                        // assign your grid server address
                        String gridAddress = "174.129.57.20";
                        URL url = new URL("https://" + gridAddress + ":4444/wd/hub");
                        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                        desiredCapabilities.setBrowserName("chrome");
                        driverPool.set(new RemoteWebDriver(url, desiredCapabilities));
                        //driverPool.set(new RemoteWebDriver(new URL("http://0.0.0.0:4444/wd/hub"),desiredCapabilities));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                case "headless-chrome" -> {
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                    options.addArguments("--headless=new");
                    driverPool.set(new ChromeDriver(options));
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(3L));
                }
                case "chrome" -> {
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver());
                }
                case "chrome-headless" -> {
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver(new ChromeOptions().addArguments("--headless=new")));
                }
                case "firefox" -> {
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                }
                case "firefox-headless" -> {
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver(new FirefoxOptions().addArguments("--headless=new")));
                }
                case "ie" -> {
                    if (!System.getProperty("os.name").toLowerCase().contains("windows"))
                        throw new WebDriverException("Your OS doesn't support Internet Explorer");
                    WebDriverManager.iedriver().setup();
                    driverPool.set(new InternetExplorerDriver());
                }
                case "edge" -> {
                    if (!System.getProperty("os.name").toLowerCase().contains("windows"))
                        throw new WebDriverException("Your OS doesn't support Edge");
                    WebDriverManager.edgedriver().setup();
                    driverPool.set(new EdgeDriver());
                }
                case "safari" -> {
                    if (!System.getProperty("os.name").toLowerCase().contains("mac"))
                        throw new WebDriverException("Your OS doesn't support Safari");
                    WebDriverManager.getInstance(SafariDriver.class).setup();
                    driverPool.set(new SafariDriver());
                }
            }
        }

        return driverPool.get();
    }

    public static void closeDriver() {
        driverPool.get().quit();
        driverPool.remove();
    }
    /*
        Headless is an execution mode for Firefox and Chromium based browsers. It allows users to run automated scripts
        in headless mode, meaning that the browser window would not be visible. In most of Selenium’s bindings there is
        a convenience method to set this execution mode while setting the browser options. However, in Selenium 4.8.0
        this method will be deprecated and now users need to set it through arguments when setting the browser options.
        Before:
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(true);
            WebDriver driver = new ChromeDriver(options);
            driver.get("https://selenium.dev");
            driver.quit();

       After:
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            WebDriver driver = new ChromeDriver(options);
            driver.get("https://selenium.dev");
            driver.quit();
    */
}
