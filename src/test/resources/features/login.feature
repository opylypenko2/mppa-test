# @regression @ui
#@wip @ui
Feature: Login function

  Background:
    Given user is on the login page

 # @smoke
  Scenario: Login with valid credentials
    # use step "When user logs in with valid credentials" in other
    # places (it's already implemented)
    When user enters valid credentials
    Then login button is displayed
    Then login button is enabled
    Then user clicks login button
    Then the current url matches expected url
    Then user should see the account menu link "Account"

  Scenario Outline: Login with invalid credentials
    When user enters invalid credentials
    Then login button is displayed
    Then login button is enabled
    Then user clicks login button
    Then alert message "<alert message>" is displayed
    Examples:
      | alert message                  |
      | Incorrect username or password |

  Scenario: Incorrect email address format
    When user enters incorrect email address format
    Then field error message "Incorrect email address format" is displayed
    Then login button is displayed
    Then login button is disabled
