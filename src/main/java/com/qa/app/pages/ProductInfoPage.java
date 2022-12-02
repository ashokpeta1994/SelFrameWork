package com.qa.app.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.app.utilities.Constants;
import com.qa.app.utilities.ElementUtil;

public class ProductInfoPage {

	@SuppressWarnings("unused")
	private WebDriver driver;
	private ElementUtil eleUtil;
	private Map<String, String> metaData;

	private By pageHeader = By.cssSelector("div#content h1");
	private By imagesList = By.cssSelector("div#content ul.thumbnails li a.thumbnail");
	private By addToCartBtn = By.id("button-cart");
	private By productData = By.cssSelector("div#content div.col-sm-4 ul:nth-of-type(1) li");
	private By priceData = By.cssSelector("div#content div.col-sm-4 ul:nth-of-type(2) li");
	private By addCartVeriText = By.cssSelector("div.alert.alert-success.alert-dismissible");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getHeaderText() {
		String header = eleUtil.doGetText(pageHeader, Constants.STANDRAD_TIME_OUT);
		System.out.println(header);
		return header;
	}

	public int imageCount() {
		return eleUtil.getElements(imagesList).size();
	}

	public boolean isAddCartBtnVisible() {
		return eleUtil.doIsDisplayed(addToCartBtn);
	}

	public Map<String, String> verifyMetaData() {
		metaData = new LinkedHashMap<String, String>();
		metaData.put("Product Name", getHeaderText());
		productMetaData();
		productPriceData();
		return metaData;
	}

	private void productMetaData() {
		List<WebElement> list = eleUtil.getElements(productData);
		for (WebElement e : list) {
			String text = e.getText();
			String data[] = text.split(":");
			String key = data[0].trim();
			String value = data[1].trim();
			metaData.put(key, value);
		}
	}

	private void productPriceData() {
		List<WebElement> priceList = eleUtil.getElements(priceData);
		String price = priceList.get(0).getText().trim();
		String exTaxPrice = priceList.get(1).getText().trim();
		metaData.put("Price", price);
		metaData.put("ExTaxPrice", exTaxPrice);
	}

	public String doClickAddToCart() {
		eleUtil.doClick(addToCartBtn);
		return eleUtil.doGetText(addCartVeriText, Constants.STANDRAD_TIME_OUT);
	}

}
