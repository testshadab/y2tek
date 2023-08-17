package com.qa.testcase;

import com.qa.baseclass.BaseClass;
import com.qa.testpackage.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.net.MalformedURLException;

public class BackTestPageTest extends BaseClass {
    LoginPage loginPage;
    BackTestPage backTestPage;

    public BackTestPageTest() {
        super();
    }

    @BeforeMethod
    public void setup() throws MalformedURLException {
        initialization();
        loginPage = new LoginPage();
        backTestPage=new BackTestPage();
    }

    @Test(priority = 38,description = "backTest")
    public void backTest() throws InterruptedException {
        loginPage.y2kLogin(userName,password);
        backTestPage.backTesting();
        logger1 = extent.createTest("Validation backtest service");
    }

    @Test(priority = 39,description = "Backtest create")
    public void backTestCreate() throws InterruptedException{
        loginPage.y2kLogin(userName,password);
        backTestPage.backTestCreate();
        logger1 = extent.createTest("Validation back test create service");
    }

    @Test(priority = 40,description = "Backtest without data")
    public void backTestWithoutData() throws InterruptedException {
        loginPage.y2kLogin(userName,password);
        backTestPage.backTestCreateWithoutData();
        logger1 = extent.createTest("Validation back test create without data service");
    }

    @Test(priority = 41,description = "Backtest Delete")
    public void backTestDelete() throws InterruptedException{
        loginPage.y2kLogin(userName,password);
        backTestPage.backTestDelete();
        logger1 = extent.createTest("Validation back test delete service");
    }

    @Test(priority = 42,description = "Backtest with Risk Management")
    public void backTestCreateWithRiskManagement() throws InterruptedException {
        loginPage.y2kLogin(userName,password);
        backTestPage.backTestCreateWithRiskManagement();
        logger1 = extent.createTest("Validation back test create with risk management service");
    }

    @Test(priority = 43, description = "Backtest with execution")
    public void backTestCreateWithExecution() throws InterruptedException{
        loginPage.y2kLogin(userName,password);
        backTestPage.backTestCreateWithExecution();
        logger1 = extent.createTest("Validation back test create with execution service");
    }
}
