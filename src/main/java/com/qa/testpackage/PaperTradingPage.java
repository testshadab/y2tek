package com.qa.testpackage;

import com.qa.baseclass.BaseClass;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import java.util.List;

@Log4j2
public class PaperTradingPage extends BaseClass {

    private String CreatedPaperTrading;
    private String PaperTradingName;
    private String AddSchedulePaperTradingName;
    private String ScheduledServiceName;

    public PaperTradingPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "span[class='profile-span']")
    List<WebElement> serviceTabList;
    @FindBy(css = "div[class*='create-paper-trading-text ']")
    public WebElement createPaperTradingCTA;
    @FindBy(name = "start")
    public WebElement startBotCTA;
    @FindBy(name = "schedule")
    public WebElement scheduleBotCTA;
    @FindBy(css = "div[class='loader']")
    public WebElement loadingPage;
    @FindBy(css = "input[placeholder='Initial Capital USD']")
    public WebElement intialCapitalTxtField;
    @FindBy(css = "button[class='btn-next']")
    public WebElement nextCTA;
    @FindBy(css = "input[class*='enter-papertrade-name']")
    public WebElement serviceNameTxtField;
    @FindBy(css = "div[id='action-alert']")
    public WebElement botServiceAlert;
    @FindBy(xpath = "(//div[contains(@class,'col-3 table-col')]/span)[1]")
    public WebElement createdPaperTradingName;
    @FindBy(xpath = "(//span[@class='suspend']/ancestor::div[@class='row table-row']/descendant::span)[1]")
    public WebElement scheduledBotName;
    @FindBy(xpath = "(//span[@class='calendar-image'])[1]")
    public WebElement startDate;
    @FindBy(xpath = "(//td[contains(@class,'today')])[1]")
    public WebElement todayStartDate;
    @FindBy(xpath = "(//button[contains(@class,'applyBtn')])[1]")
    public WebElement applyStartDateCTA;
    @FindBy(css = "input[placeholder='end date']")
    public WebElement endDate;
    @FindBy(xpath = "(//td[contains(@class,'today')])[2]")
    public WebElement todayEndDate;
    @FindBy(xpath = "(//button[contains(@class,'applyBtn')])[2]")
    public WebElement applyEndDateCTA;
    @FindBy(css = "button[class='btn save-changes delete']")
    public WebElement serviceDeleteCTA;
    @FindBy(xpath = "//div[@class='confirm-msg']")
    public WebElement paperTradingDeleteAlert;
    @FindBy(xpath = "//span[text()='Next']")
    WebElement nextBtn;
    @FindBy(xpath = "//div[@class='confirm-msg']")
    public WebElement serviceDeleteAlert;
    @FindBy(css = "button[aria-controls='collapseOne']")
    public WebElement riskManagement;
    @FindBy(xpath = "(//span[contains(text(),'Target Profit')]/following::input)[1]")
    public WebElement profitPercentageTxtField;
    @FindBy(xpath = "(//span[contains(text(),'Trailing Target')]/following::span)[1]")
    public WebElement trailingTargetRadio;
    @FindBy(css = "button[aria-controls='collapseTwo']")
    public WebElement executionParameters;
    @FindBy(xpath = "(//span[contains(text(),'Limit Order Price')]/following::input)[1]")
    public WebElement limitOrderPriceTxtField;
    @FindBy(xpath = "//span[@class='delete']//parent::span")
    public List<WebElement> botServiceDeleteCTA;
    @FindBy(css = "span[class='active']")
    public List<WebElement> botActivateRadioCTA;

    public void paperTrading() throws InterruptedException {
        waitForElementToBeInVisible(loadingPage);
        try {
            for (WebElement serviceTabList : serviceTabList) {
                String botServiceTab = serviceTabList.getText();
                if (botServiceTab.equalsIgnoreCase("Paper Trading")) {
                    waitForElementToBeInVisible(loadingPage);
                    serviceTabList.click();
                }
            }
            waitForWebElementToAppear(createPaperTradingCTA);
        } catch (StaleElementReferenceException exp) {
            log.info("Exception :" + exp);
        }
        waitForElementToBeInVisible(loadingPage);
        waitForWebElementToAppear(createPaperTradingCTA);
    }

    public void addPaperTradingWithStart() throws InterruptedException {
        waitForElementToBeInVisible(loadingPage);
        waitForWebElementToAppear(createPaperTradingCTA);
        createPaperTradingCTA.click();
        startBotCTA.click();
        waitForElementToBeInVisible(loadingPage);
        String CapitalAmount = String.valueOf(constants.capital);
        intialCapitalTxtField.click();
        intialCapitalTxtField.clear();
        intialCapitalTxtField.sendKeys(CapitalAmount);
        riskManagement.click();
        waitForWebElementToAppear(profitPercentageTxtField);
        profitPercentageTxtField.click();
        profitPercentageTxtField.clear();
        String TargetProfit = String.valueOf(constants.targetProfitPercentage);
        profitPercentageTxtField.sendKeys(TargetProfit);
        if (trailingTargetRadio.isEnabled() == true) {
            log.info("trailing Target Radio is active");
        } else {
            log.info("trailing Target Radio is inactive");
            trailingTargetRadio.click();
        }
        executionParameters.click();
        limitOrderPriceTxtField.click();
        limitOrderPriceTxtField.clear();
        String limitOrder = String.valueOf(constants.limitOrderPrice);
        limitOrderPriceTxtField.sendKeys(limitOrder);
        Thread.sleep(2000);
        PaperTradingName = serviceNameTxtField.getAttribute("value");
        nextBtn.click();
        waitForElementToBeInVisible(loadingPage);
        waitForWebElementToAppear(createdPaperTradingName);
        CreatedPaperTrading = createdPaperTradingName.getText();
        Assert.assertEquals(PaperTradingName, CreatedPaperTrading);
    }

    public void addTradingWithStartRiskManagement() throws InterruptedException {
        waitForElementToBeInVisible(loadingPage);
        waitForWebElementToAppear(createPaperTradingCTA);
        createPaperTradingCTA.click();
        startBotCTA.click();
        waitForElementToBeInVisible(loadingPage);
        String CapitalAmount = String.valueOf(constants.capital);
        intialCapitalTxtField.click();
        intialCapitalTxtField.clear();
        intialCapitalTxtField.sendKeys(CapitalAmount);
        riskManagement.click();
        waitForWebElementToAppear(profitPercentageTxtField);
        profitPercentageTxtField.click();
        profitPercentageTxtField.clear();
        String TargetProfit = String.valueOf(constants.targetProfitPercentage);
        profitPercentageTxtField.sendKeys(TargetProfit);
        if (trailingTargetRadio.isEnabled() == true) {
            log.info("trailing Target Radio is active");
        } else {
            log.info("trailing Target Radio is inactive");
            trailingTargetRadio.click();
        }
        PaperTradingName = serviceNameTxtField.getAttribute("value");
        waitForElementToBeClickable(nextBtn);
        nextBtn.click();
        waitForElementToBeInVisible(loadingPage);
        waitForWebElementToAppear(createdPaperTradingName);
        CreatedPaperTrading = createdPaperTradingName.getText();
        Assert.assertEquals(PaperTradingName, CreatedPaperTrading);
    }

    public void addTradingWithStartExecution() throws InterruptedException {
        waitForElementToBeInVisible(loadingPage);
        waitForWebElementToAppear(createPaperTradingCTA);
        createPaperTradingCTA.click();
        startBotCTA.click();
        waitForElementToBeInVisible(loadingPage);
        String CapitalAmount = String.valueOf(constants.capital);
        intialCapitalTxtField.click();
        intialCapitalTxtField.clear();
        intialCapitalTxtField.sendKeys(CapitalAmount);
        executionParameters.click();
        limitOrderPriceTxtField.click();
        limitOrderPriceTxtField.clear();
        String limitOrder = String.valueOf(constants.limitOrderPrice);
        limitOrderPriceTxtField.sendKeys(limitOrder);
        Thread.sleep(2000);
        PaperTradingName = serviceNameTxtField.getAttribute("value");
        nextBtn.click();
        waitForElementToBeInVisible(loadingPage);
        waitForWebElementToAppear(createdPaperTradingName);
        CreatedPaperTrading = createdPaperTradingName.getText();
        Assert.assertEquals(PaperTradingName, CreatedPaperTrading);
    }

    public void createPaperTradingWithStartRiskM() throws InterruptedException {
        paperTrading();
        int deleteCTASize = botServiceDeleteCTA.size();
        int activateRadioSize = botActivateRadioCTA.size();
        if (deleteCTASize > 0) {
            if (activateRadioSize > 0) {
                try {
                    for (int i = 0; activateRadioSize > i; i++) {
                        Thread.sleep(2000);
                        botActivateRadioCTA.get(0).click();
                        waitForElementToBeInVisible(loadingPage);
                        boolean Alert = botServiceAlert.isDisplayed();
                        Assert.assertTrue(Alert);
                    }
                } catch (StaleElementReferenceException ele) {
                    log.info("Radio Exception: " + ele);
                }
                try {
                    for (int j = 0; deleteCTASize > j; j++) {
                        Thread.sleep(2000);
                        botServiceDeleteCTA.get(0).click();
                        serviceDeleteCTA.click();
                        boolean Alert = serviceDeleteAlert.isDisplayed();
                        Assert.assertTrue(Alert);
                    }
                    addTradingWithStartRiskManagement();
                } catch (StaleElementReferenceException excep) {
                    log.info("Delete Exception: " + excep);
                }
            } else {
                try {
                    for (int k = 0; deleteCTASize > k; k++) {
                        Thread.sleep(2000);
                        botServiceDeleteCTA.get(0).click();
                        serviceDeleteCTA.click();
                        boolean Alert = serviceDeleteAlert.isDisplayed();
                        Assert.assertTrue(Alert);
                    }
                    addTradingWithStartRiskManagement();
                } catch (StaleElementReferenceException ele) {
                    log.info("Else Delete Exception: " + ele);
                }
            }
        } else {
            addTradingWithStartRiskManagement();
        }
    }

    public void createPaperTradingWithStartExecution() throws InterruptedException {
        paperTrading();
        int deleteCTASize = botServiceDeleteCTA.size();
        int activateRadioSize = botActivateRadioCTA.size();
        if (deleteCTASize > 0) {
            if (activateRadioSize > 0) {
                try {
                    for (int i = 0; activateRadioSize > i; i++) {
                        Thread.sleep(2000);
                        botActivateRadioCTA.get(0).click();
                        waitForElementToBeInVisible(loadingPage);
                        boolean Alert = botServiceAlert.isDisplayed();
                        Assert.assertTrue(Alert);
                    }
                } catch (StaleElementReferenceException ele) {
                    log.info("Radio Exception: " + ele);
                }
                try {
                    for (int j = 0; deleteCTASize > j; j++) {
                        Thread.sleep(2000);
                        botServiceDeleteCTA.get(0).click();
                        serviceDeleteCTA.click();
                        boolean Alert = serviceDeleteAlert.isDisplayed();
                        Assert.assertTrue(Alert);
                    }
                    addTradingWithStartExecution();
                } catch (StaleElementReferenceException excep) {
                    log.info("Delete Exception: " + excep);
                }
            } else {
                try {
                    for (int k = 0; deleteCTASize > k; k++) {
                        Thread.sleep(2000);
                        botServiceDeleteCTA.get(0).click();
                        serviceDeleteCTA.click();
                        boolean Alert = serviceDeleteAlert.isDisplayed();
                        Assert.assertTrue(Alert);
                    }
                    addTradingWithStartExecution();
                } catch (StaleElementReferenceException ele) {
                    log.info("Else Delete Exception: " + ele);
                }
            }
        } else {
            addTradingWithStartExecution();
        }
    }

    public void addPaperTradingWithSchedule() throws InterruptedException {
        waitForElementToBeInVisible(loadingPage);
        waitForWebElementToAppear(createPaperTradingCTA);
        createPaperTradingCTA.click();
        scheduleBotCTA.click();
        waitForElementToBeInVisible(loadingPage);
        Thread.sleep(1000);
        startDate.click();
        todayStartDate.click();
        applyStartDateCTA.click();
        endDate.click();
        todayEndDate.click();
        applyEndDateCTA.click();
        intialCapitalTxtField.click();
        String CapitalAmount = String.valueOf(constants.capital);
        intialCapitalTxtField.clear();
        intialCapitalTxtField.sendKeys(CapitalAmount);
        riskManagement.click();
        waitForWebElementToAppear(profitPercentageTxtField);
        profitPercentageTxtField.click();
        profitPercentageTxtField.clear();
        String TargetProfit = String.valueOf(constants.targetProfitPercentage);
        profitPercentageTxtField.sendKeys(TargetProfit);
        if (trailingTargetRadio.isEnabled() == true) {
            log.info("trailing Target Radio is active");
        } else {
            log.info("trailing Target Radio is inactive");
            trailingTargetRadio.click();
        }
        executionParameters.click();
        limitOrderPriceTxtField.click();
        limitOrderPriceTxtField.clear();
        String limitOrder = String.valueOf(constants.limitOrderPrice);
        limitOrderPriceTxtField.sendKeys(limitOrder);
        Thread.sleep(4000);
        AddSchedulePaperTradingName = serviceNameTxtField.getAttribute("value");
        nextCTA.click();
        waitForElementToBeInVisible(loadingPage);
        ScheduledServiceName = scheduledBotName.getText();
        Assert.assertEquals(AddSchedulePaperTradingName, ScheduledServiceName);
    }

    public void TradingWithScheduleRiskManagement() throws InterruptedException {
        waitForElementToBeInVisible(loadingPage);
        waitForWebElementToAppear(createPaperTradingCTA);
        createPaperTradingCTA.click();
        scheduleBotCTA.click();
        waitForElementToBeInVisible(loadingPage);
        riskManagement.click();
        waitForWebElementToAppear(profitPercentageTxtField);
        profitPercentageTxtField.click();
        profitPercentageTxtField.clear();
        String TargetProfit = String.valueOf(constants.targetProfitPercentage);
        profitPercentageTxtField.sendKeys(TargetProfit);
        if (trailingTargetRadio.isEnabled() == true) {
            log.info("trailing Target Radio is active");
        } else {
            log.info("trailing Target Radio is inactive");
            trailingTargetRadio.click();
        }
        AddSchedulePaperTradingName = serviceNameTxtField.getAttribute("value");
        nextCTA.click();
        waitForElementToBeInVisible(loadingPage);
        ScheduledServiceName = scheduledBotName.getText();
        Assert.assertEquals(AddSchedulePaperTradingName, ScheduledServiceName);
    }

    public void TradingWithScheduleExecution() throws InterruptedException {
        waitForElementToBeInVisible(loadingPage);
        waitForWebElementToAppear(createPaperTradingCTA);
        createPaperTradingCTA.click();
        scheduleBotCTA.click();
        waitForElementToBeInVisible(loadingPage);
        executionParameters.click();
        limitOrderPriceTxtField.click();
        limitOrderPriceTxtField.clear();
        String limitOrder = String.valueOf(constants.limitOrderPrice);
        limitOrderPriceTxtField.sendKeys(limitOrder);
        Thread.sleep(4000);
        AddSchedulePaperTradingName = serviceNameTxtField.getAttribute("value");
        nextCTA.click();
        waitForElementToBeInVisible(loadingPage);
        ScheduledServiceName = scheduledBotName.getText();
        Assert.assertEquals(AddSchedulePaperTradingName, ScheduledServiceName);
    }

    public void createPaperTradingWithStart() throws InterruptedException {
        paperTrading();
        int deleteCTASize = botServiceDeleteCTA.size();
        int activateRadioSize = botActivateRadioCTA.size();
        if (deleteCTASize > 0) {
            if (activateRadioSize > 0) {
                try {
                    for (int i = 0; activateRadioSize > i; i++) {
                        Thread.sleep(2000);
                        botActivateRadioCTA.get(0).click();
                        waitForElementToBeInVisible(loadingPage);
                        boolean Alert = botServiceAlert.isDisplayed();
                        Assert.assertTrue(Alert);
                    }
                } catch (StaleElementReferenceException ele) {
                    log.info("Radio Exception: " + ele);
                }
                try {
                    for (int j = 0; deleteCTASize > j; j++) {
                        Thread.sleep(2000);
                        botServiceDeleteCTA.get(0).click();
                        serviceDeleteCTA.click();
                        boolean Alert = serviceDeleteAlert.isDisplayed();
                        Assert.assertTrue(Alert);
                    }
                    addPaperTradingWithStart();
                } catch (StaleElementReferenceException excep) {
                    log.info("Delete Exception: " + excep);
                }
            } else {
                try {
                    for (int k = 0; deleteCTASize > k; k++) {
                        Thread.sleep(2000);
                        botServiceDeleteCTA.get(0).click();
                        serviceDeleteCTA.click();
                        boolean Alert = serviceDeleteAlert.isDisplayed();
                        Assert.assertTrue(Alert);
                    }
                    addPaperTradingWithStart();
                } catch (StaleElementReferenceException ele) {
                    log.info("Else Delete Exception: " + ele);
                }
            }
        } else {
            addPaperTradingWithStart();
        }
    }

    public void createPaperTradingWithSchedule() throws InterruptedException {
        paperTrading();
        int deleteCTASize = botServiceDeleteCTA.size();
        int activateRadioSize = botActivateRadioCTA.size();

        if (deleteCTASize > 0) {
            if (activateRadioSize > 0) {
                try {
                    for (int i = 0; activateRadioSize > i; i++) {
                        Thread.sleep(2000);
                        botActivateRadioCTA.get(0).click();
                        waitForElementToBeInVisible(loadingPage);
                        boolean Alert = botServiceAlert.isDisplayed();
                        Assert.assertTrue(Alert);
                    }
                } catch (StaleElementReferenceException ele) {
                    log.info("Radio Exception: " + ele);
                }
                try {
                    for (int j = 0; deleteCTASize > j; j++) {
                        Thread.sleep(2000);
                        botServiceDeleteCTA.get(0).click();
                        serviceDeleteCTA.click();
                        boolean Alert = serviceDeleteAlert.isDisplayed();
                        Assert.assertTrue(Alert);
                    }
                    addPaperTradingWithSchedule();
                } catch (StaleElementReferenceException excep) {
                    log.info("Delete Exception: " + excep);
                }
            } else {
                try {
                    for (int k = 0; deleteCTASize > k; k++) {
                        Thread.sleep(2000);
                        botServiceDeleteCTA.get(0).click();
                        serviceDeleteCTA.click();
                        boolean Alert = serviceDeleteAlert.isDisplayed();
                        Assert.assertTrue(Alert);
                    }
                    addPaperTradingWithSchedule();
                } catch (StaleElementReferenceException ele) {
                    log.info("Else Delete Exception: " + ele);
                }
            }
        } else {
            addPaperTradingWithSchedule();
        }
    }

    public void createPaperTradingWithScheduleRiskM() throws InterruptedException {
        paperTrading();
        int deleteCTASize = botServiceDeleteCTA.size();
        int activateRadioSize = botActivateRadioCTA.size();

        if (deleteCTASize > 0) {
            if (activateRadioSize > 0) {
                try {
                    for (int i = 0; activateRadioSize > i; i++) {
                        Thread.sleep(2000);
                        botActivateRadioCTA.get(0).click();
                        waitForElementToBeInVisible(loadingPage);
                        boolean Alert = botServiceAlert.isDisplayed();
                        Assert.assertTrue(Alert);
                    }
                } catch (StaleElementReferenceException ele) {
                    log.info("Radio Exception: " + ele);
                }
                try {
                    for (int j = 0; deleteCTASize > j; j++) {
                        Thread.sleep(2000);
                        botServiceDeleteCTA.get(0).click();
                        serviceDeleteCTA.click();
                        boolean Alert = serviceDeleteAlert.isDisplayed();
                        Assert.assertTrue(Alert);
                    }
                    TradingWithScheduleRiskManagement();
                } catch (StaleElementReferenceException excep) {
                    log.info("Delete Exception: " + excep);
                }
            } else {
                try {
                    for (int k = 0; deleteCTASize > k; k++) {
                        Thread.sleep(2000);
                        botServiceDeleteCTA.get(0).click();
                        serviceDeleteCTA.click();
                        boolean Alert = serviceDeleteAlert.isDisplayed();
                        Assert.assertTrue(Alert);
                    }
                    TradingWithScheduleRiskManagement();
                } catch (StaleElementReferenceException ele) {
                    log.info("Else Delete Exception: " + ele);
                }
            }
        } else {
            TradingWithScheduleRiskManagement();
        }
    }

    public void createPaperTradingWithScheduleExecution() throws InterruptedException {
        paperTrading();
        int deleteCTASize = botServiceDeleteCTA.size();
        int activateRadioSize = botActivateRadioCTA.size();

        if (deleteCTASize > 0) {
            if (activateRadioSize > 0) {
                try {
                    for (int i = 0; activateRadioSize > i; i++) {
                        Thread.sleep(2000);
                        botActivateRadioCTA.get(0).click();
                        waitForElementToBeInVisible(loadingPage);
                        boolean Alert = botServiceAlert.isDisplayed();
                        Assert.assertTrue(Alert);
                    }
                } catch (StaleElementReferenceException ele) {
                    log.info("Radio Exception: " + ele);
                }
                try {
                    for (int j = 0; deleteCTASize > j; j++) {
                        Thread.sleep(2000);
                        botServiceDeleteCTA.get(0).click();
                        serviceDeleteCTA.click();
                        boolean Alert = serviceDeleteAlert.isDisplayed();
                        Assert.assertTrue(Alert);
                    }
                    TradingWithScheduleExecution();
                } catch (StaleElementReferenceException excep) {
                    log.info("Delete Exception: " + excep);
                }
            } else {
                try {
                    for (int k = 0; deleteCTASize > k; k++) {
                        Thread.sleep(2000);
                        botServiceDeleteCTA.get(0).click();
                        serviceDeleteCTA.click();
                        boolean Alert = serviceDeleteAlert.isDisplayed();
                        Assert.assertTrue(Alert);
                    }
                    TradingWithScheduleExecution();
                } catch (StaleElementReferenceException ele) {
                    log.info("Else Delete Exception: " + ele);
                }
            }
        } else {
            TradingWithScheduleExecution();
        }
    }

    public void deletePaperTrading() throws InterruptedException {
        paperTrading();
        int deleteCTASize = botServiceDeleteCTA.size();
        int activateRadioSize = botActivateRadioCTA.size();

        if (deleteCTASize > 0) {
            if (activateRadioSize > 0) {
                try {
                    for (int i = 0; activateRadioSize > i; i++) {
                        Thread.sleep(2000);
                        botActivateRadioCTA.get(0).click();
                        waitForElementToBeInVisible(loadingPage);
                        boolean Alert = botServiceAlert.isDisplayed();
                        Assert.assertTrue(Alert);
                    }
                } catch (StaleElementReferenceException ele) {
                    log.info("Radio Exception: " + ele);
                }
                try {
                    for (int j = 0; deleteCTASize > j; j++) {
                        Thread.sleep(2000);
                        botServiceDeleteCTA.get(0).click();
                        serviceDeleteCTA.click();
                        boolean Alert = serviceDeleteAlert.isDisplayed();
                        Assert.assertTrue(Alert);
                    }
                } catch (StaleElementReferenceException excep) {
                    log.info("Delete Exception: " + excep);
                }
            } else {
                try {
                    for (int j = 0; deleteCTASize > j; j++) {
                        Thread.sleep(2000);
                        botServiceDeleteCTA.get(0).click();
                        serviceDeleteCTA.click();
                        boolean Alert = serviceDeleteAlert.isDisplayed();
                        Assert.assertTrue(Alert);
                    }
                } catch (StaleElementReferenceException excep) {
                    log.info("Delete Exception: " + excep);
                }
            }
        } else {
            addPaperTradingWithStart();
            botActivateRadioCTA.get(0).click();
            waitForElementToBeInVisible(loadingPage);
            botServiceDeleteCTA.get(0).click();
            serviceDeleteCTA.click();
            boolean Alert = serviceDeleteAlert.isDisplayed();
            Assert.assertTrue(Alert);
        }
    }

}
