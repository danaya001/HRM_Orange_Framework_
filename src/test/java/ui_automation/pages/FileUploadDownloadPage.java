package ui_automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FileUploadDownloadPage {
    WebDriver driver;

    public FileUploadDownloadPage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "file-upload")
    public WebElement chooseFileButton;

    @FindBy(id = "file-submit")
    public WebElement uploadButton;

    @FindBy(id = "uploaded-files")
    public WebElement uploadedFileHeader;
}
