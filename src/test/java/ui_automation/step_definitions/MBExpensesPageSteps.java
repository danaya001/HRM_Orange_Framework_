package ui_automation.step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ui_automation.pages.MBExpensesPage;
import ui_automation.utilities.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class MBExpensesPageSteps {
    WebDriver driver = Driver.getInstance().getDriver();
    MBExpensesPage mbExpensesPage = new MBExpensesPage(driver);

    String todaysDate;
    double amount;
    String expenseName;

    @Given("user navigates to MealB Login Page")
    public void user_navigates_to_MealB_Login_Page() throws InterruptedException {
        Thread.sleep(2000);
        String url = ConfigurationReader.getProperty("ui-config.properties", "mealb.url");
        driver.get(url);
    }

    @When("user logs in to MealB with valid credentials")
    public void user_logs_in_to_MealB_with_valid_credentials() throws InterruptedException {
        Thread.sleep(2000);
        String username = ConfigurationReader.getProperty("ui-config.properties", "mealb.username");
        String password = ConfigurationReader.getProperty("ui-config.properties", "mealb.password");
        driver.findElement(By.name("usernameOrEmailAddress")).sendKeys(username);
        driver.findElement(By.name("Password")).sendKeys(password);
        driver.findElement(By.id("LoginButton")).click();
    }

    @Then("user navigates to {string} tab on MealB")
    public void user_navigates_to_tab_on_MealB(String tab) throws InterruptedException {
        Thread.sleep(2000);
        switch (tab) {
            case "Expenses":
                driver.findElement(By.xpath("//*[contains(text(),'Expenses')]")).click();
                break;
        }
    }

    @Then("user navigates to {string} expense modal window on MealB")
    public void user_navigates_to_expense_modal_window_on_MealB(String expenseType) throws InterruptedException {
        Thread.sleep(2000);
        mbExpensesPage.addExpenseDropdown.click();
        Thread.sleep(1000);
        switch (expenseType) {
            case "Create Meal & Entertainment":
                mbExpensesPage.mealAndEntertainmentOption.click();
                break;
        }
    }

    @Then("user completes all mandatory fields on Create Meal & Entertainment expense modal window")
    public void user_completes_all_mandatory_fields_on_Create_Meal_Entertainment_expense_modal_window() throws Exception {
        Thread.sleep(2000);
        /* establishing the connection to the excel file */
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\testData.xlsx";
        ExcelUtility.setExcelFile(filePath, "Sheet1");

        /* retrieving data from excel file */
        todaysDate = DateHelper.getTodaysDate("MM/dd/yyyy");
        amount = ExcelUtility.getCellDataAsDouble(7, 3);
        expenseName = ExcelUtility.getCellDataAsString(7, 0);

        /* complete mandatory fields */
        mbExpensesPage.dateField.click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//*[@aria-label='"+todaysDate+"'])[3]")).click();
        mbExpensesPage.amountTextBox.sendKeys(String.valueOf(amount));
        mbExpensesPage.expenseNameTextBox.sendKeys(expenseName);
    }

    @Then("user clicks on save button on Create Meal & Entertainment expense modal window")
    public void user_clicks_on_save_button_on_Create_Meal_Entertainment_expense_modal_window() throws InterruptedException {
        Thread.sleep(2000);
        mbExpensesPage.saveButton.click();
    }

    @Then("user validates created Meal & Entertainment expense on Expenses Page")
    public void user_validates_created_Meal_Entertainment_expense_on_Expenses_Page() throws InterruptedException {
        Thread.sleep(5000);
        List<WebElement> allExpenses = driver.findElements(By.xpath("//table[@id='expenses-table']/tbody/tr/td[2]"));
        boolean isNewExpenseDisplayed = false;

        for (WebElement expense : allExpenses) {
            String actualExpenseNameValue = expense.getText();

            if (actualExpenseNameValue.equals(expenseName)) {
                isNewExpenseDisplayed = true;
                break;
            }
        }
        Assert.assertTrue("New Expense validation failed!", isNewExpenseDisplayed);
    }


    @And("user validates UI expenses with DB expenses")
    public void userValidatesUIExpensesWithDBExpenses() throws InterruptedException, SQLException {
        Thread.sleep(5000);
        /* create a connection to MealB DB */
        DBUtility.openConnection();

        /* establish the statement and execute the query */
        String query = "SELECT Name from Expenses as expenses\n" +
                        "join AbpUserAccounts as users\n" +
                        "on users.UserId = expenses.CreatorUserId\n" +
                        "WHERE users.UserName = 'walmart' and expenses.DeletionTime is NULL\n" +
                        "ORDER by expenses.Name ASC;";

        List<Map<String, Object>> dataFromDB = DBUtility.executeSQLQuery(query);
        int countFromUI;
        int countFromDB = dataFromDB.size();

        /* extract count value from UI and compare with DB */
        String expenseCountMessage = mbExpensesPage.expensesTableFooter.getText();
        String[] words = expenseCountMessage.split(" ");
        countFromUI = Integer.parseInt(words[5]);
        Assert.assertEquals("Expense count verification failed!", countFromDB, countFromUI);

        /* click on Expense Name column to sort in alph ASC order */
        Thread.sleep(2000);
        driver.findElement(By.xpath("//table[@id='expenses-table']/thead//th[2]")).click();

        /* validate DB expenses with UI expenses one by one */
        Thread.sleep(3000);
        int i = 0;
        List<WebElement> allExpenses = driver.findElements(By.xpath("//table[@id='expenses-table']/tbody/tr/td[2]"));

        for (Map<String, Object> data : dataFromDB) {
            String expectedDataFromDB = data.get("Name").toString();
            String actualDataFromUI = allExpenses.get(i).getText();
            Assert.assertEquals("Data verification failed!", expectedDataFromDB, actualDataFromUI);
            i++;
        }
    }
}
