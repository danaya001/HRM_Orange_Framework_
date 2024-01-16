@regression @login
Feature: This feature file validates Login Page Functionality

  Background:
    Given user navigates to HRM Login Page

  @smoke
  Scenario: User should be able to login to HRM with valid credentials
    When user enters "Admin" username and "admin123" password and clicks on login button
    And user should be able to verify that he landed to the Dashboard on HRM Homepage

  @negative
  Scenario Outline: User should not be able to login to HRM with invalid credentials
    When user enters "<username>" username and "<password>" password and clicks on login button
    But user validates "<errorMessage>" error message
    Examples:
      | username | password | errorMessage             |
      | xyz      | abc      | Invalid credentials      |
      | xyz      |          | Password cannot be empty |
      |          | abc      | Username cannot be empty |
      |          |          | Username cannot be empty |

