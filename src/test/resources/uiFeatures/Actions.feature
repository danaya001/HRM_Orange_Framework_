@regression @actions
Feature: This feature file validates Actions Functionality

  Scenario: User should be able to hover over Admin tab on HRM Homepage
    Given user navigates to HRM Login Page
    When user enters "yoll-student" username and "Bootcamp5#" password and clicks on login button
    Then user hovers over PIM tab on HRM Homepage
    Then user hovers over Admin tab on HRM Homepage
    Then user hovers over User Management subtab on HRM Homepage
    Then user hovers over Users option on HRM Homepage
    And user clicks on Users option on HRM Homepage

  Scenario: User should be able to perform right click action on Context Menu Page
    Given user navigates to Context Menu Page
    Then user clicks on right click me button on Context Menu Page
    Then user clicks on "edit" option on Context Menu Page
    And user validates and closes "edit" alert on Context Menu Page

  Scenario: User should be able to perform drag and drop action on DHTML Page
    Given user navigates to DHTML Page
    Then user drags "Washington" box and drops it to "United States" box on DHTML Page

  Scenario: User should be able to perform double click action on Practice Page
    Given user navigates to Practice Page
    Then user double clicks and verifies copied text on Practice Page
  @smoke
  Scenario: User should be able to perform keyboard action on Key Presses Page
    Given user navigates to Key Presses Page
    Then user performs keyboard action on Key Presses Page

  Scenario: User should be able to verify tooltip on JQuery Page
    Given user navigates to JQuery Page
    Then user validates tooltip on JQuery Page
@hw1
  Scenario: User should be able to hover over PIM tab on HRM Homepage
    Given user navigates to HRM Login Page
    When user enters "yoll-student" username and "Bootcamp5#" password and clicks on login button
    Then user hovers over PIM tab on HRM Homepage
    Then user hovers over Admin tab on Configuration Homepage
    Then user hovers over Optional Fields subtab on HRM Homepage
    And user clicks on Optional Fields option on HRM Homepage
@tooltip
  Scenario: User should be able to verify tooltip on Demoqa Page
    Given user navigates to Demoqa Page
    Then user validates tooltip on Demoqa Page