package stepdefinitions;

import base.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import pages.SignupPage;
import utils.driverClient.DriverClient;
import utils.util.ConfigReader;

@Epic("User Registration")
@Feature("Signup Functionality")
public class SignupStepDefinitions {
    private final SignupPage signupPage;

    public SignupStepDefinitions() {
        this.signupPage = new SignupPage();
    }

    @Step("User navigates to the signup page")
    @Severity(SeverityLevel.CRITICAL)
    @Description("User goes to the signup page and validates the UI elements.")
    @Given("user is on the signup page")
    public void userIsOnTheSignupPage() {
        DriverClient.getDriver().get(ConfigReader.getProperty("signupPageUrl"));
        System.out.println("User navigated to the signup page.");
    }

    @Step("User enters valid details and submits the form")
    @Severity(SeverityLevel.BLOCKER)
    @Description("User fills the signup form with valid data and submits it.")
    @When("user enters valid details and submits the form")
    public void userEntersValidDetails() {
        signupPage.fillsignupForm();
    }

    @Step("Verify user is on the Sign Up page")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Ensure that the user is redirected to the Sign Up page after form submission.")
    @Then("user verifies that they are on the Sign up page")
    public void userVerifiesThatTheyAreOnThePage() {
        String pageTitle = DriverClient.getDriver().findElement(By.xpath("//*[contains(text(),'Sign Up')]")).getText();
        System.out.println("Page Title = " + pageTitle);
        Assert.assertTrue("Signup page is not accessible", pageTitle.equalsIgnoreCase("Sign Up"));
    }
}
