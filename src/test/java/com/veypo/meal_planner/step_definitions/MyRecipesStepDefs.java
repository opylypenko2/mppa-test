package com.veypo.meal_planner.step_definitions;

import com.veypo.meal_planner.pages.*;
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

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MyRecipesStepDefs {

    MyRecipesPage myRecipesPage = new MyRecipesPage();
    ViewRecipePage viewRecipePage = new ViewRecipePage();
    CreateRecipePage createRecipePage = new CreateRecipePage();
    UpdateRecipePage updateRecipePage = new UpdateRecipePage();
    LoginPage loginPage = new LoginPage();

    // Generate a random UUID :
    public String uniqueRecipeName = UUID.randomUUID().toString();
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5L));

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
        BrowserUI_Utils.verifyCurrentUrl(myRecipesPage.createNewRecipeOption,
                ConfigurationReader.getProperty("url.ui") + ConfigurationReader.getProperty("create.recipe.page.path"));
    }

    @When("user enters new recipe name")
    public void user_enters_new_recipe_name() {
        createRecipePage.recipeNameField.sendKeys(uniqueRecipeName);
    }

    @And("user enters recipe description {string}")
    public void user_enters_recipe_description(String recipeDescription) {
        createRecipePage.recipeDescriptionField.sendKeys(recipeDescription);
        //    BrowserUI_Utils.sleep(3);
    }

    @When("user can see and select following allergy options")
    public void user_can_see_and_select_following_allergy_options(List<String> expectedOptions) {
        List<String> actualOptions = BrowserUI_Utils.getElementsText(createRecipePage.allergyOptionsField);
        Assert.assertEquals(expectedOptions, actualOptions);

        for (WebElement each : createRecipePage.allergyOptionsField) {
            Assert.assertFalse(each.isSelected());
            each.click();
        }
        //    BrowserUI_Utils.sleep(3);
    }

    @When("user clicks recipe select image button and chooses internet option")
    public void user_clicks_recipe_select_image_button_and_chooses_internet_option() {
        createRecipePage.recipeSelectImageBtn.click();
        createRecipePage.internetOption.click();
        //    BrowserUI_Utils.sleep(3);
    }

    @And("user enters image url {string} and clicks next button")
    public void user_enters_image_url_and_clicks_next_button(String imgUrl) {
        createRecipePage.imageUrlField.sendKeys(imgUrl);
        createRecipePage.imageInternetNextBtn.click();
        //    BrowserUI_Utils.sleep(3);
    }

    @And("user clicks crop button and then clicks save button")
    public void user_clicks_crop_button_and_then_clicks_save_button() {
        createRecipePage.imgCropBtn.click();
        createRecipePage.imgSaveBtn.click();
        //    BrowserUI_Utils.sleep(3);
    }

    @And("user enters new tag {string} and servings number {string}")
    public void user_enters_new_tag_and_servings_number_and_clicks_add_ingredient_button(String newTag, String servingsNum) {
        createRecipePage.newTagField.sendKeys(newTag);
        createRecipePage.servingsField.sendKeys(servingsNum);
        //    BrowserUI_Utils.sleep(3);
    }

    @And("user clicks add ingredient button")
    public void user_clicks_add_ingredient_button() {
        wait.until(ExpectedConditions.elementToBeClickable(createRecipePage.addIngredientBtn));
        BrowserUI_Utils.sleep(1);
        createRecipePage.addIngredientBtn.click();
    }

    @And("user enters ingredient {string}, additional information {string} and quantity {string}")
    public void user_enters_ingredient_additional_information_and_quantity(String ingredient, String additionalInfo, String quantity) {
        createRecipePage.ingredientInputField.sendKeys(ingredient);
        createRecipePage.ingredientAdditionalInfoField.sendKeys(additionalInfo);
        createRecipePage.ingredientQuantityField.sendKeys(quantity);
        //    BrowserUI_Utils.sleep(3);
    }

    @And("user selects measure option {string} from measure component and clicks add button")
    public void user_selects_measure_option_from_measure_component_and_clicks_add_button(String measureOption) {
        createRecipePage.ingredientMeasureMenu.click();
        BrowserUI_Utils.selectMenuOption(measureOption, createRecipePage.measureMenuOptions);
        wait.until(ExpectedConditions.elementToBeClickable(createRecipePage.ingredientsSectionAddBtn));
        createRecipePage.ingredientsSectionAddBtn.click();
        //    BrowserUI_Utils.sleep(3);
    }

    @When("user clicks add step button")
    public void user_clicks_add_step_button() {
        createRecipePage.addStepBtn.click();
    }

    @When("user clicks step add image button and chooses internet option")
    public void user_clicks_step_add_image_button_and_chooses_internet_option() {
        createRecipePage.stepAddImageBtn.click();
        createRecipePage.internetOption.click();
        //    BrowserUI_Utils.sleep(3);
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
        //    BrowserUI_Utils.sleep(3);
    }

    @And("user enters additional information {string} and clicks add button")
    public void user_enters_additional_information_and_clicks_add_button(String additionalInfo) {
        createRecipePage.directionsAdditionalInfoField.sendKeys(additionalInfo);
        createRecipePage.directionsSectionAddBtn.click();
        //    BrowserUI_Utils.sleep(3);
    }

//    @When("user clicks notes tips section and then clicks add note tip button")
//    public void user_clicks_notes_tips_section_and_then_clicks_add_note_tip_button() {
//        createRecipePage.notesTipsSection.click();
//        createRecipePage.addNoteTipBtn.click();
//        BrowserUI_Utils.sleep(3);
//    }

    @And("user clicks create button")
    public void user_clicks_create_button() {
        createRecipePage.createBtn.click();
    }

    @Then("recently created recipe is displayed")
    public void recently_created_recipe_is_displayed() {
        Assert.assertTrue(myRecipesPage.recentlyCreatedRecipe.isDisplayed());
        //    BrowserUI_Utils.sleep(3);
    }

    @Then("user enters recipe name into my recipes search field")
    public void user_enters_recipe_name_into_my_recipes_search_field() {
        myRecipesPage.myRecipesSearchField.sendKeys(uniqueRecipeName);
        BrowserUI_Utils.sleep(3);
        Assert.assertTrue(myRecipesPage.savedRecipe.isDisplayed());
    }

    @Then("after user clicks view {string} option in recipe card the recipe title matches expected recipe title")
    public void
    after_user_clicks_view_option_in_recipe_card_the_recipe_title_matches_expected_recipe_title(String expectedElementText) {
        BrowserUI_Utils.selectMenuOption(expectedElementText, myRecipesPage.cardActions);
        BrowserUI_Utils.sleep(3);
        Assert.assertEquals(uniqueRecipeName, viewRecipePage.recipeTitle.getText());
    }

    @When("user selects edit {string} option in recipe view")
    public void user_selects_edit_option_in_recipe_view(String expectedElementText) {
        viewRecipePage.threeDotsMenuBtn.click();
        BrowserUI_Utils.selectMenuOption(expectedElementText, viewRecipePage.threeDotsMenuAllOptions);
        //    BrowserUI_Utils.sleep(3);
    }

    @And("user enters recipe description {string} to update recipe")
    public void user_enters_recipe_description_to_update_recipe(String recipeDescription) {
        // to replace the previous input rather than adding to it, we should first clear the input field
        // before sending the new keys:
        // 1. Clear the existing value in the input field using the clear() method.
        // 2. Send the new keys to the input field using the sendKeys() method.
        updateRecipePage.recipeDescriptionField.clear();
        updateRecipePage.recipeDescriptionField.sendKeys(recipeDescription);
        BrowserUI_Utils.sleep(3);
    }

    @And("user saves updated recipe")
    public void user_saves_updated_recipe() {
        updateRecipePage.updateRecipeSaveBtn.click();
        BrowserUI_Utils.sleep(3);
    }

    @Then("recipe description matches expected updated recipe description {string}")
    public void recipe_description_matches_expected_updated_recipe_description(String expectedUpdatedRecipeDescription) {
        Assert.assertEquals(expectedUpdatedRecipeDescription, viewRecipePage.recipeDescription.getText());
        //to get the value of fields(textarea in this case), we need to fetch the value using the getAttribute() method:
        // Assert.assertEquals(expectedUpdatedRecipeDescription, createRecipePage.recipeDescriptionField.getAttribute("value"));
    }

    @Then("user selects delete {string} option in recipe view and deletes the recipe")
    public void user_selects_delete_option_and_deletes_the_recipe(String expectedElementText) {
        viewRecipePage.threeDotsMenuBtn.click();
        BrowserUI_Utils.selectMenuOption(expectedElementText, viewRecipePage.threeDotsMenuAllOptions);
        viewRecipePage.confirmationDeleteOption.click();
        BrowserUI_Utils.sleep(3);
    }

    @Then("user logs out")
    public void user_logs_out() {
        loginPage.logout();
    }

//---------------------------------------------------------------------
//Tartar Sauce --> recipe name
//Homemade Tartar Sauce is way better than any store-bought version. This has just the right taste and texture. It's perfect with fish sticks, crab or salmon cakes. --> recipe description
// https://www.wholesomeyum.com/wp-content/uploads/2021/02/wholesomeyum-keto-tartar-sauce-recipe-14.jpg  --> image url
// KETO-FRIENDLY --> new tag
// Mayonnaise --> ingredient
// Hellman's Real Mayonnaise --> additional info
// 1. Combine all ingredients in a bowl and mix well. --> directions steps
// 2. Add more lemon juice or pickles if desired.

// https://i2.wp.com/www.downshiftology.com/wp-content/uploads/2021/02/Best-Guten-Free-Recipes-3.jpg

// https://i2.wp.com/www.downshiftology.com/wp-content/uploads/2021/01/Baked-Salmon-9.jpg
//---------------------------------------------------------------------
}
