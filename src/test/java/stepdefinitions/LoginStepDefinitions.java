package stepdefinitions;

import io.cucumber.java.en.*;
import io.qameta.allure.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import pages.LoginPage;
import utils.config.Endpoints;
import utils.config.PropertyManager;
import utils.driver.DriverClient;

public class LoginStepDefinitions {
    private final LoginPage loginPage;
    private final WebDriver driver;

    public LoginStepDefinitions() {
        this.loginPage = new LoginPage();
        this.driver = DriverClient.getDriver();
    }

    @Step("User navigates to login page")
    @Severity(SeverityLevel.CRITICAL)
    @Description("User goes to the login page and validates the UI elements.")
    @Given("user is on the login page")
    public void userIsOnTheLoginPage() {
     //   driver.get(Endpoints.getBaseUrl()+ Endpoints.getLoginUrl());
        loginPage.validateLoginPage();
    }

    @Step("User clicks on Sign Up button")
    @Severity(SeverityLevel.NORMAL)
    @Description("User clicks the sign-up button to navigate to the registration page.")
    @When("user clicks on signUp button")
    public void userClicksOnSignUpButton() {
        loginPage.clickSignUpButton();
    }

    @Step("User successfully logs in")
    @Severity(SeverityLevel.BLOCKER)
    @Description("User enters credentials and successfully logs into the system.")
    @When("user successfully logins")
    public void userSuccessfullyLogins() {
        loginPage.login(PropertyManager.getInstance().getProperty("email"), PropertyManager.getInstance().getProperty("password"));
    }

    @Step("User enters email: {email} and password: {password}")
    @Severity(SeverityLevel.CRITICAL)
    @Description("User enters the provided email and password for login.")
    @And("user enters {string} as the email and {string} as the password")
    public void userEntersAsTheEmailAndAsThePassword(String email, String password) {
        loginPage.login(email, password);
    }

    @Step("Verify error message: {errorMessage}")
    @Severity(SeverityLevel.NORMAL)
    @Description("Validate that the correct error message is displayed when login fails.")
    @Then("The error message {string} should be displayed")
    public void theErrorMessageShouldBeDisplayed(String errorMessage) {
        DriverClient.waitForElement(DriverClient.getDriver().findElement(By.xpath("//div[contains(text(), '"+errorMessage+"')]")));
        String actualErrorMessage= DriverClient.getDriver().findElement(By.xpath("//div[contains(text(), '"+errorMessage+"')]")).getText();
        System.out.println("actualErrorMessage = "+actualErrorMessage);
        Assert.assertTrue(errorMessage+"  is not accessible",actualErrorMessage.equalsIgnoreCase(errorMessage));
    }


    @Then("user verifies that they are on the {string} page")
    public void userVerifiesThatTheyAreOnThePage(String page) {
        DriverClient.wait(8);
        WebElement pageTitleWebElement=DriverClient.getDriver().findElement(By.xpath("//*[contains(text(),page)]"));
        DriverClient.waitForElement(pageTitleWebElement);
        String pageTitle=pageTitleWebElement.getText();
        System.out.println("Sayfa Başlığı = "+pageTitle);
        Assert.assertTrue("Dashboard page is not accessible",pageTitle.contains(page));

    }
}
