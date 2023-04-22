package com.veypo.meal_planner.step_definitions;

import com.veypo.meal_planner.utilities.Driver;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class HomePageStepDefs {

    @Then("the title is {string}")
    public void the_title_is(String expectedTitle) {
        String actualTitle = Driver.getDriver().getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
    }
}
