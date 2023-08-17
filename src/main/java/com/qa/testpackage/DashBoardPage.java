package com.qa.testpackage;

import com.qa.baseclass.BaseClass;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import java.util.List;
@Log4j2
public class DashBoardPage extends BaseClass
{
	public Select objSelect;

	@FindBy(css = "span[class='dashboard']+span")
	public WebElement dashboardOption;
	@FindBy(xpath="//li[@class='col']/a/descendant::span")
	List<WebElement> graphsType;
	@FindBy(xpath = "(//select[@name='duration'])[1]")
	public WebElement durationDropDown;
	@FindBy(css = "div[class='loader']")
	public WebElement loadingPage;
	@FindBy(css = "input[name='limitbuyQty']")
	public WebElement quantityTxtField;
	@FindBy(css = "input[name='limitsellQty']")
	public WebElement quantitySellTxtField;
	@FindBy(name = "marketbuyQty")
	public WebElement quantityMarketTxtField;
	@FindBy(name = "marketsellQty")
	public WebElement quantitySellMarketTxtField;
	@FindBy(css = "input[class='form-control']")
	public List<WebElement> limitGridTxtFieldlist;
	@FindBy(css = "a[id='selltab']")
	public WebElement sellCTA;
	@FindBy(css = "li[class='nav-item'] a[data-toggle='tab']")
	public List<WebElement> navBuyList;
	@FindBy(css = "li[class='nav-item'] a[data-toggle='tab']")
	public List<WebElement> navSellList;
	@FindBy(xpath = "//span[@class='sort']//preceding-sibling::span")
	public List<WebElement> investedAssetsList;
	@FindBy(css = "span[class='transtab-text'][data-view='marketview']")
	public WebElement marketViewNavCTA;
	@FindBy(css = "div[class='box']")
	public WebElement digitalCurrencyDropDown;
	@FindBy(css = "div[class='highlight']")
	public List<WebElement> digitalValuesList;

	public DashBoardPage() {
		PageFactory.initElements(driver, this);
	}
	public void homePage() throws InterruptedException {
		waitForWebElementToAppear(dashboardOption);
		String dashboardURL = driver.getCurrentUrl();
		Assert.assertEquals(dashboardURL,"https://dev.y2tek.io/dashboard");
	}

	public void userViewPage() throws InterruptedException {
		waitForElementToBeInVisible(loadingPage);
		for (WebElement graphTypeList : graphsType){
			String graphlist = graphTypeList.getText();
			if (graphlist.equalsIgnoreCase("Profit")){
				waitForElementToBeInVisible(loadingPage);
				graphTypeList.click();
				objSelect = new Select(durationDropDown);
				objSelect.selectByValue("day");
			} else if (graphlist.equalsIgnoreCase("Balance")) {
				waitForElementToBeInVisible(loadingPage);
				graphTypeList.click();
				objSelect = new Select(durationDropDown);
				objSelect.selectByValue("year");
			} else if (graphlist.equalsIgnoreCase("Volume")) {
				waitForElementToBeInVisible(loadingPage);
				graphTypeList.click();
				objSelect = new Select(durationDropDown);
				objSelect.selectByValue("month");
			}
		}
		for (WebElement investedList: investedAssetsList){
			String inAssetsList = investedList.getText();
			if (inAssetsList.equalsIgnoreCase("Coins")){
				Assert.assertEquals(inAssetsList,"Coins");
			} else if (inAssetsList.equalsIgnoreCase("Quantity/Volume")) {
				Assert.assertEquals(inAssetsList,"Quantity/Volume");
			}else if (inAssetsList.equalsIgnoreCase("Invested Amount")) {
				Assert.assertEquals(inAssetsList,"Invested Amount");
			}else if (inAssetsList.equalsIgnoreCase("Current Amount")) {
				Assert.assertEquals(inAssetsList,"Current Amount");
			}else if (inAssetsList.equalsIgnoreCase("Profit %")) {
				Assert.assertEquals(inAssetsList,"Profit %");
			}else if (inAssetsList.equalsIgnoreCase("Sell/Buy")) {
				Assert.assertEquals(inAssetsList,"Sell/Buy");
			}else if (inAssetsList.equalsIgnoreCase("Earnings")) {
				Assert.assertEquals(inAssetsList,"Earnings");
			}
		}
	}

	public void marketViewPage() throws InterruptedException {
		waitForElementToBeInVisible(loadingPage);
		marketViewNavCTA.click();
		waitForElementToBeInVisible(loadingPage);
		waitForElementToBeClickable(digitalCurrencyDropDown);
		digitalCurrencyDropDown.click();
		try {
			for (WebElement valuesList: digitalValuesList){
				String list = valuesList.getAttribute("value");
				if (list.equalsIgnoreCase("ETHUSDT")){
					valuesList.click();
					waitForElementToBeInVisible(loadingPage);
				}
			}
		}catch (StaleElementReferenceException staleEle){
			log.info("Exception: "+staleEle);
		}
		String limitQuantity = String.valueOf(constants.limitQuantities);
		quantityTxtField.sendKeys(limitQuantity);
		for (WebElement navBuylist : navBuyList){
			String buyType = navBuylist.getText();
			if (buyType.equalsIgnoreCase("Market")){
				navBuylist.click();
				String marketQuantity = String.valueOf(constants.marketQuantities);
				quantityMarketTxtField.sendKeys(marketQuantity);
			} else if (buyType.equalsIgnoreCase("Grid Buy")) {
				navBuylist.click();
				String gridBuyLimits = String.valueOf(constants.gridBuyLimits);
				limitGridTxtFieldlist.get(0).sendKeys(gridBuyLimits);
				limitGridTxtFieldlist.get(1).sendKeys(gridBuyLimits);
				limitGridTxtFieldlist.get(2).sendKeys(gridBuyLimits);
			}
		}
		sellCTA.click();
		String limitSellQuantity = String.valueOf(constants.limitQuantities);
		quantitySellTxtField.sendKeys(limitSellQuantity);
		try {
			for (WebElement sellNav : navSellList){
				String sellType = sellNav.getText();
				if (sellType.equalsIgnoreCase("Market")){
					sellNav.click();
					String marketSellQuantity = String.valueOf(constants.marketQuantities);
					quantitySellMarketTxtField.sendKeys(marketSellQuantity);
				}else if (sellType.equalsIgnoreCase("Grid Sell")) {
					sellNav.click();
					String gridBuyLimits = String.valueOf(constants.gridBuyLimits);
					limitGridTxtFieldlist.get(0).sendKeys(gridBuyLimits);
					limitGridTxtFieldlist.get(1).sendKeys(gridBuyLimits);
					limitGridTxtFieldlist.get(2).sendKeys(gridBuyLimits);
				}
			}
		}catch (StaleElementReferenceException staleElement){
			staleElement.printStackTrace();
		}
	}
}
