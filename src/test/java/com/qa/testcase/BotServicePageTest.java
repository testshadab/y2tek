package com.qa.testcase;

import com.qa.baseclass.BaseClass;
import com.qa.testpackage.*;
import com.qa.lib.TakeScreenshot;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class BotServicePageTest extends BaseClass {
    LoginPage loginPage;
    DashBoardPage dashBoardPage;
    WalletPage walletPage;
    PaperTradingPage paperTradingPage;

    TransactionsPage transactionsPage;
    BotServicePage botServicePage;

    public BotServicePageTest() {
        super();
    }

    @BeforeMethod
    public void setup() throws MalformedURLException {
        initialization();
        loginPage = new LoginPage();
        dashBoardPage = new DashBoardPage();
        walletPage = new WalletPage();
        paperTradingPage = new PaperTradingPage();
        transactionsPage = new TransactionsPage();
        botServicePage = new BotServicePage();

    }

    @Test(priority = 30, description = "navigate to bot service page")
    public void navigateToBotServicePageTest() throws Exception {
        loginPage.y2kLogin(userName, password);
        botServicePage.botService();
        logger1 = extent.createTest("Validation boat service");
        TakeScreenshot.captuerScreenshot(driver, "boatService");

    }

    @Test(priority = 31, description = "create a  bot service to start")
    public void createBotServiceWithStartTest() throws Exception {
        loginPage.y2kLogin(userName, password);
        botServicePage.createBotServiceWithStart();
        logger1 = extent.createTest("create a boat service with start");
        TakeScreenshot.captuerScreenshot(driver, "boatServiceStart");
    }

    @Test(priority = 32, description = "create a  bot service to Schedule")
    public void createBotServiceWithScheduleTest() throws Exception {
        loginPage.y2kLogin(userName, password);
        botServicePage.createBotServiceWithSchedule();
        logger1 = extent.createTest("create a boat service with Schedule");
        TakeScreenshot.captuerScreenshot(driver, "boatServiceSchedule");
    }

    @Test(priority = 33, description = "Delete a  bot service")
    public void botServiceDelete() throws Exception {
        loginPage.y2kLogin(userName, password);
        botServicePage.deleteBotService();
        logger1 = extent.createTest("Delete a boat service with Schedule");
        TakeScreenshot.captuerScreenshot(driver, "Deletebot");
    }


    @Test(priority = 34, description = "Create a boat service with Schedule")
    public void botServiceWithStartRiskManagement() throws Exception {
        loginPage.y2kLogin(userName, password);
        botServicePage.createBotServiceWithStartRiskM();
        logger1 = extent.createTest("Create a boat service with Schedule");
        TakeScreenshot.captuerScreenshot(driver, "ScheduleBot");
    }

    @Test(priority = 35, description = "Create a boat service start")
    public void botServiceWithStartExecution() throws Exception {
        loginPage.y2kLogin(userName, password);
        botServicePage.createBotServiceWithStartExecution();
        logger1 = extent.createTest("Create a boat service with Start");
        TakeScreenshot.captuerScreenshot(driver, "StartBot");
    }

    @Test(priority = 36, description = "Create a boat RiskManagement")
    public void botServiceWithScheduleRiskManagement() throws Exception {
        loginPage.y2kLogin(userName, password);
        botServicePage.createBotServiceWithScheduleRiskManagement();
        logger1 = extent.createTest("Create a boat service risk ");
        TakeScreenshot.captuerScreenshot(driver, "StartRiskBot");
    }

    @Test(priority = 37, description = "Create a boat withScheduleExecution")
    public void botServiceWithScheduleExecution() throws Exception {
        loginPage.y2kLogin(userName, password);
        botServicePage.createBotServiceWithScheduleExecution();
        logger1 = extent.createTest("Create a boat withScheduleExecution ");
        TakeScreenshot.captuerScreenshot(driver, " withScheduleExecution");
    }
}
