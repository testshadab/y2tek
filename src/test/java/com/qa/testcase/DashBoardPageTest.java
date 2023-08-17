package com.qa.testcase;

import com.qa.testpackage.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.baseclass.BaseClass;
import com.qa.testpackage.DashBoardPage;
import com.qa.lib.TakeScreenshot;
import java.net.MalformedURLException;


public class DashBoardPageTest extends BaseClass {

    LoginPage loginPage;
    DashBoardPage dashBoardPage;

    public DashBoardPageTest() {
        super();
    }

    @BeforeMethod
    public void setup() throws MalformedURLException {
        initialization();
        loginPage = new LoginPage();
        dashBoardPage = new DashBoardPage();
    }


    @Test(priority = 10, description = "validate y2tek Dashbaord Page")
    public void navigateToDashboardPageTest() throws Exception {
        loginPage.y2kLogin(userName, password);
        dashBoardPage.homePage();
        logger1 = extent.createTest("Validation user on y2tek Dashboard Page");
        TakeScreenshot.captuerScreenshot(driver, "y2tekDashboard");
    }

    @Test(priority = 11, description = "validate y2tek Dashboard userView")
    public void userViewPageTest() throws Exception {
        loginPage.y2kLogin(userName, password);
        dashBoardPage.userViewPage();
        logger1 = extent.createTest("Validation user on y2tek Dashboard userView");
        TakeScreenshot.captuerScreenshot(driver, "y2tekDashboardUserView");
    }

    @Test(priority = 12, description = "validate y2tek Dashboard marketView")
    public void marketViewPageTest() throws Exception {
        loginPage.y2kLogin(userName, password);
        dashBoardPage.marketViewPage();
        logger1 = extent.createTest("Validation marketView ");
        logger1 = extent.createTest("Validation user on y2tek Dashboard marketView");
        TakeScreenshot.captuerScreenshot(driver, "y2tekDashboardMarketView");

    }
}

