@regression @fileUploadDownload
Feature: This feature file validates File Upload and Download Functionalities

  Scenario: User should be able to upload a document on Heroku App File Upload Page
    Given user navigates to Heroku App File Upload Page
    Then user uploads a document on Heroku App File Upload Page
    And user validates successful upload on Heroku App File Upload Page

  Scenario: User should be able to download a document on Heroku App File Download Page
    Given user navigates to Heroku App File Download Page
    Then user downloads a document on Heroku App File Download Page