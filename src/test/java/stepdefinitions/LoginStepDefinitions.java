package stepdefinitions;

import base.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.LoginPage;
import utils.driverClient.DriverClient;
import utils.util.Configs;


public class LoginStepDefinitions {
    private final LoginPage loginPage;


    public LoginStepDefinitions()  {
        this.loginPage = BaseTest.getLoginPage();
    }

    @Given("user is on the login page")
    public void userIsOnTheLoginPage() {
        DriverClient.getDriver().get(Configs.getAllConfigs().get("ENVIRONMENT_DOMAIN"));
        loginPage.validateLoginPage();
    }


    @When("user clicks on signUp button")
    public void userClicksOnSignUpButton() {
        loginPage.clickSignUpButton();
    }

    @When("user successfully logins")
    public void userSuccessfullyLogins() {
        loginPage.login(Configs.getAllConfigs().get("ENVIRONMENT_EMAIL"), Configs.getAllConfigs().get("ENVIRONMENT_PASSWORD"));
    }

    @And("user enters {string} as the email and {string} as the password")
    public void userEntersAsTheEmailAndAsThePassword(String email, String password) {
        loginPage.login(email, password);
    }

    @Then("The error message {string} should be displayed")
    public void theErrorMessageShouldBeDisplayed(String errorMessage) {
        DriverClient.waitForVisibility(DriverClient.getDriver().findElement(By.xpath("//div[contains(text(), '"+errorMessage+"')]")));
        String actualErrorMessage= DriverClient.getDriver().findElement(By.xpath("//div[contains(text(), '"+errorMessage+"')]")).getText();
        System.out.println("actualErrorMessage = "+actualErrorMessage);
        Assert.assertTrue(errorMessage+"  is not accessible",actualErrorMessage.equalsIgnoreCase(errorMessage));
    }



    @Then("user verifies that they are on the Dashboard page")
    public void userVerifiesThatTheyAreOnThePage() {
        String pageTitle=DriverClient.getDriver().findElement(By.xpath("//*[contains(text(),'Dashboard')]")).getText();
        System.out.println("Sayfa Başlığı = "+pageTitle);
        Assert.assertTrue("Dashboard page is not accessible",pageTitle.equalsIgnoreCase("Dashboard"));
    }


}
