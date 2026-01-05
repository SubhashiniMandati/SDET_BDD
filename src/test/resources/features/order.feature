Feature: Order E2E Flow
  @Regression
  Scenario: User creates order and verifies in UI
    Given user is created via API
    #And order is created via API
    When user logs in via UI
    Then order should be visible in dashboard
