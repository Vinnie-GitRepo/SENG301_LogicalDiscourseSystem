Feature: Update database
  As a user, I want to add an organisation into the system so it can be linked to actors.

  Acceptance criteria
  -An organisation has a unique name.
  -When the registration of the organisation is done, a confirmation message is displayed to the user.
  -The organisation should be stored in the database.
  -If an organisation with an existing name is submitted an error message is shown to the user and no duplicate is inserted into the database


  Scenario: Register a unique organisation
    Given I am connected to the database
    And the name "Cool Inc" does not exist in database
    When I register organisation with name "Cool Inc"
    Then a new organisation with name "Cool Inc" should be added to the database
    And confirmation message is displayed to user

  Scenario: Attempting to register an existing organisation
    Given I am connected to the database
    And the name "Cool Inc" exists in database
    When I register organisation with name "Cool Inc"
    Then an error message is shown to the user
    And next time I retrieve organisation with name "Cool Inc", should return one row