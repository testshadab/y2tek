package com.qa.testcase;

import com.qa.baseclass.BaseClass;
import com.qa.testpackage.DashBoardPage;
import com.qa.testpackage.LoginPage;
import com.qa.testpackage.PaperTradingPage;
import com.qa.testpackage.WalletPage;
import com.qa.lib.TakeScreenshot;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.net.MalformedURLException;

public class PaperTradingPageTest extends BaseClass {
    LoginPage loginPage;
    DashBoardPage dashBoardPage;
    WalletPage walletPage;
    PaperTradingPage paperTradingPage;
    public PaperTradingPageTest() {
        super();
    }

    @BeforeMethod
    public void setup() throws MalformedURLException {
        initialization();
        loginPage = new LoginPage();
        dashBoardPage = new DashBoardPage();
        walletPage = new WalletPage();
        paperTradingPage=new PaperTradingPage();

    }

    @Test(priority = 18,description = "validate PaperTrading  Page")
    public void navigateToPaperTradingPage() throws Exception {
        loginPage.y2kLogin(userName, password);
        paperTradingPage.paperTrading();
        logger1 = extent.createTest("Validation user PaperTrading page");
        TakeScreenshot.captuerScreenshot(driver, "PaperTrading");
    }

    @Test(priority = 19,description = "create a PaperTrading  Page")
    public void createPaperTradingWithStartTest() throws Exception {
        loginPage.y2kLogin(userName, password);
        paperTradingPage.createPaperTradingWithStart();
        logger1 = extent.createTest("create a PaperTrading");
        TakeScreenshot.captuerScreenshot(driver, "CreatePaperTrading");
    }

    @Test(priority = 20,description = "create a paperTradingSchedule")
    public void createPaperTradingWithScheduleTest() throws Exception {
        loginPage.y2kLogin(userName, password);
        paperTradingPage.createPaperTradingWithSchedule();
        logger1 = extent.createTest("create a PaperTrading Schedule");
        TakeScreenshot.captuerScreenshot(driver, "SchedulePaperTrading");
    }

    @Test(priority = 21)
    public void paperTradingDelete() throws Exception {
        loginPage.y2kLogin(userName, password);
        paperTradingPage.deletePaperTrading();
        logger1 = extent.createTest("Delete a PaperTrading Schedule");
        TakeScreenshot.captuerScreenshot(driver, "DeletePaperTrading");
    }

    @Test(priority = 22,description = "Create a paperTrading with Start risk")
    public void TradingWithStartRiskManagement() throws Exception {
        loginPage.y2kLogin(userName, password);
        paperTradingPage.createPaperTradingWithStartRiskM();
        logger1 = extent.createTest("paperTrading with Start risk");
        TakeScreenshot.captuerScreenshot(driver, "paperTradingStartRisk");
    }

    @Test(priority = 23, description = " create a paperTrading with StartExecution")
    public void TradingWithStartExecution() throws Exception {
        loginPage.y2kLogin(userName, password);
        paperTradingPage.createPaperTradingWithStartExecution();
        logger1 = extent.createTest("paperTrading StartExecution");
        TakeScreenshot.captuerScreenshot(driver, "StartExecution");
    }


    @Test(priority = 24,description = "Paper Trading with scheduleRisk")
    public void TradingWithScheduleRiskManagement() throws Exception {
        loginPage.y2kLogin(userName, password);
        paperTradingPage.createPaperTradingWithScheduleRiskM();
        logger1 = extent.createTest("paperTrading scheduleRisK");
        TakeScreenshot.captuerScreenshot(driver, "scheduleRisk");
    }

    @Test(priority = 25,description = "Paper Trading with Trading Schedule")
    public void TradingWithScheduleExecution() throws Exception {
        loginPage.y2kLogin(userName, password);
        paperTradingPage.createPaperTradingWithScheduleExecution();
        logger1 = extent.createTest("paperTrading Schedule Risk");
        TakeScreenshot.captuerScreenshot(driver, "paperTradingSchedule");
    }
}
