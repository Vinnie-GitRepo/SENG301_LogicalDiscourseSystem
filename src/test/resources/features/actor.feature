Feature: Update database

  Scenario: Create an actor
    Given I am connected to the database
    When I submit the unique details of a new actor to the system
    Then A new row in our database is created containing those details

  Scenario: