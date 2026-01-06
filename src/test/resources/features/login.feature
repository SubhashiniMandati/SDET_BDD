Feature: Login Feature

  @Smoke
  Scenario: Valid login
    Given user is on login page
    When user enters valid credentials
    Then user should land on home page


  @Smoke
  Scenario: Invalid login
    Given user is on login page
    When user enters valid credentials
    Then user should land on home page
