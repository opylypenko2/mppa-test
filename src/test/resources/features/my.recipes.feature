@wip @ui
Feature: Create a new recipe flow

  Background:
    Given user is on the home page

 # @wip @ui
  Scenario Outline: User can create, update and delete a recipe
    When user clicks login link
    And user logs in with valid credentials
    When user clicks my recipes link
    And user clicks add recipe menu
    And after user chooses create new recipe option from a menu current url matches expected create recipe page url
    When user enters new recipe name
    And user enters recipe description "<recipeDescription1>"
    And user can see and select following allergy options
      | GLUTEN-FREE |
      | DAIRY-FREE  |
      | VEGETARIAN  |
      | VEGAN       |
      | NUT-FREE    |
    When user clicks recipe select image button and chooses internet option
    And user enters image url "<recipeImageUrl>" and clicks next button
    And user clicks crop button and then clicks save button
    And user enters new tag "<newTag>" and servings number "<servingsNumber>"
    And user clicks add ingredient button
    And user enters ingredient "<ingredient>", additional information "<ingredientAdditionalInfo>" and quantity "<quantity>"
    And user selects measure option "<measureOption>" from measure component and clicks add button
    When user clicks add step button
    When user clicks step add image button and chooses internet option
    And user enters image url "<stepImageUrl>" and clicks next button
    And user clicks crop button and then clicks save button
    And user enters following directions section inputs
      | step    | Mix |
      | hours   | 1   |
      | minutes | 5   |
      | seconds | 15  |
    And user enters additional information "<stepAdditionalInfo>" and clicks add button
  #  When user clicks notes tips section and then clicks add note tip button
    # Steps for add section //TODO
    # Add more steps for notes/tips    //TODO
    And user clicks create button
    Then recently created recipe is displayed
    Then user enters recipe name into my recipes search field
    Then after user clicks view "View" option in recipe card the recipe title matches expected recipe title
    When user selects edit "Edit" option in recipe view
    And user enters recipe description "<recipeDescription2>" to update recipe
    And user saves updated recipe
    Then after user clicks view "View" option in recipe card the recipe title matches expected recipe title
    Then recipe description matches expected updated recipe description "<recipeDescription2>"
    Then user selects delete "Delete" option in recipe view and deletes the recipe
    Then user logs out

    Examples:
      | recipeDescription1                              | recipeDescription2             | newTag        | servingsNumber | ingredient | ingredientAdditionalInfo  | quantity | measureOption | stepAdditionalInfo     | recipeImageUrl                                                                                       | stepImageUrl                                                                                      |
      | Perfect with fish sticks, crab or salmon cakes. | Every chocolate lover's dream. | KETO-FRIENDLY | 4              | Mayonnaise | Hellman's Real Mayonnaise | 1        | Cup           | Refrigerate when done. | https://www.wholesomeyum.com/wp-content/uploads/2021/02/wholesomeyum-keto-tartar-sauce-recipe-14.jpg | https://i2.wp.com/www.downshiftology.com/wp-content/uploads/2021/02/Best-Guten-Free-Recipes-3.jpg |



#  Scenario Outline: User can update recipe
#    When user clicks login link
#    And user logs in with valid credentials
#    When user clicks my recipes link
#    And user clicks add recipe menu
#    And after user chooses create new recipe option from a menu current url matches expected create recipe page url
#    When user enters new recipe name
#    And user clicks create button
#    Then user enters recipe name into my recipes search field and related saved recipe is displayed
#    Then after user clicks view "View" option in recipe card the recipe title matches expected recipe title
#    When user selects edit "Edit" option in recipe view
#    And user enters recipe description "<recipeDescription>" to update recipe
#    And user saves updated recipe
#    Then after user clicks view "View" option in recipe card the recipe title matches expected recipe title
#    Then recipe description matches expected updated recipe description "<recipeDescription>"
#    Then user selects delete "Delete" option in recipe view and deletes the recipe
#
#    Examples:
#      | recipeDescription              |
#      | Every chocolate lover's dream. |
