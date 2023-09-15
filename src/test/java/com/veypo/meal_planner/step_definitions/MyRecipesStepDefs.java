package com.veypo.meal_planner.step_definitions;

import com.veypo.meal_planner.pages.CreateRecipePage;
import com.veypo.meal_planner.pages.MyRecipesPage;
import com.veypo.meal_planner.pages.UpdateRecipePage;
import com.veypo.meal_planner.pages.ViewRecipePage;
import com.veypo.meal_planner.utilities.BrowserUI_Utils;
import com.veypo.meal_planner.utilities.ConfigurationReader;
import com.veypo.meal_planner.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyRecipesStepDefs {

    MyRecipesPage myRecipesPage = new MyRecipesPage();
    ViewRecipePage viewRecipePage = new ViewRecipePage();
    CreateRecipePage createRecipePage = new CreateRecipePage();
    UpdateRecipePage updateRecipePage = new UpdateRecipePage();
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 30);

    @When("user clicks my recipes link")
    public void user_clicks_my_recipes_link() {
        myRecipesPage.myRecipesLink.click();
    }

    @When("user clicks add recipe menu")
    public void user_clicks_add_recipe_menu() {
        myRecipesPage.addRecipeMenu.click();
    }

    @When("after user chooses create new recipe option from a menu current url matches expected create recipe page url")
    public void after_user_chooses_create_new_recipe_option_from_a_menu_current_url_matches_expected_create_recipe_page_url() {
        BrowserUI_Utils.verifyCurrentUrl(myRecipesPage.createNewRecipeOption, ConfigurationReader.getProperty("url.ui") + ConfigurationReader.getProperty("create.recipe.page.path"));
    }

    @When("user enters new recipe name {string}")
    public void user_enters_new_recipe_name (String recipeName) {
        createRecipePage.recipeNameField.sendKeys(recipeName);
    }

    @And("user enters recipe description {string}")
    public void user_enters_recipe_description(String recipeDescription) {
        createRecipePage.recipeDescriptionField.sendKeys(recipeDescription);
    }

    @When("user can see and select following allergy options")
    public void user_can_see_and_select_following_allergy_options(List<String> expectedOptions) {
        List<String> actualOptions = BrowserUI_Utils.getElementsText(createRecipePage.allergyOptionsField);
        Assert.assertEquals(expectedOptions, actualOptions);

        for (WebElement each : createRecipePage.allergyOptionsField) {
            Assert.assertFalse(each.isSelected());
            each.click();
        }
    }

    @When("user clicks recipe select image button and chooses internet option")
    public void user_clicks_recipe_select_image_button_and_chooses_internet_option() {
        createRecipePage.recipeSelectImageBtn.click();
        createRecipePage.internetOption.click();
    }

    @And("user enters image url {string} and clicks next button")
    public void user_enters_image_url_and_clicks_next_button(String imgUrl) {
        createRecipePage.imageUrlField.sendKeys(imgUrl);
        createRecipePage.imageInternetNextBtn.click();
    }

    @And("user clicks crop button and then clicks save button")
    public void user_clicks_crop_button_and_then_clicks_save_button() {
        createRecipePage.cropBtn.click();
        createRecipePage.saveBtn.click();
    }

    @And("user enters new tag {string} and servings number {string} and clicks add ingredient button")
    public void user_enters_new_tag_and_servings_number_and_clicks_add_ingredient_button(String newTag, String servingsNum) {
        createRecipePage.newTagField.sendKeys(newTag);
        createRecipePage.servingsField.sendKeys(servingsNum);
        wait.until(ExpectedConditions.elementToBeClickable(createRecipePage.addIngredientBtn));
        createRecipePage.addIngredientBtn.click();
    }

    @And("user enters ingredient {string}, additional information {string} and quantity {string}")
    public void user_enters_ingredient_additional_information_and_quantity(String ingredient, String additionalInfo, String quantity) {
        createRecipePage.ingredientInputField.sendKeys(ingredient);
        createRecipePage.ingredientAdditionalInfoField.sendKeys(additionalInfo);
        createRecipePage.ingredientQuantityField.sendKeys(quantity);
    }

    @And("user selects measure option {string} from measure component and clicks add button")
    public void user_selects_measure_option_from_measure_component_and_clicks_add_button(String measureOption) {
        createRecipePage.ingredientMeasureMenu.click();
        BrowserUI_Utils.selectMenuOption(measureOption, createRecipePage.measureMenuOptions);
        wait.until(ExpectedConditions.elementToBeClickable(createRecipePage.ingredientsSectionAddBtn));
        createRecipePage.ingredientsSectionAddBtn.click();
    }

    @When("user clicks add step button")
    public void user_clicks_add_step_button() {
        createRecipePage.addStepBtn.click();
    }

    @When("user clicks step add image button and chooses internet option")
    public void user_clicks_step_add_image_button_and_chooses_internet_option() {
        createRecipePage.stepAddImageBtn.click();
        createRecipePage.internetOption.click();
    }

    @When("user enters following directions section inputs")
    public void user_enters_following_directions_section_inputs(Map<String, String> directionsInputs) {
        List<String> allValues = new ArrayList<>();
        for (Map.Entry<String, String> eachEntry : directionsInputs.entrySet()) {
            allValues.add(eachEntry.getValue());
        }
//        System.out.println(allValues);
        for (int i = 0; i < createRecipePage.directionsSectionInputs.size(); i++) {
            createRecipePage.directionsSectionInputs.get(i).sendKeys(allValues.get(i));
        }
    }

    @And("user enters additional information {string} and clicks add button")
    public void user_enters_additional_information_and_clicks_add_button(String additionalInfo) {
        createRecipePage.directionsAdditionalInfoField.sendKeys(additionalInfo);
        createRecipePage.directionsSectionAddBtn.click();
 //       BrowserUtils.sleep(10);
    }

    @When("user clicks notes tips section and then clicks add note tip button")
    public void user_clicks_notes_tips_section_and_then_clicks_add_note_tip_button() {
        createRecipePage.notesTipsSection.click();
        createRecipePage.addNoteTipBtn.click();
//        BrowserUtils.sleep(5);
    }

    @And("user clicks create button")
    public void user_clicks_create_button() {
        createRecipePage.createBtn.click();
    }

    @Then("recently created recipe is displayed")
    public void recently_created_recipe_is_displayed() {
        Assert.assertTrue(myRecipesPage.recentlyCreatedRecipe.isDisplayed());
    }

    @Then("after user clicks view {string} option in recipe card the recipe title matches expected recipe title {string}")
    public void after_user_clicks_view_option_in_recipe_card_the_recipe_title_matches_expected_recipe_title(String expectedElementText, String expectedRecipeTitle) {
        BrowserUI_Utils.selectMenuOption(expectedElementText, myRecipesPage.cardActions);
//        BrowserUtils.sleep(5);
        Assert.assertEquals(expectedRecipeTitle, myRecipesPage.recipeTitle.getText());
    }

    @Then("user selects delete {string} option in recipe view and deletes the recipe")
    public void user_selects_delete_option_and_deletes_the_recipe(String expectedElementText) {
        viewRecipePage.threeDotsMenuBtn.click();
        BrowserUI_Utils.selectMenuOption(expectedElementText, viewRecipePage.threeDotsMenuAllOptions);
        viewRecipePage.confirmationDeleteOption.click();
//        BrowserUtils.sleep(5);
    }

    @And("user enters recipe name {string} into my recipes search field and related saved recipe is displayed")
    public void user_enters_recipe_name_into_my_recipes_search_field_and_related_saved_recipe_is_displayed(String expectedRecipeName) {
        myRecipesPage.myRecipesSearchField.sendKeys(expectedRecipeName);
        Assert.assertTrue(myRecipesPage.savedRecipe.isDisplayed());
        Assert.assertEquals(expectedRecipeName, myRecipesPage.savedRecipe.getText());
    }

    @Then("user selects edit {string} option in recipe view")
    public void user_selects_edit_option_in_recipe_view(String expectedElementText) {
        viewRecipePage.threeDotsMenuBtn.click();
        BrowserUI_Utils.selectMenuOption(expectedElementText, viewRecipePage.threeDotsMenuAllOptions);
    }

    @And("user saves updated recipe")
    public void user_saves_updated_recipe() {
        createRecipePage.saveBtn.click();
    }

    @Then("recipe description matches expected updated recipe description {string}")
    public void recipe_description_matches_expected_updated_recipe_description(String expectedUpdatedRecipeDescription) {
        Assert.assertEquals(expectedUpdatedRecipeDescription, createRecipePage.recipeDescriptionField.getAttribute("value")); //to get the value of fields(textarea in this case), we need to fetch the value using the getAttribute() method
    }

    @Then("user selects delete option in update recipe and deletes the recipe")
    public void user_selects_delete_option_in_update_recipe_and_deletes_the_recipe() {
        updateRecipePage.threeDotsMenuBtn.click();
        updateRecipePage.deleteRecipeOption.click();
        viewRecipePage.confirmationDeleteOption.click();
    }

//---------------------------------------------------------------------
//Tartar Sauce --> recipe name
//Homemade Tartar Sauce is way better than any store-bought version. This has just the right taste and texture. It's perfect with fish sticks, crab or salmon cakes. --> recipe description
// https://www.wholesomeyum.com/wp-content/uploads/2021/02/wholesomeyum-keto-tartar-sauce-recipe-14.jpg  --> image url
// KETO-FRIENDLY --> new tag
// Mayonnaise --> ingredient
// Hellmann's Real Mayonnaise --> additional info
// 1. Combine all ingredients in a bowl and mix well. --> directions steps
// 2. Add more lemon juice or pickles if desired.

// https://i2.wp.com/www.downshiftology.com/wp-content/uploads/2021/02/Best-Guten-Free-Recipes-3.jpg

// https://i2.wp.com/www.downshiftology.com/wp-content/uploads/2021/01/Baked-Salmon-9.jpg
//---------------------------------------------------------------------
}
