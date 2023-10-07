package ui_automation.step_definitions;

import ui_automation.pages.HomePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import ui_automation.utilities.Driver;

import java.util.List;
import java.util.Map;

public class HomePageSteps {
    WebDriver driver = Driver.getInstance().getDriver();
    HomePage homePage = new HomePage(driver);

    @And("user should be able to verify account name header {string} on HRM Homepage")
    public void userShouldBeAbleToVerifyAccountNameHeaderOnHRMHomepage(String usernameHeader) {
        homePage.verifyUsernameHeader(usernameHeader);
    }

    @Then("user navigates to {string} Page")
    public void user_navigates_to_Page(String page) throws InterruptedException {
        switch (page) {
            case "Job Titles":
                homePage.navigateToJobTitlesPage();
                break;
            case "Employee List":
                homePage.navigateToEmployeeListSubTab();
                break;
        }
    }

    @And("user validates main tabs on HRM Homepage")
    public void userValidatesMainTabsOnHRMHomepage(DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);
        int i = 0;

        for (Map<String, String> map : maps) {
            String expectedTab = map.get("tabname");
            String actualTab = homePage.allMainTabs.get(i).getText();
            Assert.assertEquals("Tab name verification failed!",
                    expectedTab, actualTab);
            i++;
        }
    }
}
