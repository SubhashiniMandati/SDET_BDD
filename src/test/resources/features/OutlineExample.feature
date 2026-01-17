Feature: Login functionality
@Smoke
  Scenario Outline: Successful login with valid credentials
    Given user is on login page
    When user enters username "<username>" and password "<password>"
    #And user clicks on login button
    Then user should see homepage with message "<message>"

    Examples:
      | username | password | message        |
      | admin    | admin123 | Welcome Admin  |
      | user1    | pass123  | Welcome User   |

  @Smoke
  Scenario Outline: Login tests
    Given user is on login page
    When user enters username "<username>" and password "<password>"
    Then user should see homepage with message "<message>"

    Examples: Valid users
      | username | password | result  |
      | admin    | admin123 | success |

    Examples: Invalid users
      | username | password | result  |
      | admin    | wrong    | failure |

