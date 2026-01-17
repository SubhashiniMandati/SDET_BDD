Feature: Data Table Example
@Smoke
Scenario: User registration
  Given user enters registration details
    | John | Doe | john@test.com | 9876543210 |

@Smoke
Scenario: Exa02
  Given user enters registration details in tab
    | firstName | lastName | email         | phone      |
    | John      | Doe      | john@test.com | 9876543210 |
    | Jane      | Smith    | jane@test.com | 9123456780 |
