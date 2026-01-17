Feature: Login Feature

  @Smoke
  Scenario: Basic BDD scenario example
    Given user is on login page
    When user enters valid credentials
    Then user should land on home page

  @Smoke
  Scenario: Verify login failure flow
    Given user is on login page
    When user enters valid credentials
    Then user should land on home page01

  @Smoke
  Scenario: DataTable Example without headers
    Given user is on login page
    When user enters registration details
      | John | Doe |

  @Smoke
  Scenario: DataTable Example with headers
    Given user is on login page
    When user enters registration details in tab
      | firstName | lastName |
      | Jane      | Smith    |

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

