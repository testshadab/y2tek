package com.qa.testcase;

import com.qa.baseclass.BaseClass;
import com.qa.testpackage.DashBoardPage;
import com.qa.testpackage.LoginPage;
import com.qa.testpackage.WalletPage;
import com.qa.lib.TakeScreenshot;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.net.MalformedURLException;

public class WalletPageTest extends BaseClass {
    LoginPage loginPage;
    DashBoardPage dashBoardPage;
    WalletPage walletPage;

    public WalletPageTest() {
        super();
    }

    @BeforeMethod
    public void setup() throws MalformedURLException {
        initialization();
        loginPage = new LoginPage();
        dashBoardPage = new DashBoardPage();
        walletPage = new WalletPage();

    }

    @Test(priority = 13, description = "validate y2tek login Page")
    public void navigateToWalletPageTest() throws Exception {
        loginPage.y2kLogin(userName, password);
        walletPage.wallet();
        logger1 = extent.createTest("Validation user wallet page");
        TakeScreenshot.captuerScreenshot(driver, "wallet");
    }

    @Test(priority = 14, description = "validate y2tek user create a wallet")
    public void depositWithPlusIconTest() throws Exception {
        loginPage.y2kLogin(userName, password);
        walletPage.wallet();
        walletPage.depositWithPlusIcon();
        logger1 = extent.createTest("Validation user create a wallet");
        walletPage.depositAlertMsg();
        logger1 = extent.createTest("Validation user alert message for create wallet page");
        TakeScreenshot.captuerScreenshot(driver, "PlusIcon");
    }

    @Test(priority = 15, description = "validate deposit a wallet")
    public void depositAmountTest() throws Exception {
        loginPage.y2kLogin(userName, password);
        walletPage.wallet();
        walletPage.depositAmount();
        logger1 = extent.createTest("Validation deposit amount wallet");
        TakeScreenshot.captuerScreenshot(driver, "PlusIcon");
        walletPage.depositAlertMsg();
        TakeScreenshot.captuerScreenshot(driver, "deposit");
    }

    @Test(priority = 16, description = "validate withdrawAmount a wallet")
    public void withdrawAmountTest() throws Exception {
        loginPage.y2kLogin(userName, password);
        walletPage.wallet();
        walletPage.withdrawAmount();
        logger1 = extent.createTest("Validation withdrawAmount amount wallet");
        walletPage.depositAlertMsg();
        TakeScreenshot.captuerScreenshot(driver, "Withdraw");
    }

    @Test(priority = 17, description = "validate moreInfo a wallet")
    public void moreInfoTest() throws Exception {
        loginPage.y2kLogin(userName, password);
        walletPage.wallet();
        walletPage.moreInfo();
        logger1 = extent.createTest("Validation moreInfo");
        TakeScreenshot.captuerScreenshot(driver, "MoreInfo");

    }

}
