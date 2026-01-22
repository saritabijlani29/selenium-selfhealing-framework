package selenium.selfhealing.Utilities.Extensions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.selfhealing.Utilities.LLMs.SelfHealingLocators;

public class WebDriverExtensions {

    public static WebElement AIFindElement(WebDriver driver, By locator) {
        try {
            SelfHealingLocators selfHealingLocators = new SelfHealingLocators(driver, locator);
            return selfHealingLocators.findElement(2);
        } catch (Exception e) {
            throw new RuntimeException("Failed to locate field using AI locator", e);
        }
    }
}
