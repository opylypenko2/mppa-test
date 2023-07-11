# @regression @ui
# @wip @ui
Feature: Base Page

  Background:
    Given user is on the home page


  Scenario: Before logging in/signing up user should see the following links
    Then user should see the app name link "Meal Planner"
    Then user should see the following links
      | My Recipes |
      | Log in     |
      | Sign up    |


  Scenario: After logging in user should see the following links
    When user clicks login link
    When user logs in with valid credentials
    Then user should see the app name link "Meal Planner"
    Then user should see my recipes link "My Recipes"
    Then user should see the account menu link "Account"


  Scenario: User should see the footer title and social network apps links
    Then the footer title "Find Meal Planner on" is displayed
    Then the following social network apps links are displayed
      | Instagram       |
      | Twitter         |
      | Facebook        |
      | YouTube         |
      | Buy Me a Coffee |


  Scenario: User should see the footer columns headers
    Then the following footer columns headers are displayed
      | Legal Policies |
      | Product        |
      | VEYPO LLC      |


  Scenario: User should see the following footer links
    Then the following footer links are displayed
      | Terms and Conditions |
      | Privacy Policy       |
      | Cookies Policy       |
      | Third Party Licenses |
      | Demo                 |
      | FAQ                  |
      | Feedback             |
      | About Us             |
      | Developer Center     |


  Scenario: User clicks My Recipes link and ends up on My Recipes page
    When user clicks login link
    When user logs in with valid credentials
    Then after user clicks my recipes link current url matches expected my recipes page url


  Scenario: User clicks Signup link and ends up on Signup page
    Then after user clicks signup link current url matches expected signup page url


  Scenario: User clicks Terms and Conditions link and ends up on Terms and Conditions page
    Then after user clicks terms and conditions link current url matches expected terms and conditions page url


  Scenario: User clicks Privacy Policy link and ends up on Privacy Policy page
    Then after user clicks privacy policy link current url matches expected privacy policy page url


  Scenario: User clicks Cookies Policy link and ends up on Cookies Policy page
    Then after user clicks cookies policy link current url matches expected cookies policy page url


  Scenario: User clicks Third Party Licenses link and ends up on Third Party Licenses page
    Then after user clicks third party licenses link current url matches expected third party licenses page url


  Scenario: User clicks Demo link and ends up on Demo page
    Then after user clicks demo link current url matches expected demo page url

#  @wip @ui
  Scenario: User clicks FAQ link and ends up on Help Center page
    Then after user clicks frequently asked questions link current url matches expected help center page url


  Scenario: User clicks Feedback link and should see the dialog window for providing feedback
    Then after clicking feedback link user should see dialog window for providing feedback


  Scenario: User clicks About Us link and ends up on About Us page
    Then after user clicks about us link current url matches expected about us page url

 # @wip @ui
  Scenario: User clicks Developer Center link and ends up on Developer Center page
    Then after user clicks developer center link current url matches expected developer center page url

 # @wip @ui
  Scenario: User clicks Instagram link and ends up on Instagram page
    Then after user clicks instagram link current url matches expected instagram home page url

 # @wip @ui
  Scenario: User clicks Twitter link and ends up on Twitter home page
    Then after user clicks twitter link current url matches expected twitter home page url


  Scenario: User clicks Facebook link and ends up on Facebook home page
    Then after user clicks facebook link current url matches expected facebook home page url

 # @wip @ui
  Scenario: User clicks YouTube link and ends up on
  YouTube home page
    Then after user clicks youtube link current url matches expected youtube home page url


  Scenario: User clicks Buy Me a Coffee link and ends up on
  Buy Me a Coffee home page
    Then after user clicks buyMeACoffee link current url matches expected buyMeACoffee home page url
