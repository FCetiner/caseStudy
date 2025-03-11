package pages;

import base.BasePage;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import utils.driver.DriverClient;
import utils.helper.JSUtils;
import utils.helper.ReusableMethods;
import java.util.List;


public class SignupPage extends BasePage {
    @FindBy(xpath = "//input[contains(@class, 'ant-input-status-error')]")
    public WebElement inputStatusErrorWebElement;

    @FindBy(xpath = "//button[.=' Agree & Sign-Up ']")
    public WebElement agreeAndSignUpButtonWebElement;

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
    private final By forcegetLink = By.xpath("//*[.=\" © 2025 Forceget \"]");
    private final By legalLink = By.xpath("//*[contains(text(),'Legal')]");
    private final By privacyLink = By.xpath("//*[contains(text(),'Privacy')]");
    private final By inputStatusError = By.xpath("//input[contains(@class, 'ant-input-status-error')]");



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
            throw e;
        }
    }

    public void fillsignupForm() {
        enterText(firstNameTextBox, "firstname");
        enterText(lastNameTextBox, "lastName");
        clickElement(countryDropdown);

        WebElement searchInput = getDriver().findElement(By.xpath("//nz-select-search//input"));
        searchInput.sendKeys("Turkey");
        searchInput.sendKeys(Keys.ENTER);

        enterText(phoneNumberTextBox, "5996006060");
        enterText(companyTextBox, "companyTextBox");
        enterText(emailTextBox, "test@test.com");

        clickElement(titleDropdown);
        WebElement searchInput2 = getDriver().findElement(By.xpath("(//nz-select-search//input)[2]"));
        DriverClient.waitForElement(searchInput2);
        searchInput2.sendKeys("Test Engineer");
        DriverClient.waitForElement(searchInput2);
        searchInput2.sendKeys(Keys.ENTER);

        enterText(passwordTextBox, "Test12++");
        enterText(passwordConfirmBox, "Test12++");
        clickElement(termsAndConditionsCheckbox);

        WebElement acceptButtonWebElement = getDriver().findElement(By.xpath("//button[.=' Accept ']"));
        waitForVisibility(acceptButtonWebElement);
        waitForClickability(acceptButtonWebElement);
        DriverClient.waitForElement(acceptButtonWebElement);
        clickWithJS(acceptButtonWebElement);
    }
    public void enterFirstName(String firstName) {
        enterText(firstNameTextBox, firstName);
    }

    public void enterLastName(String lastName) {
        enterText(lastNameTextBox, lastName);
    }

    public void selectCountry(String country) {
        clickElement(countryDropdown);
        WebElement searchInput = getDriver().findElement(By.xpath("//nz-select-search//input"));
        searchInput.sendKeys(country);
        searchInput.sendKeys(Keys.ENTER);
    }

    public void enterPhoneNumber(String phoneNumber) {
        enterText(phoneNumberTextBox, phoneNumber);
    }

    public void enterCompanyName(String companyName) {
        enterText(companyTextBox, companyName);
    }

    public void enterEmail(String email) {
        enterText(emailTextBox, email);
    }

    public void selectJobTitle(String jobTitle) {
        clickElement(titleDropdown);
        WebElement searchInput = getDriver().findElement(By.xpath("(//nz-select-search//input)[2]"));
        DriverClient.waitForElement(searchInput);
        searchInput.sendKeys(jobTitle);
        searchInput.sendKeys(Keys.ENTER);
    }

    public void enterPassword(String password) {
        enterText(passwordTextBox, password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        enterText(passwordConfirmBox, confirmPassword);
    }

    public void acceptTermsAndConditions() {
        clickElement(termsAndConditionsCheckbox);
        clickAcceptButton();
    }

    public void clickAcceptButton() {
        WebElement acceptButtonWebElement = getDriver().findElement(By.xpath("//button[.=' Accept ']"));
        waitForVisibility(acceptButtonWebElement);
        waitForClickability(acceptButtonWebElement);
        DriverClient.waitForElement(acceptButtonWebElement);
        clickWithJS(acceptButtonWebElement);
    }

    public void fillSignupForm(String firstName, String lastName, String country, String phoneNumber,
                               String companyName, String email, String jobTitle, String password) {
        enterFirstName(firstName);
        enterLastName(lastName);
        selectCountry(country);
        enterPhoneNumber(phoneNumber);
        enterCompanyName(companyName);
        enterEmail(email);
        selectJobTitle(jobTitle);
        enterPassword(password);
        enterConfirmPassword(password);
        acceptTermsAndConditions();
        clickAcceptButton();
    }

    public void enterOtpCode() {
        String otpCode = ReusableMethods.getDateTime("mmHHdd");
        List<WebElement> otpInputs = getDriver().findElements(By.cssSelector("input.ant-input.otp-input"));
        waitForClickability(otpInputs.get(0));

        for (int i = 0; i < otpCode.length(); i++) {
            otpInputs.get(i).sendKeys(String.valueOf(otpCode.charAt(i)));
        }
    }

    public void checkInputStatusError(){
        Assert.assertTrue("inputStatusErrorWebElement not displayed",inputStatusErrorWebElement.isDisplayed());
    }
    public void clickAgreeAndSignupButton() {
        DriverClient.waitForElement(agreeAndSignUpButtonWebElement);
        JSUtils.scrollIntoView(agreeAndSignUpButtonWebElement);
        JSUtils.clickElementByJS(agreeAndSignUpButtonWebElement);
    }
}