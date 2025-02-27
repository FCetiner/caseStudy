@regression @smoke @signup
Feature: Signup feature

  Scenario: Successful User Signup
    Given user is on the login page
    When user clicks on signUp button
    Then user verifies that they are on the Sign up page
    When user enters valid details and submits the form
  #  Then the user submits otp code

