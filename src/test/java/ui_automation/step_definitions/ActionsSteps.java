package ui_automation.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import ui_automation.pages.ActionsPage;
import ui_automation.pages.HomePage;
import ui_automation.utilities.ConfigurationReader;
import ui_automation.utilities.Driver;
import ui_automation.utilities.Helper;

public class ActionsSteps {
    WebDriver driver = Driver.getInstance().getDriver();
    HomePage homePage = new HomePage(driver);
    ActionsPage actionsPage = new ActionsPage(driver);
    Actions actions = new Actions(driver);

    @Then("user hovers over PIM tab on HRM Homepage")
    public void user_hovers_over_PIM_tab_on_HRM_Homepage() throws InterruptedException {
        Thread.sleep(3000);
        actions.moveToElement(homePage.allMainTabs.get(1)).perform();
    }

    @Then("user hovers over Admin tab on HRM Homepage")
    public void user_hovers_over_Admin_tab_on_HRM_Homepage() throws InterruptedException {
        Thread.sleep(3000);
        actions.moveToElement(homePage.allMainTabs.get(0)).perform();
    }

    @Then("user hovers over User Management subtab on HRM Homepage")
    public void user_hovers_over_User_Management_subtab_on_HRM_Homepage() throws InterruptedException {
        Thread.sleep(3000);
        actions.moveToElement(homePage.userManagementSubtab).perform();
    }

    @Then("user hovers over Users option on HRM Homepage")
    public void user_hovers_over_Users_option_on_HRM_Homepage() throws InterruptedException {
        Thread.sleep(3000);
        actions.moveToElement(homePage.usersOption).perform();
    }

    @Then("user clicks on Users option on HRM Homepage")
    public void user_clicks_on_Users_option_on_HRM_Homepage() throws InterruptedException {
        Thread.sleep(3000);
        Helper.clickWithJS(homePage.usersOption);
    }

    @Given("user navigates to Context Menu Page")
    public void user_navigates_to_Context_Menu_Page() {
        String url = ConfigurationReader.getProperty("ui-config.properties", "contextMenu.url");
        driver.get(url);
    }

    @Then("user clicks on right click me button on Context Menu Page")
    public void user_clicks_on_right_click_me_button_on_Context_Menu_Page() throws InterruptedException {
        Thread.sleep(3000);
        actions.contextClick(actionsPage.rightClickMeButton).perform();
    }

    @Then("user clicks on {string} option on Context Menu Page")
    public void user_clicks_on_option_on_Context_Menu_Page(String option) throws InterruptedException {
        Thread.sleep(3000);
        String element = "context-menu-icon-" + option;
        driver.findElement(By.xpath("//*[contains(@class,'"+element+"')]")).click();
    }

    @Then("user validates and closes {string} alert on Context Menu Page")
    public void user_validates_and_closes_alert_on_Context_Menu_Page(String option) throws InterruptedException {
        Thread.sleep(3000);
        Alert alert = driver.switchTo().alert();
        Thread.sleep(3000);
        String expectedAlertText = "clicked: " + option;
        String actualAlertText = alert.getText();
        Assert.assertEquals("Alert text verification failed", expectedAlertText, actualAlertText);
        Thread.sleep(3000);
        alert.accept();
    }

    @Given("user navigates to DHTML Page")
    public void user_navigates_to_DHTML_Page() {
        String url = ConfigurationReader.getProperty("ui-config.properties", "dragAndDrop.url");
        driver.get(url);
    }

    @When("user drags {string} box and drops it to {string} box on DHTML Page")
    public void user_drags_box_and_drops_it_to_box_on_DHTML_Page(String string, String string2) throws InterruptedException {
        /* drag and drop source item to target item */
        actions.dragAndDrop(actionsPage.sourceWashington, actionsPage.targetUSA).perform();
        Thread.sleep(3000);

        /* get the background color of source and assert */
        //TODO Hint for HW: retrieve expected values from Examples section on Scenario Outline from Feature File
        String expectedBackgroundColor = "0, 255, 0";
        String actualBackgroundColor = actionsPage.sourceWashington.getCssValue("background-color");
        System.out.println("Actual background color of source is: " + actualBackgroundColor);
        Assert.assertTrue("Background color verification failed",
                actualBackgroundColor.contains(expectedBackgroundColor));
    }

    @Given("user navigates to Practice Page")
    public void user_navigates_to_Practice_Page() {
        String url = ConfigurationReader.getProperty("ui-config.properties", "doubleClick.url");
        driver.get(url);
    }

    @Then("user double clicks and verifies copied text on Practice Page")
    public void user_double_clicks_and_verifies_copied_text_on_Practice_Page() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        actions.doubleClick(actionsPage.copyTextButton).perform();
        Thread.sleep(5000);
        String expectedText = "Hello World!";
        //String actualText = actionsPage.textFieldTwo.getText();
        String actualText = (String) js.executeScript("return arguments[0].value;", actionsPage.textFieldTwo);
        Assert.assertEquals("Data verification failed!", expectedText, actualText);
    }

    @Given("user navigates to Key Presses Page")
    public void user_navigates_to_Key_Presses_Page() {
        String url = ConfigurationReader.getProperty("ui-config.properties", "keyboardAction.url");
        driver.get(url);
    }

    @Then("user performs keyboard action on Key Presses Page")
    public void user_performs_keyboard_action_on_Key_Presses_Page() throws InterruptedException {
        /* perform some keyboard actions */
        actions.sendKeys(Keys.SPACE).build().perform();
        Thread.sleep(3000);

        /* verify is entered key is displayed */
        String expectedKeyValue = "SPACE";
        String actualKeyValue = actionsPage.keyActionResultText.getText();
        Assert.assertTrue("Key value verification failed!", actualKeyValue.contains(expectedKeyValue));
    }

    @Given("user navigates to JQuery Page")
    public void user_navigates_to_JQuery_Page() {
        String url = ConfigurationReader.getProperty("ui-config.properties", "tooltipJQuery.url");
        driver.get(url);
    }

    @Then("user validates tooltip on JQuery Page")
    public void user_validates_tooltip_on_JQuery_Page() throws InterruptedException {
        /* locate the iframe and switch the control to it */
        Thread.sleep(2000);
        driver.switchTo().frame(actionsPage.yourAgeTextBoxIframe);

        /* get 'title' attribute value before hover over */
        Thread.sleep(2000);
        String expectedAttributeValueBeforeHoverOver = "We ask for your age only for statistical purposes.";
        String actualAttributeValueBeforeHoverOver = actionsPage.yourAgeTextBox.getAttribute("title");
        Assert.assertEquals("Title attribute value before hover over verification failed!",
                expectedAttributeValueBeforeHoverOver, actualAttributeValueBeforeHoverOver);

        /* hover over 'your age' text box */
        Thread.sleep(2000);
        actions.moveToElement(actionsPage.yourAgeTextBox).perform();

        /* get 'title' attribute value after hover over */
        Thread.sleep(5000);
        String expectedAttributeValueAfterHoverOver = "";
        String actualAttributeValueAfterHoverOver = actionsPage.yourAgeTextBox.getAttribute("title");
        Assert.assertEquals("Title attribute value after hover over verification failed!",
                expectedAttributeValueAfterHoverOver, actualAttributeValueAfterHoverOver);
    }
}
