package ui_automation.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import ui_automation.pages.WindowsTabsPage;
import ui_automation.utilities.ConfigurationReader;
import ui_automation.utilities.Driver;

import java.util.Set;

public class WindowsTabsPageSteps {
    WebDriver driver = Driver.getInstance().getDriver();
    WindowsTabsPage windowsTabsPage = new WindowsTabsPage(driver);

    @Given("user navigates to Windows and Tabs Page")
    public void user_navigates_to_Windows_and_Tabs_Page() {
        String url = ConfigurationReader.getProperty("ui-config.properties", "windowsTabs.url");
        driver.get(url);
    }

    @When("user clicks on new window button on Windows and Tabs Page")
    public void user_clicks_on_new_window_button_on_Windows_and_Tabs_Page() throws InterruptedException {
        Thread.sleep(3000);
        windowsTabsPage.newWindowButton.click();
    }

    @Then("user validates new window header on Windows and Tabs Page")
    public void user_validates_new_window_header_on_Windows_and_Tabs_Page() throws InterruptedException {
        Thread.sleep(3000);

        /* storing the main window GUID */
        String mainGUID = driver.getWindowHandle();
        System.out.println("Main GUID is: " + mainGUID);

        /* storing new window GUIDs */
        Set<String> allWindowGUIDs = driver.getWindowHandles();

        /* switch the control to new window */
        for (String windowGUID : allWindowGUIDs) {
            System.out.println("Iterated window GUID is: " + windowGUID);
            driver.switchTo().window(windowGUID);
        }

        /* perform some operation on new window */
        Thread.sleep(2000);
        String expectedNewWindowHeader = "This is a sample page";
        String actualNewWindowHeader = windowsTabsPage.newWindowHeader.getText();
        Assert.assertEquals("New window header verification failed!",
                expectedNewWindowHeader, actualNewWindowHeader);

        /* close the new window */
        Thread.sleep(2000);
        driver.close();

        /* switch the control back to main window */
        Thread.sleep(2000);
        driver.switchTo().window(mainGUID);
        System.out.println("Title of original window is: " + driver.getTitle());
    }
}
