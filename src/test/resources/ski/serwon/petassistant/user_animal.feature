Feature: User's animals collection

  As an user I want to add, modify and delete animals in my profile.

  Scenario: Adding an animal
    Given An user profile
    When I create an animal
    And Add it to user's profile
    Then This animal will be in user's animals


  Scenario: Modifying an animal
    Given An user profile
    And an animal in this profile
    When I modify any animal's property
    Then User's animals collection will contain modified animal

  Scenario: Deleting an animal
    Given An user profile
    And an animal in this profile
    When I delete an animal
    Then User's animals collection will not contain this animal