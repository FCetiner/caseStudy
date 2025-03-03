package stepdefinitions;

import base.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import pages.LoginPage;
import pages.SignupPage;
import utils.driverClient.DriverClient;
import utils.util.Configs;

@Epic("User Authentication")
@Feature("Login Functionality")
public class LoginStepDefinitions {
    private final LoginPage loginPage;

    public LoginStepDefinitions() {
        this.loginPage = new LoginPage();
    }


    @Step("User navigates to login page")
    @Severity(SeverityLevel.CRITICAL)
    @Description("User goes to the login page and validates the UI elements.")
    @Given("user is on the login page")
    public void userIsOnTheLoginPage() {
        DriverClient.getDriver().get(Configs.getAllConfigs().get("ENVIRONMENT_DOMAIN"));
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
        loginPage.login(Configs.getAllConfigs().get("ENVIRONMENT_EMAIL"), Configs.getAllConfigs().get("ENVIRONMENT_PASSWORD"));
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
        DriverClient.waitForVisibility(DriverClient.getDriver().findElement(By.xpath("//div[contains(text(), '"+errorMessage+"')]")));
        String actualErrorMessage= DriverClient.getDriver().findElement(By.xpath("//div[contains(text(), '"+errorMessage+"')]")).getText();
        System.out.println("Actual Error Message = "+actualErrorMessage);
        Assert.assertTrue(errorMessage+" is not accessible", actualErrorMessage.equalsIgnoreCase(errorMessage));
    }

    @Step("Verify user is on Dashboard page")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Ensure that after successful login, the user is redirected to the Dashboard page.")
    @Then("user verifies that they are on the Dashboard page")
    public void userVerifiesThatTheyAreOnThePage() {
        String pageTitle = DriverClient.getDriver().findElement(By.xpath("//*[contains(text(),'Dashboard')]")).getText();
        System.out.println("Page Title = " + pageTitle);
        Assert.assertTrue("Dashboard page is not accessible", pageTitle.equalsIgnoreCase("Dashboard"));
    }
}
