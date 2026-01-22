# Selenium Self-Healing Automation Framework

An open-source Maven-based Selenium automation framework designed to automatically recover from broken locators and reduce flaky UI tests caused by application changes.

The framework follows a Page Object Model (POM) design and persists healed locators so that future executions become faster and more stable.

---

## Features

- Self-healing Selenium locators  
- JSON-based storage for healed elements  
- Page Object Model (POM) structure  
- Pluggable healing strategies  
- Locator caching  
- Maven build system  
- CI/CD friendly design  

---

## How It Works

1. Tests interact with Page classes.
2. When a locator fails, the self-healing engine evaluates alternative strategies.
3. Valid locators are selected based on confidence scoring.
4. The healed locator is persisted for reuse.
5. Future runs reuse stored locators before attempting recovery again.




