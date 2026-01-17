Feature: API CRUD Operations

  @api
  Scenario: Get single object
    Given user get object via API
    Then validate specific object response

  @api
  Scenario: Get list of objects
    Given user get list of object via API
    Then validate object response

  @api
  Scenario: Add object
    Given object is created via API
    Then object is created

  @api
  Scenario: Update object
    Given object is created via API
    Then object is created
    When object is updated via API
    Then object details are updated

  @api01
  Scenario: Partially Update object
    Given object is created via API
    Then object is created
    When object is partially updated via API
    Then object details are partially updated

  @api
  Scenario: Delete object
    Given object is created via API
    Then object is created
    When object is deleted via API
    Then verify object is deleted
