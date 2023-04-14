@wip @ui
Feature: Login function

  Background:
    Given user is on the login page

  Scenario: Login with valid credentials
    When user logins with valid credentials
    Then the current url matches expected url

  Scenario: Login with valid credentials
    When user enters valid credentials
    Then login button is displayed and enabled and user clicks it
    Then the current url matches expected url
    Then the title is "Meal Planner"
    Then the account dropdown is displayed

  Scenario: Login with invalid credentials
    When user tries to login with invalid credentials
    Then login button is displayed and enabled and user clicks it
    Then alert message "Incorrect username or password" is displayed

  Scenario: Incorrect email address format
    When user enters incorrect email address format
    Then error message "Incorrect email address format" is displayed
    Then login button is disabled