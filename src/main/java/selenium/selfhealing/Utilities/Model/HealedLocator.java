package selenium.selfhealing.Utilities.Model;

public class HealedLocator {

    private String originalLocator;
    private String workingLocatorType;
    private String workingLocatorValue;
    private String healedAt;

    public String getHealedAt() {
        return healedAt;
    }

    public void setHealedAt(String healedAt) {
        healedAt = healedAt;
    }

    public String getWorkingLocatorValue() {
        return workingLocatorValue;
    }

    public void setWorkingLocatorValue(String workingLocatorValue) {
        this.workingLocatorValue = workingLocatorValue;
    }

    public String getWorkingLocatorType() {
        return workingLocatorType;
    }

    public void setWorkingLocatorType(String workingLocatorType) {
        this.workingLocatorType = workingLocatorType;
    }

    public String getOriginalLocator() {
        return originalLocator;
    }

    public void setOriginalLocator(String originalLocator) {
        this.originalLocator = originalLocator;
    }

}
