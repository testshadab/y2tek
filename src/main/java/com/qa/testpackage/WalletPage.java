package com.qa.testpackage;

import com.qa.baseclass.BaseClass;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class WalletPage extends BaseClass {
    @FindBy(css = "span[class='profile-span']")
    List<WebElement> walletTestOption;
    @FindBy(css = "span[class='card-bt-add']")
    public WebElement depositAddIcon;
    @FindBy(css = "input[placeholder='Enter Amount']")
    public WebElement enterAmount;
    @FindBy(css = "button[class='btn-execute']")
    public WebElement addMoneyCTA;
    @FindBy(css = "div[id='action-alert']")
    public WebElement depositAlert;
    @FindBy(css = "button[class*='btn-deposite']")
    public WebElement depositCTA;
    @FindBy(xpath = "//span[text()='See More']/parent::button")
    public WebElement seeMore;
    @FindBy(css = "button[class*='btn-withdraw']")
    public WebElement withdrawCTA;
    @FindBy(css = "button[class='btn-execute']")
    public WebElement withdrawAmountCTA;
    @FindBy(css = "span[class='withdraw-text']")
    public WebElement withdrawalAmountTxt;
    @FindBy(css = "button[class='btn-execute']")
    public WebElement sendRequstCTA;
    @FindBy(xpath = "//span[@class='floating-label']/following::span")
    public List<WebElement> moreInfoMenus;
    @FindBy(css = "button[class='close']")
    public WebElement closeCTA;
    @FindBy(css = "div[class='loader']")
    public WebElement loadingPage;

    public WalletPage() {
        PageFactory.initElements(driver, this);
    }

    public void wallet() throws InterruptedException {
        waitForElementToBeInVisible(loadingPage);
        try {
            for (WebElement serviceTabList : walletTestOption) {
                String walletTab = serviceTabList.getText();
                if (walletTab.equalsIgnoreCase("Wallet")) {
                    waitForElementToBeInVisible(loadingPage);
                    serviceTabList.click();
                }
            }
            waitForElementToBeInVisible(loadingPage);
        } catch (StaleElementReferenceException ele) {
            System.out.println("Wallet Exp: " + ele);
        }
        waitForWebElementToAppear(depositAddIcon);
    }

    public void depositWithPlusIcon() throws InterruptedException {
        waitForElementToBeInVisible(loadingPage);
        depositAddIcon.click();
        enterAmount.click();
        String amount = String.valueOf(constants.depositAmount);
        enterAmount.sendKeys(amount);
        addMoneyCTA.click();
    }

    public void depositAlertMsg() throws InterruptedException {
        alertMsg();
        Assert.assertTrue(alertMsg());
    }

    public boolean alertMsg() {
        return depositAlert.isDisplayed();
    }

    public void depositAmount() throws InterruptedException {
        waitForElementToBeInVisible(loadingPage);
        depositCTA.click();
        enterAmount.click();
        String amount = String.valueOf(constants.depositAmount);
        enterAmount.sendKeys(amount);
        addMoneyCTA.click();
    }

    public void withdrawAmount() throws InterruptedException {
        waitForElementToBeInVisible(loadingPage);
        withdrawCTA.click();
        enterAmount.click();
        String amount = String.valueOf(constants.depositAmount);
        enterAmount.sendKeys(amount);
        withdrawAmountCTA.click();
        withdrawalAmountTxt.isDisplayed();
        sendRequstCTA.click();
    }

    public void moreInfo() throws InterruptedException {
        waitForElementToBeInVisible(loadingPage);
        seeMore.click();
        for (WebElement moreInfolist : moreInfoMenus) {
            String labelTxt = moreInfolist.getText();
            if (labelTxt.equalsIgnoreCase("Current Balance")) {
                Assert.assertEquals(labelTxt, "Current Balance");
            } else if (labelTxt.equalsIgnoreCase("Amount On Hold")) {
                Assert.assertEquals(labelTxt, "Amount On Hold");
            } else if (labelTxt.equalsIgnoreCase("Available for Trade")) {
                Assert.assertEquals(labelTxt, "Available for Trade");
                break;
            }
        }
        waitForElementToBeVisible(closeCTA);
        closeCTA.click();
        seeMore.isDisplayed();
    }

}
