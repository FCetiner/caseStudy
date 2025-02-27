package stepdefinitions;

import base.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.SignupPage;
import utils.driverClient.DriverClient;


public class SignupStepDefinitions {
    private final SignupPage signupPage;


    public SignupStepDefinitions()  {
        this.signupPage = BaseTest.getSignupPage();
    }

    @Given("user is on the signup page")
    public void userIsOnTheSignupPage() {

    }

    @When("user enters valid details and submits the form")
    public void userEntersValidDetails() {
       signupPage.fillsignupForm();
    }

    @Then("user verifies that they are on the Sign up page")
    public void userVerifiesThatTheyAreOnThePage() {
        String pageTitle=DriverClient.getDriver().findElement(By.xpath("//*[contains(text(),'Sign Up')]")).getText();
        System.out.println("Sayfa Başlığı = "+pageTitle);
        Assert.assertTrue(" Signup page is not accessible",pageTitle.equalsIgnoreCase("Sign Up"));
    }

}
