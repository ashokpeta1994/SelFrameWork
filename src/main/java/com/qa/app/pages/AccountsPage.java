package com.qa.app.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.app.utilities.Constants;
import com.qa.app.utilities.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By pageHeader = By.cssSelector("img.img-responsive");
	private By accPageSecs = By.cssSelector("div#content h2");
	private By searchField = By.name("search");
	private By searchBtn = By.cssSelector("button.btn.btn-default.btn-lg");
	private By downloadsLink = By.linkText("Downloads");
	private By logoutLink = By.linkText("Logout");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public boolean doVerifyAccountsPageTitle() {
		return eleUtil.waitForTitleContains(Constants.ACCOUNTS_PAGE_TITLE_FRACTION, Constants.STANDRAD_TIME_OUT);
	}

	public boolean doVerifyAccountsPageUrl() {
		return eleUtil.waitForURLToContain(Constants.ACCOUNTS_PAGE_URL_FRACTION, Constants.STANDRAD_TIME_OUT);
	}

	public boolean doVerifyPageHeader() {
		return eleUtil.doIsDisplayed(pageHeader);
	}

	public List<String> doVerifyAccountsPageSections() {
		List<WebElement> secList = eleUtil.getElements(accPageSecs);
		List<String> expSecList = new ArrayList<String>();
		for (WebElement e : secList) {
			String str = e.getText();
			expSecList.add(str);
		}
		return expSecList;

	}

	public boolean doVerifyDownloadsLink() {
		return eleUtil.doIsDisplayed(downloadsLink);
	}

	public boolean doVerifyLogoutLink() {
		return eleUtil.doIsDisplayed(logoutLink);
	}

	public SearchResultsPage doPerformSearch(String productName) {
		eleUtil.doSendKeys(searchField, productName);
		eleUtil.doClick(searchBtn);
		return new SearchResultsPage(driver);
	}
}
