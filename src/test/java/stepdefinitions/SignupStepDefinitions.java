package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import pages.SignupPage;
import utils.config.PropertyManager;
import utils.driver.DriverClient;


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
        DriverClient.getDriver().get(PropertyManager.getInstance().getProperty("signupPageUrl"));
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

    @Step("User enters first name: {firstName}")
    @Severity(SeverityLevel.NORMAL)
    @Description("User fills in the first name field on the signup form")
    @And("User enters {string} in the first name field")
    public void userEntersInTheFirstNameField(String firstName) {
        signupPage.enterFirstName(firstName);
    }

    @Step("User enters last name: {lastName}")
    @Severity(SeverityLevel.NORMAL)
    @Description("User fills in the last name field on the signup form")
    @And("User enters {string} in the last name field")
    public void userEntersInTheLastNameField(String lastName) {
        signupPage.enterLastName(lastName);
    }

    @Step("User selects country: {country}")
    @Severity(SeverityLevel.CRITICAL)
    @Description("User selects the country from the dropdown")
    @And("User selects {string} from the country dropdown")
    public void userSelectsFromTheCountryDropdown(String country) {
        signupPage.selectCountry(country);
    }

    @Step("User enters mobile number: {mobileNumber}")
    @Severity(SeverityLevel.NORMAL)
    @Description("User enters a valid mobile number")
    @And("User enters {string} in the mobile number field")
    public void userEntersInTheMobileNumberField(String mobileNumber) {
        signupPage.enterPhoneNumber(mobileNumber);
    }

    @Step("User enters company name: {company}")
    @Severity(SeverityLevel.NORMAL)
    @Description("User enters a company name in the signup form")
    @And("User enters {string} in the company field")
    public void userEntersInTheCompanyField(String company) {
        signupPage.enterCompanyName(company);
    }

    @Step("User enters email: {email}")
    @Severity(SeverityLevel.CRITICAL)
    @Description("User enters an email address. If invalid, registration should fail.")
    @And("User enters {string} in the email field")
    public void userEntersInTheEmailField(String email) {
        signupPage.enterEmail(email);
    }

    @Step("User selects job title: {title}")
    @Severity(SeverityLevel.MINOR)
    @Description("User selects a job title from the dropdown")
    @And("User selects {string} from the title dropdown")
    public void userSelectsFromTheTitleDropdown(String title) {
        signupPage.selectJobTitle(title);
    }

    @Step("User enters password")
    @Severity(SeverityLevel.CRITICAL)
    @Description("User enters a secure password")
    @And("User enters {string} in the password field")
    public void userEntersInThePasswordField(String password) {
        signupPage.enterPassword(password);
    }

    @Step("User confirms password")
    @Severity(SeverityLevel.CRITICAL)
    @Description("User confirms the entered password")
    @And("User enters {string} in the confirm password field")
    public void userEntersInTheConfirmPasswordField(String confirmPassword) {
        signupPage.enterConfirmPassword(confirmPassword);
    }

    @Step("User accepts KVKK agreement")
    @Severity(SeverityLevel.NORMAL)
    @Description("User accepts the KVKK agreement before proceeding")
    @And("User accepts the KVKK agreement")
    public void userAcceptsTheKVKKAgreement() {
        signupPage.acceptTermsAndConditions();
    }

    @Step("Verify email input border is red and registration is not completed")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Ensure the email field is marked as incorrect and the registration cannot proceed")
    @Then("The email input border should be red, and registration should not be completed")
    public void theEmailInputBorderShouldBeRedAndRegistrationShouldNotBeCompleted() {
        signupPage.checkInputStatusError();
    }

    @And("User clicks agree and signup button")
    public void userClicksAgreeAndSignupButton() {
        signupPage.clickAgreeAndSignupButton();
    }
}
