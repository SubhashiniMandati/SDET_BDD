Feature: Order E2E Flow

  @api01
  Scenario: Get single object
    Given user get object via API
    Then validate object response


  @api01
  Scenario: Get list of objects
    Given user get list of object via API
    Then validate specific object response


  @api
  Scenario: Add object
    Given object is created via API
    Then object is created

