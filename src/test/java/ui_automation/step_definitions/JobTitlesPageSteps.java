package ui_automation.step_definitions;

import ui_automation.pages.JobTitlesPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import ui_automation.utilities.Driver;

public class JobTitlesPageSteps {
    WebDriver driver = Driver.getInstance().getDriver();
    JobTitlesPage jobTitlesPage = new JobTitlesPage(driver);

    @Then("user validates Job Titles page header")
    public void user_validates_page_header() {
        jobTitlesPage.validatePageHeader();
    }

    @And("user validates buttons on Job Titles Page")
    public void userValidatesButtonsOnJobTitlesPage() throws Exception {
        jobTitlesPage.verifyButtonsDisplayed();
    }

    @And("user creates a new job title on Job Titles Page")
    public void userCreatesANewJobTitleOnJobTitlesPage() throws Exception {
        String jobTitle = "JobTitle001";
        String jobDescription = "Test Automation Engineer";
        jobTitlesPage.verifyJobTitleDeleted(driver, jobTitle);
        jobTitlesPage.addJobTitle(jobTitle, jobDescription);
        jobTitlesPage.verifyNewJobTitle(jobTitle);
    }
}
