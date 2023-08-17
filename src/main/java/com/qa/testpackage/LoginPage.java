package com.qa.testpackage;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.qa.baseclass.BaseClass;
import org.testng.Assert;
import java.util.List;
@Log4j2
public class LoginPage extends BaseClass {

    @FindBy(css = "[class*='company-logo d-none d-md-flex']")
    WebElement logoOfApplication;
    @FindBy(name = "username")
    WebElement emailTextBox;
    @FindBy(name = "password")
    WebElement passwordTextBox;
    @FindBy(css = "[type*='submit']")
    WebElement signInButton;
    @FindBy(css = "button[class*='btn-purple']")
    WebElement verifyOtp;
    @FindBy(css = "[class*='signup-text']")
    WebElement signUpLink;
    @FindBy(css = "[class*='welcome-text-blk']")
    WebElement createAccountTitle;
    @FindBy(css = "div[id='action-alert']")
    WebElement errorMsg;
    @FindBy(css = "input[type='password']")
    public List<WebElement> otpTextFields;
    @FindBy(css = "div[id='otp'] div")
    public WebElement otpCommon;
    @FindBy(css = "div[id='action-alert']")
    public WebElement otpErrorMsg;
    @FindBy(css = "a[href='/forgot-password']")
    public WebElement forgotPassword;
    @FindBy(css = "span[class='welcome-text-blue']")
    public WebElement resetPasswordHeading;
    @FindBy(css = "button[class*='btn-purple']")
    public WebElement sendLinkCTA;
    @FindBy(css = "span[class='welcome-text-blk']")
    public WebElement enterOTPMsg;
    @FindBy(css = "div[class='loader']")
    public WebElement loadingBar;
    @FindBy(css = "[href*='forgot-password']")
    WebElement forgetPassword;

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public boolean LogoPresent() {
        return logoOfApplication.isDisplayed();
    }

    public void y2kLogin(String user, String password){
        log.info("Test Login");
        waitForElementToBeClickable(emailTextBox);
        emailTextBox.sendKeys(user);
        passwordTextBox.sendKeys(password);
        signInButton.click();
        waitForElementToBeInVisible(loadingBar);
        enterOTP();
        waitForElementToBeClickable(verifyOtp);
        verifyOtp.click();
        waitForElementToBeInVisible(loadingBar);
    }

    public void enterOTP() {
        waitForWebElementToAppear(otpCommon);
        for (WebElement OTPList : otpTextFields) {
            String listIndex = OTPList.getAttribute("id");
            OTPList.sendKeys(listIndex);
        }
    }

    public void loginWithInvalidCreds(String User2, String Password){
        emailTextBox.sendKeys(User2);
        passwordTextBox.sendKeys(Password);
        signInButton.click();
    }

    public void invalidOTPLogin(String user, String password){
        emailTextBox.sendKeys(user);
        waitForElementToBeClickable(passwordTextBox);
        passwordTextBox.sendKeys(password);
        waitForElementToBeClickable(signInButton);
        signInButton.click();
    }

    public void errorMessage() {
        waitForWebElementToAppear(errorMsg);
        errorMessageIsPresent();
        Assert.assertTrue(errorMessageIsPresent());
    }

    public boolean errorMessageIsPresent() {
        return errorMsg.isDisplayed();
    }

    public void enteredOTPIsNotValid() throws InterruptedException {
        waitForWebElementToAppear(otpCommon);
        for (WebElement OTPList : otpTextFields) {
            String listIndex = OTPList.getAttribute("id");
            OTPList.sendKeys(listIndex + "1");
        }
        Thread.sleep(2000);
        verifyOtp.click();
        waitForWebElementToAppear(otpErrorMsg);
        Assert.assertTrue(OTPErrorMessageIsPresent());
    }

    public boolean OTPErrorMessageIsPresent() {
        return otpErrorMsg.isDisplayed();

    }

    public void resetPasswordPage() {
        waitForWebElementToAppear(forgotPassword);
        forgotPassword.click();
        Assert.assertTrue(resetPasswordMessageIsPresent());
    }

    public boolean resetPasswordMessageIsPresent() {
        return resetPasswordHeading.isDisplayed();

    }

    public void userShouldBeToOpenTheLandingPage(String URL){
        URL = driver.getCurrentUrl();
        Assert.assertEquals("https://dev.y2tek.io/", URL);
    }

    public void resetEmail(String UserName) {
        emailTextBox.sendKeys(UserName);
        sendLinkCTA.click();
    }

    public void validateResetOTPLabel() {
        boolean otpLabel = enterOTPMsg.isDisplayed();
        Assert.assertTrue(otpLabel);
        driver.navigate().back();
        signInButton.isDisplayed();
    }

    public void backToLoginPage() {
        waitForElementToBeVisible(signUpLink);
        signUpLink.click();
        Assert.assertEquals(createAccountTitle.getText().contains("Create an Account"), "Create an Account");
        waitForElementToBeVisible(signInButton);
        signInButton.click();
        Assert.assertEquals(createAccountTitle.getText().contains("Sign In to your Account"), "Sign In to your Account");
    }

    public void forgetLink() {
        waitForElementToBeClickable(forgetPassword);
        forgetPassword.click();
    }

}



