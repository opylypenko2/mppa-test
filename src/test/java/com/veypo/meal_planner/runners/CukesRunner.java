package com.veypo.meal_planner.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/cucumber-report.html", //html report
                "me.jvt.cucumber.report.PrettyReports:target/cucumber",
                "json:target/cucumber.json",
                "rerun:target/rerun.txt"}, //cucumber report
        features = "src/test/resources/features", //scenarios location
        glue = "com/veypo/meal_planner/step_definitions", //implementation
        dryRun = false,
        tags = "@wip and @ui"
)
public class CukesRunner {
}
// dryRun = true, --> to get snippets of unimplemented steps
// dryRun = false, --> to run
// tags:
//  "@wip and @ui"
//  "@smoke"
//  "@regression"
//  "@api"
//  "@db"
