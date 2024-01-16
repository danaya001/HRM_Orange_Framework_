package ui_automation.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui_automation.utilities.WaitHelper;

public class LoginPage {
    WebDriver driver;

    public LoginPage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "username")
    public WebElement usernameTextInput;

    @FindBy(name = "password")
    public WebElement passwordTextInput;

    @FindBy(css = "button[type='submit']")
    public WebElement loginButton;

    @FindBy(xpath = "//h6[text()='Dashboard']")
    public WebElement dashboardTitle;


    @FindBy(xpath = "//p[text()='Invalid credentials']")
    public WebElement errorMessage;

    public void login (String username, String password) {
        WaitHelper.waitUntilClickable(usernameTextInput, 10);
        usernameTextInput.sendKeys(username);
        WaitHelper.waitUntilClickable(passwordTextInput, 10);
        passwordTextInput.sendKeys(password);
        WaitHelper.waitUntilClickable(loginButton, 10);
        loginButton.click();
    }

    public void verifyErrorMessage (String expectedErrorMessage) {
        String actualErrorMessage = errorMessage.getText();
        Assert.assertEquals("Error message validation failed!", expectedErrorMessage, actualErrorMessage);
    }
}
