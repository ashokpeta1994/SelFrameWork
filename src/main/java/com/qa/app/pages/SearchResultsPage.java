package com.qa.app.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.app.utilities.Constants;
import com.qa.app.utilities.ElementUtil;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By prodList = By.cssSelector("div.caption a");

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public int getProductsCount() {
		return eleUtil.waitForElementsToBeVisible(prodList, Constants.STANDRAD_TIME_OUT).size();
	}

	public ProductInfoPage doGetProductsList(String mainProduct) {
		List<WebElement> list = eleUtil.getElements(prodList);
		for (WebElement e : list) {
			if (e.getText().equals(mainProduct)) {
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}

}
