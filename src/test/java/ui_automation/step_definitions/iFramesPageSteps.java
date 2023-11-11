package ui_automation.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import ui_automation.pages.iFramesPage;
import ui_automation.utilities.ConfigurationReader;
import ui_automation.utilities.Driver;

public class iFramesPageSteps {
    WebDriver driver = Driver.getInstance().getDriver();
    iFramesPage framesPage = new iFramesPage(driver);

    @Given("user navigates to iFrames Page")
    public void user_navigates_to_iFrames_Page() {
        String url = ConfigurationReader.getProperty("ui-config.properties", "iFrames.url");
        driver.get(url);
    }

    @When("user enters a keyword into the text box on iFrames Page")
    public void user_enters_a_keyword_into_the_text_box_on_iFrames_Page() throws InterruptedException {
        Thread.sleep(3000);
        driver.switchTo().frame(framesPage.iFrameMenu);
        Thread.sleep(3000);
        framesPage.editorTextBox.clear();
        framesPage.editorTextBox.sendKeys("My name is Amid!");
        Thread.sleep(5000);
    }

    @Then("user validates entered text on iFrames Page")
    public void user_validates_entered_text_on_iFrames_Page() {
        //TODO complete this step as part of HM
    }
}
