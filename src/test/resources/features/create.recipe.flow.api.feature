@api
Feature: Create Recipe Flow API

  Scenario: [API] User can create, update, and delete recipe
    When user logs in with valid credentials over API
    When user creates new recipe over API
    And user checks if new recipe is created over API
    When user updates created recipe over API
    And user checks if new recipe is updated over API
    Then user deletes created recipe over API
    And user checks if recipe is deleted over API
    Then user logs out over API
