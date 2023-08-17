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
public class BotServicePage extends BaseClass {

    private boolean alertMsg;
    private String CreatedBotService;
    private String BotServiceName;
    private String AddScheduleBotServiceName;
    private String ScheduledServiceName;

    public BotServicePage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "span[class='profile-span']")
    List<WebElement> serviceTabList;
    @FindBy(css = "div[class*='create-bot-service-text']")
    public WebElement createBotServiceCTA;
    @FindBy(name = "start")
    public WebElement startBotCTA;
    @FindBy(name = "schedule")
    public WebElement scheduleBotCTA;
    @FindBy(css = "div[class='loader']")
    public WebElement loadingPage;
    @FindBy(css = "input[placeholder='Capital to Deploy']")
    public WebElement capitalTxtField;
    @FindBy(css = "button[class='btn-next']")
    public WebElement nextCTA;
    @FindBy(css = "input[class*='bot-create-name']")
    public WebElement serviceNameTxtField;
    @FindBy(xpath = "//div[contains(@class,'col-1')]/span[@data-toggle='modal']")
    public WebElement activeService;
    @FindBy(css = "button[class*='btn-block']")
    public WebElement inactivate;
    @FindBy(css = "div[id='action-alert']")
    public WebElement botServiceAlert;
    @FindBy(xpath = "//span[@class='delete']//parent::span")
    public List<WebElement> botServiceDeleteCTA;
    @FindBy(css = "span[class='active']")
    public List<WebElement> botActivateRadioCTA;
    @FindBy(xpath = "(//div[contains(@class,'col-2 table-col')]/span)[1]")
    public WebElement createdBotName;
    @FindBy(xpath = "(//span[@class='suspend']/ancestor::div[@class='row table-row']/descendant::span)[1]")
    public WebElement scheduledBotName;
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
    @FindBy(css = "button[class='btn save-changes delete']")
    public WebElement serviceDeleteCTA;
    @FindBy(xpath = "//div[@class='confirm-msg']")
    public WebElement serviceDeleteAlert;
    @FindBy(xpath = "//span[text()='Next']")
    public WebElement nextBtn;
    @FindBy(css = "button[class*='btn-success']")
    public WebElement publishCTA;
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

    public void botService() throws InterruptedException {
        waitForElementToBeInVisible(loadingPage);
        for (WebElement serviceTabList : serviceTabList) {
            String botServiceTab = serviceTabList.getText();
            if (botServiceTab.equalsIgnoreCase("Bot Service")) {
                waitForElementToBeInVisible(loadingPage);
                serviceTabList.click();
            }
        }
        waitForElementToBeInVisible(loadingPage);
        waitForWebElementToAppear(createBotServiceCTA);
    }

    public void addBotServiceWithStart() throws InterruptedException {
        waitForElementToBeInVisible(loadingPage);
        waitForWebElementToAppear(createBotServiceCTA);
        createBotServiceCTA.click();
        startBotCTA.click();
        waitForElementToBeInVisible(loadingPage);
        String CapitalAmount = String.valueOf(constants.capital);
        capitalTxtField.click();
        capitalTxtField.sendKeys(CapitalAmount);
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
        BotServiceName = serviceNameTxtField.getAttribute("value");
        log.info("Bot Service Name: " + BotServiceName);
        nextBtn.click();
        publishCTA.click();
        waitForElementToBeInVisible(loadingPage);
        waitForWebElementToAppear(createdBotName);
        CreatedBotService = createdBotName.getText();
        log.info("Created Bot Service: " + CreatedBotService);
        Assert.assertEquals(BotServiceName, CreatedBotService);
    }

    public void addBotServiceWithStartRiskManagement() throws InterruptedException {
        waitForElementToBeInVisible(loadingPage);
        waitForWebElementToAppear(createBotServiceCTA);
        createBotServiceCTA.click();
        startBotCTA.click();
        waitForElementToBeInVisible(loadingPage);
        String CapitalAmount = String.valueOf(constants.capital);
        capitalTxtField.click();
        capitalTxtField.sendKeys(CapitalAmount);
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
        BotServiceName = serviceNameTxtField.getAttribute("value");
        log.info("Bot Service Name: " + BotServiceName);
        nextBtn.click();
        publishCTA.click();
        waitForElementToBeInVisible(loadingPage);
        waitForWebElementToAppear(createdBotName);
        CreatedBotService = createdBotName.getText();
        log.info("Created Bot Service: " + CreatedBotService);
        Assert.assertEquals(BotServiceName, CreatedBotService);
    }

    public void addBotServiceWithStartExecution() throws InterruptedException {
        waitForElementToBeInVisible(loadingPage);
        waitForWebElementToAppear(createBotServiceCTA);
        createBotServiceCTA.click();
        startBotCTA.click();
        waitForElementToBeInVisible(loadingPage);
        String CapitalAmount = String.valueOf(constants.capital);
        capitalTxtField.click();
        capitalTxtField.sendKeys(CapitalAmount);
        executionParameters.click();
        limitOrderPriceTxtField.click();
        limitOrderPriceTxtField.clear();
        String limitOrder = String.valueOf(constants.limitOrderPrice);
        limitOrderPriceTxtField.sendKeys(limitOrder);
        Thread.sleep(2000);
        BotServiceName = serviceNameTxtField.getAttribute("value");
        log.info("Bot Service Name: " + BotServiceName);
        nextBtn.click();
        publishCTA.click();
        waitForElementToBeInVisible(loadingPage);
        waitForWebElementToAppear(createdBotName);
        CreatedBotService = createdBotName.getText();
        log.info("Created Bot Service: " + CreatedBotService);
        Assert.assertEquals(BotServiceName, CreatedBotService);
    }

    public void addBotServiceWithSchedule() throws InterruptedException {
        waitForElementToBeInVisible(loadingPage);
        waitForWebElementToAppear(createBotServiceCTA);
        createBotServiceCTA.click();
        scheduleBotCTA.click();
        waitForElementToBeInVisible(loadingPage);
        Thread.sleep(1000);
        startDate.click();
        todayStartDate.click();
        applyStartDateCTA.click();
        endDate.click();
        todayEndDate.click();
        applyEndDateCTA.click();
        capitalTxtField.click();
        String CapitalAmount = String.valueOf(constants.capital);
        capitalTxtField.clear();
        capitalTxtField.sendKeys(CapitalAmount);
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
        AddScheduleBotServiceName = serviceNameTxtField.getAttribute("value");
        log.info("Scheduled Service Name: " + AddScheduleBotServiceName);
        nextCTA.click();
        publishCTA.click();
        waitForElementToBeInVisible(loadingPage);
        ScheduledServiceName = scheduledBotName.getText();
        log.info("Created Bot Service: " + ScheduledServiceName);
        Assert.assertEquals(AddScheduleBotServiceName, ScheduledServiceName);
    }

    public void addBotServiceWithScheduleExecution() throws InterruptedException {
        waitForElementToBeInVisible(loadingPage);
        waitForWebElementToAppear(createBotServiceCTA);
        createBotServiceCTA.click();
        scheduleBotCTA.click();
        waitForElementToBeInVisible(loadingPage);
        Thread.sleep(1000);
        startDate.click();
        todayStartDate.click();
        applyStartDateCTA.click();
        endDate.click();
        todayEndDate.click();
        applyEndDateCTA.click();
        capitalTxtField.click();
        String CapitalAmount = String.valueOf(constants.capital);
        capitalTxtField.clear();
        capitalTxtField.sendKeys(CapitalAmount);
        executionParameters.click();
        limitOrderPriceTxtField.click();
        limitOrderPriceTxtField.clear();
        String limitOrder = String.valueOf(constants.limitOrderPrice);
        limitOrderPriceTxtField.sendKeys(limitOrder);
        Thread.sleep(4000);
        AddScheduleBotServiceName = serviceNameTxtField.getAttribute("value");
        log.info("Scheduled Service Name: " + AddScheduleBotServiceName);
        nextCTA.click();
        publishCTA.click();
        waitForElementToBeInVisible(loadingPage);
        ScheduledServiceName = scheduledBotName.getText();
        log.info("Created Bot Service: " + ScheduledServiceName);
        Assert.assertEquals(AddScheduleBotServiceName, ScheduledServiceName);
    }

    public void addBotServiceWithScheduleRiskM() throws InterruptedException {
        waitForElementToBeInVisible(loadingPage);
        waitForWebElementToAppear(createBotServiceCTA);
        createBotServiceCTA.click();
        scheduleBotCTA.click();
        waitForElementToBeInVisible(loadingPage);
        Thread.sleep(1000);
        startDate.click();
        todayStartDate.click();
        applyStartDateCTA.click();
        endDate.click();
        todayEndDate.click();
        applyEndDateCTA.click();
        capitalTxtField.click();
        String CapitalAmount = String.valueOf(constants.capital);
        capitalTxtField.clear();
        capitalTxtField.sendKeys(CapitalAmount);
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
        AddScheduleBotServiceName = serviceNameTxtField.getAttribute("value");
        log.info("Scheduled Service Name: " + AddScheduleBotServiceName);
        nextCTA.click();
        publishCTA.click();
        waitForElementToBeInVisible(loadingPage);
        ScheduledServiceName = scheduledBotName.getText();
        log.info("Created Bot Service: " + ScheduledServiceName);
        Assert.assertEquals(AddScheduleBotServiceName, ScheduledServiceName);
    }

    public void createBotServiceWithStart() throws InterruptedException {
        botService();
        int deleteCTASize = botServiceDeleteCTA.size();
        int activateRadioSize = botActivateRadioCTA.size();

        if (deleteCTASize > 0) {
            if (activateRadioSize > 0) {
                for (int i = 0; activateRadioSize > i; i++) {
                    Thread.sleep(2000);
                    botActivateRadioCTA.get(0).click();
                    waitForWebElementToAppear(inactivate);
                    inactivate.click();
                    waitForElementToBeInVisible(loadingPage);
                }
                for (int j = 0; deleteCTASize > j; j++) {
                    Thread.sleep(2000);
                    botServiceDeleteCTA.get(0).click();
                    serviceDeleteCTA.click();
                    boolean Alert = serviceDeleteAlert.isDisplayed();
                    Assert.assertTrue(Alert);
                }
                addBotServiceWithStart();
            } else {
                for (int k = 0; deleteCTASize > k; k++) {
                    Thread.sleep(2000);
                    botServiceDeleteCTA.get(0).click();
                    serviceDeleteCTA.click();
                    boolean Alert = serviceDeleteAlert.isDisplayed();
                    Assert.assertTrue(Alert);
                }
                addBotServiceWithStart();
            }
        } else {
            addBotServiceWithStart();
        }
    }

    public void createBotServiceWithStartRiskM() throws InterruptedException {
        botService();
        int deleteCTASize = botServiceDeleteCTA.size();
        int activateRadioSize = botActivateRadioCTA.size();

        if (deleteCTASize > 0) {
            if (activateRadioSize > 0) {
                for (int i = 0; activateRadioSize > i; i++) {
                    Thread.sleep(2000);
                    botActivateRadioCTA.get(0).click();
                    waitForWebElementToAppear(inactivate);
                    inactivate.click();
                    waitForElementToBeInVisible(loadingPage);
                }
                for (int j = 0; deleteCTASize > j; j++) {
                    Thread.sleep(2000);
                    botServiceDeleteCTA.get(0).click();
                    serviceDeleteCTA.click();
                    boolean Alert = serviceDeleteAlert.isDisplayed();
                    Assert.assertTrue(Alert);
                }
                addBotServiceWithStartRiskManagement();
            } else {
                for (int k = 0; deleteCTASize > k; k++) {
                    Thread.sleep(2000);
                    botServiceDeleteCTA.get(0).click();
                    serviceDeleteCTA.click();
                    boolean Alert = serviceDeleteAlert.isDisplayed();
                    Assert.assertTrue(Alert);
                }
                addBotServiceWithStartRiskManagement();
            }
        } else {
            addBotServiceWithStartRiskManagement();
        }
    }

    public void createBotServiceWithStartExecution() throws InterruptedException {
        botService();
        int deleteCTASize = botServiceDeleteCTA.size();
        int activateRadioSize = botActivateRadioCTA.size();

        if (deleteCTASize > 0) {
            if (activateRadioSize > 0) {
                for (int i = 0; activateRadioSize > i; i++) {
                    Thread.sleep(2000);
                    botActivateRadioCTA.get(0).click();
                    waitForWebElementToAppear(inactivate);
                    inactivate.click();
                    waitForElementToBeInVisible(loadingPage);
                }
                for (int j = 0; deleteCTASize > j; j++) {
                    Thread.sleep(2000);
                    botServiceDeleteCTA.get(0).click();
                    serviceDeleteCTA.click();
                    boolean Alert = serviceDeleteAlert.isDisplayed();
                    Assert.assertTrue(Alert);
                }
                addBotServiceWithStartExecution();
            } else {
                for (int k = 0; deleteCTASize > k; k++) {
                    Thread.sleep(2000);
                    botServiceDeleteCTA.get(0).click();
                    serviceDeleteCTA.click();
                    boolean Alert = serviceDeleteAlert.isDisplayed();
                    Assert.assertTrue(Alert);
                }
                addBotServiceWithStartExecution();
            }
        } else {
            addBotServiceWithStartExecution();
        }
    }

    public void createBotServiceWithSchedule() throws InterruptedException {
        botService();
        int deleteCTASize = botServiceDeleteCTA.size();
        int activateRadioSize = botActivateRadioCTA.size();

        if (deleteCTASize > 0) {
            if (activateRadioSize > 0) {
                for (int i = 0; activateRadioSize > i; i++) {
                    Thread.sleep(2000);
                    botActivateRadioCTA.get(0).click();
                    waitForWebElementToAppear(inactivate);
                    inactivate.click();
                    waitForElementToBeInVisible(loadingPage);
                }
                for (int j = 0; deleteCTASize > j; j++) {
                    Thread.sleep(2000);
                    botServiceDeleteCTA.get(0).click();
                    serviceDeleteCTA.click();
                    boolean Alert = serviceDeleteAlert.isDisplayed();
                    Assert.assertTrue(Alert);
                }
                addBotServiceWithSchedule();
            } else {
                for (int k = 0; deleteCTASize > k; k++) {
                    Thread.sleep(2000);
                    botServiceDeleteCTA.get(0).click();
                    serviceDeleteCTA.click();
                    boolean Alert = serviceDeleteAlert.isDisplayed();
                    Assert.assertTrue(Alert);
                }
                addBotServiceWithSchedule();
            }
        } else {
            addBotServiceWithSchedule();
        }
    }

    public void createBotServiceWithScheduleRiskManagement() throws InterruptedException {
        botService();
        int deleteCTASize = botServiceDeleteCTA.size();
        int activateRadioSize = botActivateRadioCTA.size();

        if (deleteCTASize > 0) {
            if (activateRadioSize > 0) {
                for (int i = 0; activateRadioSize > i; i++) {
                    Thread.sleep(2000);
                    botActivateRadioCTA.get(0).click();
                    waitForWebElementToAppear(inactivate);
                    inactivate.click();
                    waitForElementToBeInVisible(loadingPage);
                }
                for (int j = 0; deleteCTASize > j; j++) {
                    Thread.sleep(2000);
                    botServiceDeleteCTA.get(0).click();
                    serviceDeleteCTA.click();
                    boolean Alert = serviceDeleteAlert.isDisplayed();
                    Assert.assertTrue(Alert);
                }
                addBotServiceWithScheduleRiskM();
            } else {
                for (int k = 0; deleteCTASize > k; k++) {
                    Thread.sleep(2000);
                    botServiceDeleteCTA.get(0).click();
                    serviceDeleteCTA.click();
                    boolean Alert = serviceDeleteAlert.isDisplayed();
                    Assert.assertTrue(Alert);
                }
                addBotServiceWithScheduleRiskM();
            }
        } else {
            addBotServiceWithScheduleRiskM();
        }
    }

    public void createBotServiceWithScheduleExecution() throws InterruptedException {
        botService();
        int deleteCTASize = botServiceDeleteCTA.size();
        int activateRadioSize = botActivateRadioCTA.size();

        if (deleteCTASize > 0) {
            if (activateRadioSize > 0) {
                for (int i = 0; activateRadioSize > i; i++) {
                    Thread.sleep(2000);
                    botActivateRadioCTA.get(0).click();
                    waitForWebElementToAppear(inactivate);
                    inactivate.click();
                    waitForElementToBeInVisible(loadingPage);
                }
                for (int j = 0; deleteCTASize > j; j++) {
                    Thread.sleep(2000);
                    botServiceDeleteCTA.get(0).click();
                    serviceDeleteCTA.click();
                    boolean Alert = serviceDeleteAlert.isDisplayed();
                    Assert.assertTrue(Alert);
                }
                addBotServiceWithScheduleExecution();
            } else {
                for (int k = 0; deleteCTASize > k; k++) {
                    Thread.sleep(2000);
                    botServiceDeleteCTA.get(0).click();
                    serviceDeleteCTA.click();
                    boolean Alert = serviceDeleteAlert.isDisplayed();
                    Assert.assertTrue(Alert);
                }
                addBotServiceWithScheduleExecution();
            }
        } else {
            addBotServiceWithScheduleExecution();
        }
    }

    public void deleteBotService() throws InterruptedException {
        botService();
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
            addBotServiceWithStart();
            botActivateRadioCTA.get(0).click();
            waitForElementToBeInVisible(loadingPage);
            botServiceDeleteCTA.get(0).click();
            serviceDeleteCTA.click();
            boolean Alert = serviceDeleteAlert.isDisplayed();
            Assert.assertTrue(Alert);
        }
    }
}
