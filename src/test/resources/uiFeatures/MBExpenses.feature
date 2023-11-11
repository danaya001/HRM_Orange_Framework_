@regression @mbExpenses
Feature: This feature file validates MealB Expenses Functionality

  Scenario: User should be able to create Meal & Entertainment Expense on MealB
    Given user navigates to MealB Login Page
    When user logs in to MealB with valid credentials
    Then user navigates to "Expenses" tab on MealB
    Then user navigates to "Create Meal & Entertainment" expense modal window on MealB
    Then user completes all mandatory fields on Create Meal & Entertainment expense modal window
    Then user clicks on save button on Create Meal & Entertainment expense modal window
    And user validates created Meal & Entertainment expense on Expenses Page

  Scenario: User should be able to compare UI expenses with DB Expenses
    Given user navigates to MealB Login Page
    When user logs in to MealB with valid credentials
    Then user navigates to "Expenses" tab on MealB
    And user validates UI expenses with DB expenses