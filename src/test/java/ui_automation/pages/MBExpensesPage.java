package ui_automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MBExpensesPage {
    WebDriver driver;

    public MBExpensesPage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//*[contains(@class,'add-expense-button')])[2]")
    public WebElement addExpenseDropdown;

    @FindBy(xpath = "(//*[text()='Meal & Entertainment'])[4]")
    public WebElement mealAndEntertainmentOption;

    @FindBy(id = "ExpenseDateTime")
    public WebElement dateField;

    @FindBy(id = "Amount")
    public WebElement amountTextBox;

    @FindBy(id = "name")
    public WebElement expenseNameTextBox;

    @FindBy(css = ".save-button")
    public WebElement saveButton;

    @FindBy(id = "expenses-table_info")
    public WebElement expensesTableFooter;



}
