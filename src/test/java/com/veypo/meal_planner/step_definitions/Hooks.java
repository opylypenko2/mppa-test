package com.veypo.meal_planner.step_definitions;

import com.veypo.meal_planner.utilities.ConfigurationReader;
import com.veypo.meal_planner.utilities.DB_Utils;
import com.veypo.meal_planner.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.util.concurrent.TimeUnit;
import static io.restassured.RestAssured.baseURI;

public class Hooks {

    // For DB
    @Before("@db")
    public void dbHook() {
        System.out.println("--> HOOKS --> creating database connection");
        DB_Utils.createConnection();
    }

    @After("@db")
    public void afterDbHook() {
        System.out.println("--> HOOKS --> closing database connection");
        DB_Utils.destroy();
    }

    // For UI
    @Before("@ui")
    public void setUp() {
        // we put the logic that should apply to every scenario
        Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Driver.getDriver().manage().window().maximize();
//        Driver.getDriver().get(ConfigurationReader.getProperty("root.url"));
    }

    @After("@ui")
    public void tearDown(Scenario scenario) {
        // only takes a screenshot if the scenario fails
        if (scenario.isFailed()) {
            // taking a screenshot
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshot");
        }
        Driver.closeDriver();
    }

    // For API
    @Before("@api")
    public static void init() {
        baseURI = ConfigurationReader.getProperty("url.api");
    }
}

/*
  In the context of automated testing with frameworks like Cucumber, @Before and @After annotations are used to
  specify actions that should be performed before and after EACH SCENARIO or FEATURE EXECUTION.
  These annotations are typically used with Step Definition methods to set up and tear down the test environment.

    - @Before annotation :
        - is used to mark a method that should run BEFORE EACH SCENARIO in a Cucumber feature file.
        - is often used for setting up the test environment, initializing variables, or performing any pre-test actions.
        - Methods annotated with @Before are executed ONCE FOR EACH SCENARIO, ensuring that the test environment is in
          the desired state before the scenario begins.

    - @After annotation :
        - is used to mark a method that should run AFTER EACH SCENARIO in a Cucumber feature file.
        - is often used for cleaning up resources, closing connections, or performing post-test actions.
        - Methods annotated with @After are executed ONCE FOR EACH SCENARIO, ensuring that any cleanup tasks are
          performed after the scenario execution.
 */
