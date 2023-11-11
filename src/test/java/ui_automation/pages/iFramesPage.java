package ui_automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class iFramesPage {
    WebDriver driver;

    public iFramesPage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "mce_0_ifr")
    public WebElement iFrameMenu;

    @FindBy(id = "tinymce")
    public WebElement editorTextBox;
}
