package ui_automation.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import ui_automation.pages.AlertPage;
import ui_automation.utilities.ConfigurationReader;
import ui_automation.utilities.Driver;
import ui_automation.utilities.WaitHelper;

public class AlertPageSteps {
    WebDriver driver = Driver.getInstance().getDriver();
    AlertPage alertPage = new AlertPage(driver);

    @Given("user navigates to Alert Page")
    public void user_navigates_to_Alert_Page() {
        String url = ConfigurationReader.getProperty("ui-config.properties", "alert.url");
        driver.get(url);
    }

    @When("user clicks on regular alert button on Alert Page")
    public void user_clicks_on_regular_alert_button_on_Alert_Page() {
        WaitHelper.waitUntilClickable(alertPage.regularAlertButton, 10);
        alertPage.regularAlertButton.click();
    }

    @Then("user should be able to handle regular alert on Alert Page")
    public void user_should_be_able_to_handle_regular_alert_on_Alert_Page() throws InterruptedException {
        //TODO HW: replace static wait with appropriate explicit wait condition
        Thread.sleep(2000);
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
}
