package ui_automation.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui_automation.pages.FileUploadDownloadPage;
import ui_automation.utilities.ConfigurationReader;
import ui_automation.utilities.Driver;
import ui_automation.utilities.FileUtility;

public class FileUploadDownloadSteps {
    WebDriver driver = Driver.getInstance().getDriver();
    FileUploadDownloadPage fileUploadDownloadPage = new FileUploadDownloadPage(driver);
    String expectedFileName = "person.png";

    @Given("user navigates to Heroku App File Upload Page")
    public void user_navigates_to_Heroku_App_File_Upload_Page() {
        String url = ConfigurationReader.getProperty("ui-config.properties", "fileUpload.url");
        driver.get(url);
    }

    @Then("user uploads a document on Heroku App File Upload Page")
    public void user_uploads_a_document_on_Heroku_App_File_Upload_Page() throws InterruptedException {
        Thread.sleep(3000);
        String homeDirectory = System.getProperty("user.dir");
        System.out.println("Home Directory path is: " + homeDirectory);
        String filePath = homeDirectory + "\\src\\test\\resources\\testData\\" + expectedFileName;
        fileUploadDownloadPage.chooseFileButton.sendKeys(filePath);
        Thread.sleep(3000);
        fileUploadDownloadPage.uploadButton.click();
    }

    @Then("user validates successful upload on Heroku App File Upload Page")
    public void user_validates_successful_upload_on_Heroku_App_File_Upload_Page() throws InterruptedException {
        Thread.sleep(3000);
        String actualFileName = fileUploadDownloadPage.uploadedFileHeader.getText();
        Assert.assertEquals("Uploaded file verification failed!",
                expectedFileName, actualFileName);
    }

    @Given("user navigates to Heroku App File Download Page")
    public void user_navigates_to_Heroku_App_File_Download_Page() {
        String url = ConfigurationReader.getProperty("ui-config.properties", "fileDownload.url");
        driver.get(url);
    }

    @Then("user downloads a document on Heroku App File Download Page")
    public void user_downloads_a_document_on_Heroku_App_File_Download_Page() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[text()='"+expectedFileName+"']")).click();
        Thread.sleep(5000);
        String homeDirectory = System.getProperty("user.dir");
        String filePath = homeDirectory + "\\src\\test\\resources\\testData\\Downloads\\" + expectedFileName;
        boolean isFileExist = FileUtility.isFileExists(filePath);
        Assert.assertTrue("File download failed. File was not downloaded or doesn't exist!", isFileExist);
    }
}
