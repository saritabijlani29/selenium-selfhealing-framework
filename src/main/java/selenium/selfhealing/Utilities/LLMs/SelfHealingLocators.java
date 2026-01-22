package selenium.selfhealing.Utilities.LLMs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.selfhealing.Utilities.Model.HealedLocator;
import selenium.selfhealing.Utilities.Model.LocatorSuggestion;

import java.io.IOException;
import java.util.*;

public class SelfHealingLocators {
    private WebDriver driver;
    private By currentStrategy;
    private Map<String, By> locatorStrategy;
    private String originalStrategy;

    public SelfHealingLocators(WebDriver driver, By primaryLocator) {
        this.driver = driver;
        this.currentStrategy = primaryLocator;
        this.locatorStrategy = new HashMap<>();
        this.locatorStrategy.put("primary", primaryLocator);
        this.originalStrategy = primaryLocator.toString();
    }

    public WebElement findElement(int retryAttempts) throws IOException, InterruptedException {
        //Step 1 - Try finding element using current strategy
        WebElement element = tryFindElementWithCurrentStrategy();
        if (element != null) return element;

        //Step 1.5 - Try findingElement using cache
        element = tryFindElementUsingCache();
        if (element != null) return element;

        //Step 2 - Try finding element using Alternative Strategy
        element = tryAlternativeStrategy();
        if (element != null) return element;

        if (retryAttempts > 0) {
            healUsingAI();
            return findElement(retryAttempts - 1);
        }
        throw new NoSuchElementException("Failed to locator the element after the all healing attempts " + retryAttempts);
    }

    public WebElement tryFindElementWithCurrentStrategy() {
        try {
            return driver.findElement(currentStrategy);
        } catch (Exception e) {
            return null;
        }

    }

    public WebElement tryAlternativeStrategy() {
        if (locatorStrategy.size() <= 1) return null;
        for (Map.Entry<String, By> entry : locatorStrategy.entrySet()) {
            By strategy = entry.getValue();
            if (entry.getValue().equals(currentStrategy)) continue;
            try {
                WebElement element = driver.findElement(strategy);
                SaveSuccessfulLocatorToCache(entry.getKey(),entry.getValue());
                currentStrategy = strategy;
                return element;

            } catch (NoSuchElementException | IOException e) {
                throw new RuntimeException("No such element " + currentStrategy);
            }

        }
        return null;
    }

    public void healUsingAI() throws IOException, InterruptedException {
        String pageSource = driver.getPageSource();

        String strategyString = currentStrategy.toString();
        int index = strategyString.indexOf(":");
        String locatorType = strategyString.substring(0, index);
        String locatorValue = strategyString.substring(index + 1).trim();
        LLMClient llmClient = new LLMClient();
        LocatorSuggestion locatorSuggestion = GetLocatorFromLLMs.GetHealedLocator(llmClient, pageSource, locatorType, locatorValue);
        tryCreateAIGeneratedLocator(locatorSuggestion);
        System.out.println("Added successfully " + tryCreateAIGeneratedLocator(locatorSuggestion) + " locator in locatorStrategy");

    }

    public int tryCreateAIGeneratedLocator(LocatorSuggestion loc) {
        int count = 0;
        count += addIfValid("id", loc.getID());
        count += addIfValid("xpath", loc.getXpath());
        count += addIfValid("classname", loc.getClassName());
        count += addIfValid("cssselector", loc.getCSSSelector());
        count += addIfValid("name", loc.getName());
        count += addIfValid("linktext", loc.getLinkText());
        return count;
    }

    public int addIfValid(String key, String value) {
        By locator = null;
        if (value != null && !value.isBlank()) {
            locator = generateLocatorUsingTypeAndValue(key, value);
            locatorStrategy.put(key.toLowerCase(), locator);
            return 1;
        }
        return 0;
    }

    private WebElement tryFindElementUsingCache() throws IOException {
        LocatorCache locatorcache = new LocatorCache();
        HealedLocator cacheLocator = locatorcache.cache.stream()
                .filter(item -> item.getOriginalLocator().equalsIgnoreCase(originalStrategy))
                .findFirst().orElse(null);
        if (cacheLocator != null) {
            try {
                By locator = generateLocatorUsingTypeAndValue(cacheLocator.getWorkingLocatorType(), cacheLocator.getWorkingLocatorType());
                if(locator != null) {
                    WebElement element =  driver.findElement(locator);
                    currentStrategy = locator;
                    return element;
                }
            }catch (Exception e){
                throw new RuntimeException("Cached Healed locator No longer works" + e);
            }
        }

        return null;

    }

    private By generateLocatorUsingTypeAndValue(String key, String value) {
        By locator;
        switch (key.toLowerCase()) {
            case "id" -> locator = By.id(value);
            case "name" -> locator = By.name(value);
            case "classname" -> locator = By.className(value);
            case "cssselector" -> locator = By.cssSelector(value);
            case "xpath" -> locator = By.xpath(value);
            case "linktext" -> locator = By.linkText(value);
            default -> throw new IllegalArgumentException("Unknown locator type: " + key);
        }
        return locator;
    }
    private void SaveSuccessfulLocatorToCache(String key, By locatorStrategy) throws IOException {
        LocatorCache locatorCache = new LocatorCache();
        int separatorIndex = locatorCache.toString().indexOf(":");

        String locatorValue = locatorStrategy.toString().substring(separatorIndex + 1).trim();
        locatorCache.saveHealedLocatorIntoCache(originalStrategy ,key, locatorValue);

    }


}


