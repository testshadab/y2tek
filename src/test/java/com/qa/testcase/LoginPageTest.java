package com.qa.testcase;

import java.net.MalformedURLException;
import com.qa.testpackage.LoginPage;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.baseclass.BaseClass;
import com.qa.lib.TakeScreenshot;

@Log4j2
public class LoginPageTest extends BaseClass {

    LoginPage loginPage;

    @BeforeMethod
    public void setup() throws MalformedURLException {
        initialization();
        loginPage = new LoginPage();
    }

    public LoginPageTest() {
        super();
    }

    @Test(priority = 1, description = "validate y2tek login Page")
    public void theLandingPage() throws Exception {
        logger1 = extent.createTest("Validation user on y2tek login Page");
        loginPage.userShouldBeToOpenTheLandingPage(url);
        log.info("login page is  present in y2tek.com Application");
        TakeScreenshot.captuerScreenshot(driver, "y2tekLogin");
    }

    @Test(priority = 2, description = "Validation of Logo On y2tek login Page")
    public void logoTest() throws Exception {
        logger1 = extent.createTest("Validation of Logo On y2tek login Page");
        boolean LogTest = loginPage.LogoPresent();
        Assert.assertTrue(LogTest, "Logo is  present in y2tek.com Application");
        logger.info("Logo is  present in y2tek.com Application");
        TakeScreenshot.captuerScreenshot(driver, "logo");
    }

    @Test(priority = 3, description = "y2kLogin user Login Page")
    public void loginTest() throws Exception {
        logger1 = extent.createTest("Validation of y2kLogin  Login");
        loginPage.y2kLogin(userName, password);
        logger.info("y2kLogin user Login Validation is Successfully");
        TakeScreenshot.captuerScreenshot(driver, "y2tekLogin");
    }


    @Test(priority = 4, description = "Login with invalid user")
    public void logInAsValidUserAndAnInvalidPassword() throws Exception {
        logger1 = extent.createTest("In the Invalid pass method");
        loginPage.loginWithInvalidCreds(User2, inValidPassword);
        loginPage.errorMessage();
        logger.info("y2kLogin user getting error message");
        TakeScreenshot.captuerScreenshot(driver, "invaliduser");
    }

    @Test(priority = 5, description = "Login with invalid OTP")
    public void loginWithOTPIsNotValid() throws Exception {
        logger1 = extent.createTest("In the Invalid OTP method");
        loginPage.invalidOTPLogin(userName, password);
        loginPage.enteredOTPIsNotValid();
        logger.info("y2kLogin user getting error message for invalid Otp");
        TakeScreenshot.captuerScreenshot(driver, "invalidOTP");
    }

    @Test(priority = 6, description = "Reset password")
    public void userShouldNavigateToResetPasswordPage() throws Exception {
        logger1 = extent.createTest("In the Reset Password method");
        loginPage.resetPasswordPage();
        logger.info("ResetPassword");
        logger.info("y2kLogin user In the Reset Password method");
        TakeScreenshot.captuerScreenshot(driver, "ResetPassword");
    }

    @Test(priority = 7, description = "forgot Password")
    public void forgotPassword() throws Exception {
        logger1 = extent.createTest("In the forgot Password method");
        loginPage.resetPasswordPage();
        loginPage.resetEmail(userName);
        loginPage.validateResetOTPLabel();
        logger.info("y2kLogin user In the forget Password method");
        TakeScreenshot.captuerScreenshot(driver, "ForgetPassword");
    }


    @Test(priority = 8, description = "Click password ")
    public void forgetSearchPage() throws Exception {
        logger1 = extent.createTest("Forgot password link");
        loginPage.forgetLink();
        logger.info("forget link  in y2tek.com Application");
        TakeScreenshot.captuerScreenshot(driver, "forgetpassword");

    }

    @Test(priority = 9, description = "Check Registration user back to login pag ")
    public void navigateSignUpToLoginPage() throws Exception {
        logger1 = extent.createTest("Validate Sign up Page to login page ");
        loginPage.backToLoginPage();
        logger.info("forget link  in y2tek.com Application");
        TakeScreenshot.captuerScreenshot(driver, "LoginPage");

    }


}
