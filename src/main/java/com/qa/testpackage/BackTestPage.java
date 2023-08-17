package com.qa.testpackage;

import com.qa.baseclass.BaseClass;
import com.qa.constants.Constants;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import java.util.List;

@Log4j2
public class BackTestPage extends BaseClass {
    private  String BacktestingName;
    private  String ExpCreatedTestName;
    public Constants constants=new Constants();
    @FindBy(xpath = "//span[@class='delete']")
    List<WebElement> backTestDeleteCTA;
    @FindBy(css = "span[class='profile-span']")
    List<WebElement> serviceTablist;
    @FindBy(css = "div[class='back-testing-start-button'] div")
    public WebElement createBacktestCTA;
    @FindBy(xpath = "(//span[@class='calendar-image'])[1]")
    public WebElement startDate;
    @FindBy(xpath = "(//td[contains(@class,'today active')])[1]")
    public WebElement todayStartDate;
    @FindBy(xpath = "(//button[contains(@class,'applyBtn')])[1]")
    public WebElement applyStartDateCTA;
    @FindBy(css = "input[placeholder='end date']")
    public WebElement endDate;
    @FindBy(xpath = "(//td[contains(@class,'today active')])[2]")
    public WebElement todayEndDate;
    @FindBy(xpath = "(//button[contains(@class,'applyBtn')])[2]")
    public WebElement applyEndDateCTA;
    @FindBy(css = "button[class='btn-next']")
    public WebElement nextCTA;
    @FindBy(xpath = "(//button[@class='btn-execute'])[1]")
    public WebElement executeCTA;
    @FindBy(xpath = "(//button[@class='btn-cancel']/following::button)[1]")
    public WebElement saveCTA;
    @FindBy(xpath = "(//div[contains(@class,'table-col')]/span)[1]")
    public WebElement backTestCreatedName;
    @FindBy(css = "div[class='loader']")
    public WebElement loadingPage;
    @FindBy(xpath = "(//button[@class='btn save-changes delete'])[2]")
    public WebElement serviceDeleteCTA;
    @FindBy(xpath = "//div[@class='confirm-msg']")
    public WebElement serviceDeleteAlert;
    @FindBy(css = "input[class*='backtesting-name']")
    public WebElement serviceNameTxtField;
    @FindBy(css = "button[aria-controls='collapseOne']")
    public WebElement riskManagement;
    @FindBy(xpath = "(//span[contains(text(),'Target Profit')]/following::input)[1]")
    public WebElement profitPercentageTxtField;
    @FindBy(xpath = "(//span[contains(text(),'Trailing Target')]/following::span)[1]")
    public WebElement trailingTargetRadio;
    @FindBy(css = "button[aria-controls='collapseTwo']")
    public WebElement executionParameters;
    @FindBy(xpath = "(//span[contains(text(),'Transaction Cost in Basis Points')]/following::input)[1]")
    public WebElement basisPointsTxtField;

    public BackTestPage() {
        PageFactory.initElements(driver, this);
    }


    public void backTestDelete() throws InterruptedException {
        backTesting();
        int deleteCTASize = backTestDeleteCTA.size();
        log.info("Size for the delete CTA: " + deleteCTASize);
        if (deleteCTASize > 0) {
            try {
                for (int j = 0; deleteCTASize > j; j++) {
                    Thread.sleep(2000);
                    backTestDeleteCTA.get(0).click();
                    waitForWebElementToAppear(serviceDeleteCTA);
                    serviceDeleteCTA.click();
                    boolean Alert = serviceDeleteAlert.isDisplayed();
                    Assert.assertTrue(Alert);
                }
            } catch (StaleElementReferenceException excep) {
                log.info("Delete Exception: " + excep);
            }
        } else {
            addBackTest();
            try {
                for (int j = 0; deleteCTASize > j; j++) {
                    Thread.sleep(2000);
                    backTestDeleteCTA.get(0).click();
                    waitForWebElementToAppear(serviceDeleteCTA);
                    serviceDeleteCTA.click();
                    boolean Alert = serviceDeleteAlert.isDisplayed();
                    Assert.assertTrue(Alert);
                }
            } catch (StaleElementReferenceException excep) {
                log.info("Delete Exception: " + excep);
            }
        }
    }

    public void backTesting() {
        waitForElementToBeInVisible(loadingPage);
        try {
            for (WebElement serviceTabList : serviceTablist) {
                String backTestingTab = serviceTabList.getText();
                if (backTestingTab.equalsIgnoreCase("Backtesting")) {
                    waitForElementToBeInVisible(loadingPage);
                    serviceTabList.click();
                }
            }
        } catch (StaleElementReferenceException ele) {
            log.info("BackTesting Exception: "+ele);
        }
        waitForElementToBeInVisible(loadingPage);
        waitForWebElementToAppear(createBacktestCTA);
    }

    public void addBackTest() throws InterruptedException {
        waitForElementToBeInVisible(loadingPage);
        createBacktestCTA.click();
        waitForWebElementToAppear(startDate);
        Thread.sleep(2000);
        startDate.click();
        todayStartDate.click();
        applyStartDateCTA.click();
        endDate.click();
        todayEndDate.click();
        applyEndDateCTA.click();
        BacktestingName = serviceNameTxtField.getAttribute("value");
        log.info("BackTest Name: " + BacktestingName);
        nextCTA.click();
        waitForWebElementToAppear(executeCTA);
        waitForElementToBeInVisible(loadingPage);
        Thread.sleep(8000);
        saveCTA.click();
        waitForElementToBeInVisible(loadingPage);
        waitForWebElementToAppear(backTestCreatedName);
        ExpCreatedTestName = backTestCreatedName.getText();
        log.info("Excep created:" + ExpCreatedTestName);
        Assert.assertEquals(BacktestingName, ExpCreatedTestName);
    }

    public void addBackTestWithRiskM() throws InterruptedException {
        waitForElementToBeInVisible(loadingPage);
        createBacktestCTA.click();
        waitForWebElementToAppear(startDate);
        waitForElementToBeInVisible(loadingPage);
        startDate.click();
        todayStartDate.click();
        applyStartDateCTA.click();
        endDate.click();
        todayEndDate.click();
        applyEndDateCTA.click();
        riskManagement.click();
        waitForWebElementToAppear(profitPercentageTxtField);
        profitPercentageTxtField.click();
        profitPercentageTxtField.clear();
        String TargetProfit = String.valueOf(constants.targetProfitPercentage);
        profitPercentageTxtField.sendKeys(TargetProfit);
        if (trailingTargetRadio.isEnabled()==true) {
            log.info("trailing Target Radio is active");
        } else {
            log.info("trailing Target Radio is inactive");
            trailingTargetRadio.click();
        }
        BacktestingName = serviceNameTxtField.getAttribute("value");
        log.info("BackTest Name: " + BacktestingName);
        waitForElementToBeClickable(nextCTA);
        nextCTA.click();
        waitForWebElementToAppear(executeCTA);
        waitForElementToBeInVisible(loadingPage);
        Thread.sleep(8000);
        saveCTA.click();
        waitForElementToBeInVisible(loadingPage);
        waitForWebElementToAppear(backTestCreatedName);
        ExpCreatedTestName = backTestCreatedName.getText();
        log.info("Excep created:" + ExpCreatedTestName);
        Assert.assertEquals(BacktestingName, ExpCreatedTestName);
    }

    public void addBackTestWithExecution() throws InterruptedException {
        waitForElementToBeInVisible(loadingPage);
        createBacktestCTA.click();
        waitForWebElementToAppear(startDate);
        waitForElementToBeInVisible(loadingPage);
        startDate.click();
        todayStartDate.click();
        applyStartDateCTA.click();
        endDate.click();
        todayEndDate.click();
        applyEndDateCTA.click();
        executionParameters.click();
        basisPointsTxtField.click();
        basisPointsTxtField.clear();
        String points = String.valueOf(constants.basisPoints);
        basisPointsTxtField.sendKeys(points);
        Thread.sleep(2000);
        BacktestingName = serviceNameTxtField.getAttribute("value");
        log.info("BackTest Name: " + BacktestingName);
        waitForElementToBeClickable(nextCTA);
        nextCTA.click();
        waitForWebElementToAppear(executeCTA);
        waitForElementToBeInVisible(loadingPage);
        Thread.sleep(8000);
        saveCTA.click();
        waitForElementToBeInVisible(loadingPage);
        waitForWebElementToAppear(backTestCreatedName);
        ExpCreatedTestName = backTestCreatedName.getText();
        log.info("Excep created:" + ExpCreatedTestName);
        Assert.assertEquals(BacktestingName, ExpCreatedTestName);
    }

    public void addBackTestWithoutData() throws InterruptedException {
        waitForElementToBeInVisible(loadingPage);
        createBacktestCTA.click();
        waitForElementToBeInVisible(loadingPage);
        waitForWebElementToAppear(startDate);
        BacktestingName = serviceNameTxtField.getAttribute("value");
        log.info("BackTest Name: " + BacktestingName);
        waitForElementToBeClickable(nextCTA);
        nextCTA.click();
        waitForWebElementToAppear(executeCTA);
        waitForElementToBeInVisible(loadingPage);
        Thread.sleep(5000);
        saveCTA.click();
        waitForElementToBeInVisible(loadingPage);
        waitForWebElementToAppear(backTestCreatedName);
        ExpCreatedTestName = backTestCreatedName.getText();
        log.info("Excep created:" + ExpCreatedTestName);
        Assert.assertEquals(BacktestingName, ExpCreatedTestName);
    }

    public void backTestCreateWithoutData() throws InterruptedException {
        backTesting();
        int deleteCTASize = backTestDeleteCTA.size();
        log.info("Size for the delete CTA: " + deleteCTASize);
        if (deleteCTASize > 0) {
            try {
                for (int j = 0; deleteCTASize > j; j++) {
                    Thread.sleep(2000);
                    backTestDeleteCTA.get(0).click();
                    serviceDeleteCTA.click();
                    boolean Alert = serviceDeleteAlert.isDisplayed();
                    Assert.assertTrue(Alert);
                }
            } catch (StaleElementReferenceException excep) {
                log.info("Delete Exception: " + excep);
            }
            addBackTestWithoutData();
        } else {
            addBackTestWithoutData();
        }
    }

    public void backTestCreate() throws InterruptedException {
        backTesting();
        int deleteCTASize = backTestDeleteCTA.size();
        log.info("Size for the delete CTA: " + deleteCTASize);
        if (deleteCTASize > 0) {
            try {
                for (int j = 0; deleteCTASize > j; j++) {
                    Thread.sleep(2000);
                    backTestDeleteCTA.get(0).click();
                    serviceDeleteCTA.click();
                    boolean Alert = serviceDeleteAlert.isDisplayed();
                    Assert.assertTrue(Alert);
                }
            } catch (StaleElementReferenceException excep) {
                log.info("Delete Exception: " + excep);
            }
            addBackTest();
        } else {
            addBackTest();
        }
    }

    public void backTestCreateWithRiskManagement() throws InterruptedException {
        backTesting();
        int deleteCTASize = backTestDeleteCTA.size();
        log.info("Size for the delete CTA: " + deleteCTASize);
        if (deleteCTASize > 0) {
            try {
                for (int j = 0; deleteCTASize > j; j++) {
                    waitForElementToBeInVisible(loadingPage);
                    backTestDeleteCTA.get(0).click();
                    serviceDeleteCTA.click();
                    boolean Alert = serviceDeleteAlert.isDisplayed();
                    Assert.assertTrue(Alert);
                }
            } catch (StaleElementReferenceException excep) {
                log.info("Delete Exception: " + excep);
            }
            addBackTestWithRiskM();
        } else {
            addBackTestWithRiskM();
        }
    }

    public void backTestCreateWithExecution() throws InterruptedException {
        backTesting();
        int deleteCTASize = backTestDeleteCTA.size();
        log.info("Size for the delete CTA: " + deleteCTASize);
        if (deleteCTASize > 0) {
            try {
                for (int j = 0; deleteCTASize > j; j++) {
                    waitForElementToBeInVisible(loadingPage);
                    backTestDeleteCTA.get(0).click();
                    serviceDeleteCTA.click();
                    boolean Alert = serviceDeleteAlert.isDisplayed();
                    Assert.assertTrue(Alert);
                }
            } catch (StaleElementReferenceException excep) {
                log.info("Delete Exception: " + excep);
            }
            addBackTestWithExecution();
        } else {
            addBackTestWithExecution();
        }

    }
}




