Feature: My Recipes

  Background:
    Given user is on the home page

  Scenario: User can create a recipe
    When user clicks login link
    When user logs in with valid credentials
    And user clicks my recipes button