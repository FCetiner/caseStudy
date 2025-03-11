# UI Test Automation Project

##  Overview

This project is a **UI Test Automation Framework** built using:

- **Java**
- **Selenium WebDriver**
- **Cucumber (BDD)**
- **JUnit**
- **Allure Reporting**

The project is designed to automate UI tests for the web application, following the **Page Object Model (POM)** for better maintainability and scalability.

---

##  Project Structure

```
project-root/
│── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── base/               # Base classes for framework support
│   │   │   ├── pages/              # Page Object classes (e.g., LoginPage)
│   │   │   ├── utils/              # Utility classes (config, driver, etc.)
│   ├── test/
│   │   ├── java/
│   │   │   ├── stepdefinitions/    # Step definitions for Cucumber
│   │   │   ├── runners/            # Cucumber test runner
│   │   │   ├── hooks/              # Hooks (before/after scenarios)
│   ├── resources/
│   │   ├── features/               # Cucumber feature files
│   │   ├── config.properties       # Configuration file
│── pom.xml                          # Maven dependencies
│── README.md                        # Project documentation
```

---

## 🚀 How to Run the Tests

### **1️⃣ Setup & Installation**

1. Install **Java 11+**
2. Install **Maven**
3. Clone the repository:
   ```sh
   git clone <repo-url>
   cd project-root
   ```
4. Install dependencies:
   ```sh
   mvn clean install
   ```

### **2️⃣ Run Tests**

- Run all tests:
  ```sh
  mvn test
  ```
- Run specific tag (e.g., `@smoke`):
  ```sh
  mvn test -Dcucumber.filter.tags="@smoke"
  ```
- Run failed tests:
  ```sh
  mvn test -Dcucumber.filter.tags="@rerun"
  ```

### **3️⃣ View Reports**

- **Cucumber HTML Report:** Open `test-output/HtmlReports/default-cucumber-reports.html`
- **Allure Report:**
  ```sh
  mvn allure:serve
  ```

---

## 🛠 Configuration

Modify `configuration.properties` to set environment variables:

```
browser=chrome
baseUrl=https://example.com
```

---

## 📜 Test Design

### **1️⃣ Page Object Model (POM)**

Each **Page Class** contains: ✅ Locators (`By` objects) ✅ Page-specific methods (e.g., `login()`, `validateLoginPage()`)

### **2️⃣ Step Definitions**

Each **Step Definition Class**: ✅ Calls methods from **Page Objects** ✅ Maps Gherkin steps to Java methods

Example:

```java
@When("User enters email {string} and password {string}")
public void user_enters_email_and_password(String email, String password) {
    loginPage.login(email, password);
}
```

## License

This project is licensed under the MIT License.

