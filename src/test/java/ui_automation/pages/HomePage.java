package ui_automation.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage {
    WebDriver driver;

    public HomePage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "welcome")
    WebElement usernameHeader;

    @FindBy(id = "menu_admin_viewAdminModule")
    WebElement adminTab;

    @FindBy(id = "menu_pim_viewPimModule")
    WebElement pimTab;

    @FindBy(id = "menu_admin_Job")
    WebElement jobSubTab;

    @FindBy(id = "menu_pim_viewEmployeeList")
    WebElement employeeListSubTab;

    @FindBy(id = "menu_admin_viewJobTitleList")
    WebElement jobTitlesOption;

    @FindBy(xpath = "//*[@class='menu']/ul/li")
    public List<WebElement> allMainTabs;

    public void verifyUsernameHeader (String expectedUsernameHeader) {
        String actualUsernameHeader = usernameHeader.getText();
        Assert.assertEquals("Username header verification failed!",
                expectedUsernameHeader, actualUsernameHeader);
    }

    public void navigateToJobTitlesPage () throws InterruptedException {
        adminTab.click();
        Thread.sleep(1000);
        jobSubTab.click();
        Thread.sleep(1000);
        jobTitlesOption.click();
        Thread.sleep(1000);
    }

    public void navigateToEmployeeListSubTab () throws InterruptedException {
        pimTab.click();
        Thread.sleep(1000);
        employeeListSubTab.click();
        Thread.sleep(1000);
    }

}
