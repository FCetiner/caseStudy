@regression @smoke @signup
Feature: Signup feature

  Scenario: Successful User Signup
    Given user is on the signup page
    Then user verifies that they are on the Sign up page
    And user enters valid details and submits the form


