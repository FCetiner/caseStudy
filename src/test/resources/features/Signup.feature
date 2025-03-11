@regression @smoke @signup
Feature: Signup feature

  Scenario: TC01 - Successful User Signup
    Given user is on the login page
    And user clicks on signUp button
    Then user verifies that they are on the Sign up page
    And user enters valid details and submits the form

  Scenario Outline: TC02 - Disable "Agree & Sign-Up" button when email format is invalid
    Given user is on the signup page
    And User enters "<firstName>" in the first name field
    And User enters "<lastName>" in the last name field
    And User selects "<country>" from the country dropdown
    And User enters "<mobileNumber>" in the mobile number field
    And User enters "<company>" in the company field
    And User enters "<email>" in the email field
    And User selects "<title>" from the title dropdown
    And User enters "<password>" in the password field
    And User enters "<confirmPassword>" in the confirm password field
    And User accepts the KVKK agreement
    Then The email input border should be red, and registration should not be completed

    Examples:
      | firstName | lastName   | country | mobileNumber | company | email        | title            | password | confirmPassword |
      | test      | automation | +90     | 5998008080   | Test    | abc.mail.com | Logistic Manager | Test12++ | Test12++        |

  Scenario Outline:TC03 - Recorded email error
    Given user is on the signup page
    And User enters "<firstName>" in the first name field
    And User enters "<lastName>" in the last name field
    And User selects "<country>" from the country dropdown
    And User enters "<mobileNumber>" in the mobile number field
    And User enters "<company>" in the company field
    And User enters "<email>" in the email field
    And User selects "<title>" from the title dropdown
    And User enters "<password>" in the password field
    And User enters "<confirmPassword>" in the confirm password field
    And User accepts the KVKK agreement
    And User clicks agree and signup button
  #  Then The error message "<errorMessage>" should be displayed

    Examples:
      | firstName | lastName   | country | mobileNumber | company | email               | title            | password | confirmPassword | errorMessage                                                                            |
      | test      | automation | +90     | 5998008080   | Test    | fcetinerr@gmail.com | Logistic Manager | Test12++ | Test12++        | This email is already associated with an account. Please use a different email or login |

