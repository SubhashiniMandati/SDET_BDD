Feature: Login Feature

  @Smoke01
  Scenario: Valid login
    Given user is on login page
    When user enters valid credentials
    Then user should land on home page01
