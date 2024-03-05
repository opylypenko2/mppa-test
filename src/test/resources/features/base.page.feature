# @regression @ui
# @wip @ui
Feature: Base Page

  Background:
    Given user is on the home page


  Scenario: Before logging in/signing up user should be able to see and reach the following header links
    Then the following header links are displayed and reachable
      | Meal Planner |
      | My Recipes   |
      | Log in       |
      | Sign up      |


  Scenario: After logging in user should be able to see and reach the following header links
    When user clicks login link
    When user logs in with valid credentials
    Then user should see the account menu "Account"
    Then the following account menu items are displayed and reachable
      | Settings |
      | Log out  |
    Then the following header links are displayed and reachable
      | Meal Planner |
      | My Recipes   |


  @wip @ui
  Scenario: User should see and be able to reach social network apps links
    Then the following social network apps links are displayed and reachable
      | X               |
      | Facebook        |
      | Instagram       |
      | YouTube         |
      | Buy Me a Coffee |


  Scenario: User should see the footer columns headers
    Then the following footer columns headers are displayed
      | Legal Policies |
      | Product        |
      | VEYPO LLC      |

#  @wip @ui
  Scenario: User should see and be able to reach the following footer links
    Then the following footer links are displayed and reachable
      | Terms and Conditions | /legal/terms     |
      | Privacy Policy       | /legal/privacy   |
      | Cookies Policy       | /legal/cookies   |
      | Third Party Licenses | /legal/3rd-party |
      | Demo                 | /demo            |
      | Help Center          | /help            |
      | Developer Hub        | /developers      |
      | About Us             | /company/about   |


  Scenario: User clicks My Recipes link and ends up on My Recipes page
    When user clicks login link
    When user logs in with valid credentials
    Then after user clicks my recipes link current url matches expected my recipes page url


  Scenario: User clicks Signup link and ends up on Signup page
    Then after user clicks signup link current url matches expected signup page url


  Scenario: User clicks Feedback link and should see the dialog window for providing feedback
    Then after clicking feedback link user should see dialog window for providing feedback

 # @wip @ui
  Scenario: User clicks Instagram link and ends up on Instagram page
    Then after user clicks instagram link current url matches expected instagram home page url

 # @wip @ui
  Scenario: User clicks Twitter link and ends up on Twitter home page
    Then after user clicks twitter link current url matches expected twitter home page url


  Scenario: User clicks Facebook link and ends up on Facebook home page
    Then after user clicks facebook link current url matches expected facebook home page url

 # @wip @ui
  Scenario: User clicks YouTube link and ends up on YouTube home page
    Then after user clicks youtube link current url matches expected youtube home page url


  Scenario: User clicks Buy Me a Coffee link and ends up on Buy Me a Coffee home page
    Then after user clicks buyMeACoffee link current url matches expected buyMeACoffee home page url
