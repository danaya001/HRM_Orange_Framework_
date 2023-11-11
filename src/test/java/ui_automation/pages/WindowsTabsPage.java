package ui_automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WindowsTabsPage {
    WebDriver driver;

    public WindowsTabsPage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "windowButton")
    public WebElement newWindowButton;

    @FindBy(id = "sampleHeading")
    public WebElement newWindowHeader;

}
