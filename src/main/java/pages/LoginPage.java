package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.junit.Assert;
import utils.driverClient.DriverClient;
import utils.util.ConfigReader;


public class LoginPage extends BasePage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        validateLoginPage();
    }

    private final By signInHeader = By.xpath("//h2[.=\"Sign In\"]");
    private final By emailTextBox = By.xpath("//input[@type='email' and @formcontrolname='email']");
    private final By passwordTextBox = By.xpath("//input[@type='password' and @formcontrolname='password']");
    private final By signInButton = By.xpath("//*[.=\" Sign In \"]");
    private final By signUpButton = By.xpath("//*[.=\" Sign-up \"]");
    private final By continueWithGoogleButton = By.xpath("//*[contains(text(),'Continue with Google')]");
    private final By forcegetLink = By.xpath("//*[.=\" © 2025 Forceget \"]");
    private final By legalLink = By.xpath("//*[contains(text(),'Legal')]");
    private final By privacyLink = By.xpath("//*[contains(text(),'Privacy')]");

    public void validateLoginPage() {
        try {
            Assert.assertTrue("Signin Header not found", isElementVisible(signInHeader));
            Assert.assertTrue("emailTextBox not found!!", isElementVisible(emailTextBox));
            Assert.assertTrue("passwordTextBox not found!!", isElementVisible(passwordTextBox));
            Assert.assertTrue("signInButton not found!!", isElementVisible(signInButton));
            Assert.assertTrue("signUpButton not found!!", isElementVisible(signUpButton));
            Assert.assertTrue("continueWithGoogleButton not found!!", isElementVisible(continueWithGoogleButton));
            Assert.assertTrue("forcegetLink not found!!", isElementVisible(forcegetLink));
            Assert.assertTrue("legalLink not found!!", isElementVisible(legalLink));
            Assert.assertTrue("privacyLink not found!!", isElementVisible(privacyLink));
        } catch (NoSuchElementException e) {
            System.err.println("An expected element was not found on the page! Error message: " + e.getMessage());
            e.printStackTrace(); // error details.
        }
    }

    public void getLoginPage() {
        DriverClient.getDriver().get(ConfigReader.getProperty("loginPageUrl"));
    }


    public void clickSignUpButton() {
        clickElement(signUpButton);
    }

    public void login(String email, String password)   {
        clear(emailTextBox);
        enterText(emailTextBox, email);
        clear(passwordTextBox);
        enterText(passwordTextBox, password);
        clickElement(signInButton);
    }
}
