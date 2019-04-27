Feature: Update database

  As a user, I want to add an actor into the system with his details and affiliations so that I know the link between actors and organisations.

  Acceptance criteria
  -An actor has a first name and a last name that are mandatory.
  -If the user wants to add a homonym actor, a confirmation message is asked before inserting the actor to make sure the homonym is an actual new actor.
   The confirmation message must contain all the details of the existing user, including his/her affiliations.
  -A confirmation message is shown to the user when the actor is saved.
  -When inserting an affiliation, all fields are optional.
  -An affiliation end date may not be prior to its start date. If such invalid dates are submitted, an error message must be presented.
  -The actor and its affiliations are stored in the database in case of success.

  Scenario: Create an actor with an affiliation
    Given I am connected to the debate database
    And no actor with first name "Johnny" and last name "Test" exists
    When I submit a new actor with first name "Johnny" and last name "Test"
    And I create an affiliation with attributes "CEO", "2005-06-09", "2012-09-09", "Google LLC"
    Then a new database row in ACTOR is created with those name details
    And a new database row in AFFILIATION is created those affiliation details
    And a confirmation message is presented when the actor is saved

  Scenario: Create a homonym actor with an affiliation
    Given I am connected to the debate database
    And an actor with first name "Johnny" and last name "Test" exists
    When I submit a new actor with first name "Johnny" and last name "Test"
    And I create an affiliation with attributes "CEO", "2005-05-09", "2012-05-09", "Apple Inc."
    Then a confirmation message containing the existing actor details is presented, allowing the user to cancel or proceed.
    And upon proceeding, a new database row in ACTOR is created with those details
    And a new database row in AFFILIATION is created with those affiliation details
    And a confirmation message is presented when the actor is saved

  Scenario: Create an actor with an invalid affiliation dates
    Given I am connected to the debate database
    And no actor with first name "Johnny" and last name "Test" exists
    When I submit a new actor with first name "Johnny" and last name "Test"
    And I create an affiliation with attributes "CEO", "2005-12-12", 1999-10-11", "Microsoft Corporation"
    Then an error message pertaining to the invalid affiliation dates is presented


