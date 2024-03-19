# @regression @ui
# @wip @ui
Feature: Base Page

  Background:
    Given user is on the home page

#  @wip @ui
  Scenario: Before logging in/signing up user should be able to see the following header links
    Then the following header links are displayed and corresponding path matches expected path
      | Meal Planner | /        |
      | My Recipes   | /recipes |
      | Log in       | /login   |
      | Sign up      | /signup  |

  @wip @ui
  Scenario: After logging in user should be able to see the following header links
    When user clicks login link
    When user logs in with valid credentials
    Then the following header links are displayed and enabled
      | Meal Planner |
      | My Recipes   |
    Then user should see the account menu "Account"
    Then the following account menu items are displayed and enabled
      | first.last@veypo.com |
      | Settings             |
      | Log out              |

#  @wip @ui
  Scenario: User should be able to see and reach social network apps links
    Then the following social network apps links are displayed and reachable
      | X               | https://twitter.com/                     |
      | Facebook        | https://www.facebook.com/                |
      | Instagram       | https://www.instagram.com/               |
      | YouTube         | https://www.youtube.com/                 |
      | Buy Me a Coffee | https://www.buymeacoffee.com/mealplanner |

#  @wip @ui
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


  Scenario: User clicks Signup link and ends up on Signup page
    Then after user clicks signup link current url matches expected signup page url


  Scenario: User clicks Feedback link and should see the dialog window for providing feedback
    Then after clicking feedback link user should see dialog window for providing feedback
