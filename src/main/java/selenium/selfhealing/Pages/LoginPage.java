package selenium.selfhealing.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.selfhealing.Utilities.Extensions.WebDriverExtensions;

public class LoginPage {

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement getUsernameField() {
        return WebDriverExtensions.AIFindElement(driver, By.id("UserNamessss"));
    }

    private WebElement getPasswordField() {
        return WebDriverExtensions.AIFindElement(driver, By.id("Password"));
    }

    private WebElement getLoginButton() {
        return WebDriverExtensions.AIFindElement(driver,By.cssSelector("input[type='submit']"));
    }

    public void login(String userName, String password) {
        getUsernameField().sendKeys(userName);
        getPasswordField().sendKeys(password);
        getLoginButton().click();
    }
}

