@regression @homePage
Feature: This feature file validates Home Page Functionality

  Background:
    Given user navigates to HRM Login Page
    When user enters "yoll-student" username and "Bootcamp5#" password and clicks on login button
    And user should be able to verify account name header "Welcome Yoll" on HRM Homepage
@smoke
  Scenario: User should be able to validate main tabs on HRM Homepage
    And user validates main tabs on HRM Homepage
      | tabname     |
      | Admin       |
      | PIM         |
      | Leave       |
      | Time        |
      | Recruitment |
      | My Info     |
      | Performance |
      | Dashboard   |
      | Directory   |
      | Maintenance |