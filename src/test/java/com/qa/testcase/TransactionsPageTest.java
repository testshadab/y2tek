package com.qa.testcase;

import com.qa.baseclass.BaseClass;
import com.qa.testpackage.*;
import com.qa.lib.TakeScreenshot;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.net.MalformedURLException;

public class TransactionsPageTest extends BaseClass {
    LoginPage loginPage;
    DashBoardPage dashBoardPage;
    WalletPage walletPage;
    PaperTradingPage paperTradingPage;

    TransactionsPage transactionsPage;
    public TransactionsPageTest() {
        super();
    }
    @BeforeMethod
    public void setup() throws MalformedURLException {
        initialization();
        loginPage = new LoginPage();
        dashBoardPage = new DashBoardPage();
        walletPage = new WalletPage();
        paperTradingPage=new PaperTradingPage();
        transactionsPage=new TransactionsPage();

    }
    @Test(priority = 26,description = "Transactions Page")
    public void navigateToBotServicePageTest() throws Exception {
        loginPage.y2kLogin(userName,password);
        transactionsPage.transactionsPage();
        logger1 = extent.createTest("Transactions page");
        TakeScreenshot.captuerScreenshot(driver, "Transactions");
    }

    @Test(priority = 27,description = "Transactions table,date and dropdown list")
    public void downloadStatement() throws Exception {
        loginPage.y2kLogin(userName,password);
        transactionsPage.transactionsPage();
        transactionsPage.datePicker();
        logger1 = extent.createTest("Transactions Date Picker");
        transactionsPage.downloadStatement();
        logger1 = extent.createTest("Transactions Download  Statement");
        transactionsPage.transactionsTable();
        logger1 = extent.createTest("Transactions Table");
        TakeScreenshot.captuerScreenshot(driver, "Transactions");
    }

    @Test(priority = 28, description = "Transactions Trade book")
    public void tradeBook() throws Exception {
        loginPage.y2kLogin(userName,password);
        transactionsPage.transactionsPage();
        transactionsPage.tradeBook();
        logger1 = extent.createTest("Transactions Trade book");
        transactionsPage.tradeBookTable();
        logger1 = extent.createTest("Transactions Trade book table");
        TakeScreenshot.captuerScreenshot(driver, "TransactionsTrade");
    }

    @Test(priority = 29, description = "Transactions book")
    public void profitAndLoss() throws Exception {
        loginPage.y2kLogin(userName,password);
        transactionsPage.transactionsPage();
        transactionsPage.profitAndLoss();
        logger1 = extent.createTest("Transactions Profit book");
        transactionsPage.profitAndLossCardTable();
        logger1 = extent.createTest("Transactions profit and loss book");
        transactionsPage.profitAndLossTable();
        logger1 = extent.createTest("Transactions profit and loss Table");
        TakeScreenshot.captuerScreenshot(driver, "TransactionProfitAndLoss");
    }


}
