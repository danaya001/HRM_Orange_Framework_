package ui_automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ActionsPage {
    WebDriver driver;

    public ActionsPage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[contains(@class,'context-menu-one')]")
    public WebElement rightClickMeButton;

    //Drag and Drop Website
    @FindBy(id = "box3")
    public WebElement sourceWashington;

    @FindBy(id = "box103")
    public WebElement targetUSA;

    //Automation Testing Practice
    @FindBy(xpath = "//*[text()='Copy Text']")
    public WebElement copyTextButton;

    @FindBy(id = "field2")
    public WebElement textFieldTwo;

    //Key Presses Page
    @FindBy(id = "result")
    public WebElement keyActionResultText;

    //Tooltip page
    @FindBy(xpath = "//*[@class='demo-frame']")
    public WebElement yourAgeTextBoxIframe;

    @FindBy(id = "age")
    public WebElement yourAgeTextBox;


}
