package com.veypo.meal_planner.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        // location of failed scenarios:
        features = "@target/rerun.txt",
        // implementation:
        glue = "com/veypo/meal_planner/step_definitions"
)
public class FailedTestRunner {
    // This runner class will run only failed scenarios
}
