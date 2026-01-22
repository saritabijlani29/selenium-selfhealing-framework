package selenium.selfhealing.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.selfhealing.Utilities.Extensions.WebDriverExtensions;

public class HomePage {

    private WebDriver driver;

    private WebElement getLoginLink() {
        return WebDriverExtensions.AIFindElement(driver,By.linkText("Login"));
    }

    private WebElement getEmployeeDetailsLink() {
        return WebDriverExtensions.AIFindElement(driver,By.linkText("Employee Details"));
    }

    private WebElement getManageUsersLink() {
        return WebDriverExtensions.AIFindElement(driver,By.linkText("Manage Users"));
    }

    private WebElement getLogoffLink() {
        return WebDriverExtensions.AIFindElement(driver,By.linkText("Log off"));
    }

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Click actions
    public void clickLogin() {
        getLoginLink().click();
    }

    public void clickEmployeeDetails() {
        getEmployeeDetailsLink().click();
    }

    public void clickManageUsers() {
        getManageUsersLink().click();
    }

    public void clickLogoff() {
        getLogoffLink().click();
    }
}
