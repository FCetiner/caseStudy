@regression @smoke @signup
Feature: Login feature

  @negative
  Scenario Outline: TC01 Login should not be possible with an incorrect password
    And user enters "<email>" as the email and "<password>" as the password
    Then The error message "<errorMessage>" should be displayed
    Examples:
      | email               | password        | errorMessage                 |
      | fcetinerr@gmail.com | invalidPassword | Password verification failed |

  @negative
  Scenario Outline: TC02 Login should not be possible with an incorrect username
    Given user is on the login page
    And user enters "<email>" as the email and "<password>" as the password
    Then The error message "<errorMessage>" should be displayed
    Examples:
      | email                 | password  | errorMessage                                                             |
      | invalidMail@gmail.com | Test123++ | Incorrect email or password. Please check your information and try again |

  @negative
  Scenario Outline: TC03 Username field should be required
    Given user is on the login page
    And user enters "<email>" as the email and "<password>" as the password
    Then The error message "<errorMessage>" should be displayed
    Examples:
      | email | password  | errorMessage                     |
      |       | Test123++ | Please fill the required fields! |

  @positive @test
  Scenario Outline: TC04 Password field should be required
    Given user is on the login page
    And user enters "<email>" as the email and "<password>" as the password
    Then The error message "<errorMessage>" should be displayed
    Examples:
      | email               | password | errorMessage                     |
      | fcetinerr@gmail.com |          | Please fill the required fields! |

  Scenario: TC05 Successful User Login
    Given user is on the login page
    When user successfully logins
    Then user verifies that they are on the Dashboard page
    And user logs out