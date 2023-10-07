@regression @jobTitles
Feature: This feature file validates Job Titles Page Functionality

  Background:
    Given user navigates to HRM Login Page
    When user enters "yoll-student" username and "Bootcamp5#" password and clicks on login button
    And user should be able to verify account name header "Welcome Yoll" on HRM Homepage
    Then user navigates to "Job Titles" Page

  Scenario: User should be able to navigate to Job Titles Page on HRM
    And user validates Job Titles page header

  Scenario: User should be able to validate buttons on Job Titles Page on HRM
    And user validates buttons on Job Titles Page

  Scenario: User should be able to add new job title on Job Titles Page on HRM
    And user creates a new job title on Job Titles Page

