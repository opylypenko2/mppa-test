package com.veypo.meal_planner.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/cucumber-report.html", //html report
                "me.jvt.cucumber.report.PrettyReports:target/cucumber",
                "json:target/cucumber.json",
                "rerun:target/rerun.txt"}, // cucumber report
        features = "src/test/resources/features",
        glue = "com/veypo/meal_planner/step_definitions",
        dryRun = false,
        tags = "@wip and @ui"
)
public class CukesRunner {
}

// tags:
//  "@wip and @ui"
//  "@smoke"
//  "@regression"