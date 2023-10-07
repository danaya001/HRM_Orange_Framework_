package ui_automation.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import ui_automation.pages.LoginPage;
import ui_automation.utilities.Driver;

public class LoginPageSteps {
    WebDriver driver = Driver.getInstance().getDriver();
    LoginPage loginPage = new LoginPage(driver);

    @Given("user navigates to HRM Login Page")
    public void user_navigates_to_HRM_Login_Page() {
        driver.get("http://dev-hrm.yoll.io/index.php/auth/login");
    }

    @When("user enters {string} username and {string} password and clicks on login button")
    public void user_enters_username_and_password_and_clicks_on_login_button(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("user validates {string} error message")
    public void user_validates_error_message(String errorMessage) {
        loginPage.verifyErrorMessage(errorMessage);
    }

}
