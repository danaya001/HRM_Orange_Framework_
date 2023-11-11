@regression @iFramesPage
Feature: This feature file validates iFrames Page Functionality

  Scenario: User should be able to enter a keyword into the text box on iFrames Page
    Given user navigates to iFrames Page
    When user enters a keyword into the text box on iFrames Page
    Then user validates entered text on iFrames Page
