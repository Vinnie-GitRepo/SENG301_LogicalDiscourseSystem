Feature: Update database

  Scenario: Create an actor
    Given I am connected to the database
    And the new actor has a first name and last name
    When I submit the unique details of a new actor to the system
    Then a new row in the database is created containing those details

  Scenario: Create a homonym actor
    Given I am connected to the database
    And the new actor has a first name and last name
    And the details of a new actor already exists in the database
    When I submit the details of a new actor to the system
    Then a confirmation is asked to the user if the homonym actor is a new actor
    And the confirmation message shows all details including affiliations of the conflicting actor
    And a new row in the database is created containing those details
    And a confirmation message is shown to the user when the actor is saved
