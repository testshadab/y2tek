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
public class TransactionsPage extends BaseClass {

	public TransactionsPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "span[class='profile-span']")
	List<WebElement> serviceTabList;
	@FindBy(css = "h5[class='card-title']")
	public WebElement balanceTitle;
	@FindBy(css = "input[id='statement-date-range']")
	public WebElement dateRangeTxtField;
	@FindBy(css = "input[id='tradebook-date-range']")
	public WebElement tradeDateRangeTxtField;
	@FindBy(css = "input[id='profitloss-date-range']")
	public WebElement profitAndLossDateRangeTxtField;
	@FindBy(xpath = "//span[@class='arrow-image']/parent::button")
	public WebElement arrowCTA;
	@FindBy(xpath = "//div[contains(@class,'daterangepicker')]//following::button[contains(@class,'applyBtn')]")
	public WebElement calApplyCTA;
	@FindBy(xpath = "//span[@class='sort']//preceding-sibling::span")
	public List<WebElement> transactionTableList;
	@FindBy(xpath = "//span[@class='sort']//preceding-sibling::span")
	public List<WebElement> tradebookTableList;
	@FindBy(xpath = "//span[@class='sort']//preceding-sibling::span")
	public List<WebElement> profitAndLossTableList;
	@FindBy(css = "h5[class='card-title']")
	public List<WebElement> cardTableList;
	@FindBy(xpath = "(//button[contains(@class,'applyBtn btn btn-sm btn-primary')])[2]")
	public WebElement applyCTA;
	@FindBy(css = "span[class='transtab-text'][data-view='tradebook']")
	public WebElement tradeBookNavCTA;
	@FindBy(css = "span[class='transtab-text'][data-view='profitloss']")
	public WebElement profitAndLossNavCTA;
	@FindBy(xpath = "//div[@class='box']")
	public WebElement tradeBotListDropDown;
	@FindBy(xpath = "//div[@class='box']")
	public WebElement profitAndLossBotListDropDown;
	@FindBy(css = "div[value='[object Object]']")
	public List<WebElement> tradeBotList;
	@FindBy(css = "div[value='[object Object]']")
	public List<WebElement> profitAndLossBotList;
	@FindBy(xpath = "(//button[contains(@class,'applyBtn btn btn-sm btn-primary')])[2]")
	public WebElement profitAndLossApplyCTA;
	@FindBy(css = "div[class='loader']")
	public WebElement loadingPage;
	@FindBy(css = "button[id='dropdownMenuLink']")
	public WebElement downloadCTA;
	@FindBy(css = "[class*='doc-text']")
	public List<WebElement> downloadFileType;


	public void transactionsPage() {
		waitForElementToBeInVisible(loadingPage);
		try {
			for (WebElement serviceTabList : serviceTabList) {
				String botServiceTab = serviceTabList.getText();
				if (botServiceTab.equalsIgnoreCase("Transactions")) {
					waitForElementToBeInVisible(loadingPage);
					serviceTabList.click();
				}
			}
			waitForElementToBeInVisible(loadingPage);
		} catch (StaleElementReferenceException e) {
			log.info("Exception: " + e);
		}
		waitForWebElementToAppear(balanceTitle);
	}

	public void transactionsTable() throws InterruptedException {
		for (WebElement transactionslist : transactionTableList) {
			String ColNamelist = transactionslist.getText();
			if (ColNamelist.equalsIgnoreCase("Date")) {
				Assert.assertEquals(ColNamelist, "Date");
			} else if (ColNamelist.equalsIgnoreCase("Segment")) {
				Assert.assertEquals(ColNamelist, "Segment");
			} else if (ColNamelist.equalsIgnoreCase("Particulars")) {
				Assert.assertEquals(ColNamelist, "Particulars");
			} else if (ColNamelist.equalsIgnoreCase("Debit")) {
				Assert.assertEquals(ColNamelist, "Debit");
			} else if (ColNamelist.equalsIgnoreCase("Credit")) {
				Assert.assertEquals(ColNamelist, "Credit");
			} else if (ColNamelist.equalsIgnoreCase("Net")) {
				Assert.assertEquals(ColNamelist, "Net");
			}
		}
	}

	public void datePicker() throws InterruptedException {
		waitForElementToBeInVisible(loadingPage);
		dateRangeTxtField.click();
		waitForWebElementToAppear(calApplyCTA);
		calApplyCTA.click();
		arrowCTA.click();
	}

	public void downloadStatement() throws InterruptedException {
		waitForElementToBeInVisible(loadingPage);
		downloadCTA.click();
		for (WebElement download : downloadFileType) {
			String fileType = download.getText();
			if (download.getText().contains("CSV")) {
				Assert.assertEquals(fileType, "CSV");
			} else if (download.getText().contains("XLSX")) {
				Assert.assertEquals(fileType, "XLSX");
			}
		}
	}

	public void tradeBook() throws InterruptedException {
		waitForElementToBeInVisible(loadingPage);
		tradeBookNavCTA.click();
		waitForElementToBeInVisible(loadingPage);
		tradeBotListDropDown.click();
		tradeBotList.get(0).click();
		tradeDateRangeTxtField.click();
		applyCTA.click();
		downloadStatement();
		Thread.sleep(3000);
	}

	public void tradeBookTable() throws InterruptedException {
		for (WebElement tradebooklist : tradebookTableList) {
			String ColNamelist = tradebooklist.getText();
			System.out.println("Table: " + ColNamelist);
			if (ColNamelist.equalsIgnoreCase("Symbol")) {
				Assert.assertEquals(ColNamelist, "Symbol");
			} else if (ColNamelist.equalsIgnoreCase("Trade Time")) {
				Assert.assertEquals(ColNamelist, "Trade Time");
			} else if (ColNamelist.equalsIgnoreCase("Order Id")) {
				Assert.assertEquals(ColNamelist, "Order Id");
			} else if (ColNamelist.equalsIgnoreCase("Trade Id")) {
				Assert.assertEquals(ColNamelist, "Trade Id");
			} else if (ColNamelist.equalsIgnoreCase("Qty")) {
				Assert.assertEquals(ColNamelist, "Qty");
			} else if (ColNamelist.equalsIgnoreCase("Price")) {
				Assert.assertEquals(ColNamelist, "Price");
			}else if (ColNamelist.equalsIgnoreCase("Unit Price")) {
				Assert.assertEquals(ColNamelist, "Unit Price");
			}
		}
	}

	public void profitAndLoss() throws InterruptedException {
		waitForElementToBeInVisible(loadingPage);
		profitAndLossNavCTA.click();
		waitForElementToBeInVisible(loadingPage);
		profitAndLossBotListDropDown.click();
		profitAndLossBotList.get(0).click();
		profitAndLossDateRangeTxtField.click();
		profitAndLossApplyCTA.click();
		arrowCTA.click();
		downloadStatement();
		Thread.sleep(3000);
	}

	public void profitAndLossCardTable() throws InterruptedException {
		for (WebElement cardList : profitAndLossTableList) {
			String ColNamelist = cardList.getText();
			System.out.println("Table: " + ColNamelist);
			if (ColNamelist.equalsIgnoreCase("Symbol")) {
				Assert.assertEquals(ColNamelist, "Symbol");
			} else if (ColNamelist.equalsIgnoreCase("Qty")) {
				Assert.assertEquals(ColNamelist, "Qty");
			} else if (ColNamelist.equalsIgnoreCase("Buy Value")) {
				Assert.assertEquals(ColNamelist, "Buy Value");
			} else if (ColNamelist.equalsIgnoreCase("Sell Value")) {
				Assert.assertEquals(ColNamelist, "Sell Value");
			}
		}
	}

	public void profitAndLossTable() throws InterruptedException {
		for (WebElement cardList : cardTableList) {
			String ColNamelist = cardList.getText();
			System.out.println("Table: " + ColNamelist);
			if (ColNamelist.equalsIgnoreCase("Realized P&L")) {
				Assert.assertEquals(ColNamelist, "Realized P&L");
			} else if (ColNamelist.equalsIgnoreCase("Charges & Taxes")) {
				Assert.assertEquals(ColNamelist, "Charges & Taxes");
			} else if (ColNamelist.equalsIgnoreCase("Net Realized P&L")) {
				Assert.assertEquals(ColNamelist, "Net Realized P&L");
			} else if (ColNamelist.equalsIgnoreCase("Unrealized P&L")) {
				Assert.assertEquals(ColNamelist, "Unrealized P&L");
			}
		}
	}

}
