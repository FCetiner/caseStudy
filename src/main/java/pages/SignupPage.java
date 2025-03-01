package pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import utils.driverClient.DriverClient;
import utils.util.JSUtils;
import utils.util.ReusableMethods;
import java.util.List;
import static base.BasePage.*;


public class SignupPage {
    WebDriver driver;

    public SignupPage(WebDriver driver) {
        this.driver = driver;
        validateSignUpPage();
    }

    private final By signUpHeader = By.xpath("//h1[.=\"Sign Up\"]");
    private final By firstNameTextBox = By.id("firstName");
    private final By lastNameTextBox = By.id("lastName");
    private final By phoneNumberTextBox = By.id("phoneNumber");
    private final By companyTextBox = By.id("companyName");
    private final By emailTextBox = By.xpath("//input[@type='email' and @formcontrolname='email']");
    private final By passwordTextBox = By.xpath("//input[@type='password' and @formcontrolname='password']");
    private final By passwordConfirmBox = By.xpath("//*[@formcontrolname='passwordConfirm']");
    private final By checkbox = By.xpath("//span[@class='checkbox-box']");
    private final By countryDropdown = By.xpath("//forceget-country-dropdown//nz-select");
    private final By countrySearchInput = By.xpath("//nz-select-search//input");
    private final By titleDropdown = By.xpath("//nz-select[@formcontrolname='jobTitle']");
    private final By titleSearchInput = By.xpath("//nz-select-search//input");
    private final By termsAndConditionsCheckbox = By.className("checkbox-box");
    private final By acceptButton = By.xpath("//button[.=' Accept ']");
    private final By agreeAndSignUpButton = By.xpath("//button[.=' Agree & Sign-Up ']");
    private final By signInButton = By.xpath("//*[.=\"Sign In\"]");
    private final By forcegetLink = By.xpath("//*[.=\" © 2024 Forceget \"]");
    private final By legalLink = By.xpath("//*[contains(text(),'Legal')]");
    private final By privacyLink = By.xpath("//*[contains(text(),'Privacy')]");

    public void validateSignUpPage() {
        try {
            Assert.assertTrue("signUpHeader Header not found", isElementVisible(signUpHeader));
            Assert.assertTrue("firstNameTextBox Header not found", isElementVisible(firstNameTextBox));
            Assert.assertTrue("lastNameTextBox Header not found", isElementVisible(lastNameTextBox));
            Assert.assertTrue("phoneNumberTextBox Header not found", isElementVisible(phoneNumberTextBox));
            Assert.assertTrue("companyTextBox Header not found", isElementVisible(companyTextBox));
            Assert.assertTrue("companyTextBox Header not found", isElementVisible(companyTextBox));
            Assert.assertTrue("emailTextBox not found!!", isElementVisible(emailTextBox));
            Assert.assertTrue("passwordTextBox not found!!", isElementVisible(passwordTextBox));
            Assert.assertTrue("passwordConfirmBox not found!!", isElementVisible(passwordConfirmBox));
            Assert.assertTrue("checkbox not found!!", isElementVisible(checkbox));
            Assert.assertTrue("termsAndConditionsCheckbox not found!!", isElementVisible(termsAndConditionsCheckbox));
            Assert.assertTrue("signInButton not found!!", isElementVisible(signInButton));
            Assert.assertTrue("forcegetLink not found!!", isElementVisible(forcegetLink));
            Assert.assertTrue("legalLink not found!!", isElementVisible(legalLink));
            Assert.assertTrue("privacyLink not found!!", isElementVisible(privacyLink));
        } catch (NoSuchElementException e) {
            System.err.println("An expected element was not found on the page! Error message: " + e.getMessage());
            e.printStackTrace(); // error details.
        }
    }

    public void fillsignupForm() {
        enterText(firstNameTextBox, "fistname");
        enterText(lastNameTextBox, "lastNameTextBox");
        clickElement(countryDropdown);
        WebElement searchInput = driver.findElement(By.xpath("//nz-select-search//input"));
        searchInput.sendKeys(Keys.ENTER);
        enterText(phoneNumberTextBox, "5996006060");
        enterText(companyTextBox, "companyTextBox");
        enterText(emailTextBox, "test@test.com");
        clickElement(titleDropdown);
        searchInput = driver.findElement(By.xpath("(//nz-select-search//input)[2]"));
        searchInput.sendKeys("Turkey");
        searchInput.sendKeys(Keys.ENTER);

        enterText(passwordTextBox, "Test12++");
        enterText(passwordConfirmBox, "Test12++");
        clickElement(termsAndConditionsCheckbox);
        WebElement acceptButtonWebElement = driver.findElement(By.xpath("//button[.=' Accept ']"));
        System.out.println("acceptButtonWebElement = " + acceptButtonWebElement.getText());
        JSUtils.scrollIntoView(acceptButtonWebElement);
        DriverClient.wait(1);
        DriverClient.waitForVisibility(acceptButtonWebElement);
        DriverClient.waitForClickability(acceptButtonWebElement);
        JSUtils.clickElementByJS(acceptButtonWebElement);
        DriverClient.waitAndClick(acceptButtonWebElement);

     //   WebElement agreeAndSignUpButtonWebElement = driver.findElement(By.xpath("//button[.=' Agree & Sign-Up ']"));
     //   DriverClient.waitForClickability(agreeAndSignUpButtonWebElement);
     //   JSUtils.clickElementByJS(agreeAndSignUpButtonWebElement);
        //enterOtpCode();
    }
    public void enterOtpCode() {
        String otpCode=ReusableMethods.getDateTime("mmHHdd");
        List<WebElement> otpInputs = driver.findElements(By.cssSelector("input.ant-input.otp-input"));
        DriverClient.waitForClickability(otpInputs.get(0));

        for (int i = 0; i < otpCode.length(); i++) {
            otpInputs.get(i).sendKeys(String.valueOf(otpCode.charAt(i)));
        }
    }

}
