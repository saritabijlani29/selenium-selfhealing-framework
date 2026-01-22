package selenium.selfhealing.Tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import selenium.Pages.HomePage;
import selenium.selfhealing.Pages.LoginPage;

import java.io.IOException;

public class EnhancedTests {

    @Test
    public void traditionalTest() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        WebDriver driver = new ChromeDriver(options);

        try {
            driver.navigate().to("http://eaapp.somee.com");

            HomePage homePage = new HomePage(driver);
            homePage.clickLogin();

            LoginPage loginPage = new LoginPage(driver);
            loginPage.login("admin", "password");

            homePage.clickEmployeeDetails();
            homePage.clickManageUsers();
            homePage.clickLogoff();

        } finally {
            driver.quit();
        }
    }

    @Test
    public void CallingLLMClientTest() throws IOException, InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        WebDriver driver = new ChromeDriver(options);

        driver.navigate().to("http://eaapp.somee.com");
    }
}