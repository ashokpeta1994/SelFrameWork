package com.qa.app.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.app.listeners.TestAllureListener;
import com.qa.app.utilities.Constants;

@Listeners(TestAllureListener.class)
public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void doLoginTest() {
		accPage = lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void verifyHeaderTest() {
		searchPage = accPage.doPerformSearch("Apple");
		productPage = searchPage.doGetProductsList("Apple Cinema 30\"");
		Assert.assertEquals(productPage.getHeaderText(), "Apple Cinema 30\"");
	}

	@Test(priority = 2)
	public void verifyImagesCountTest() {
		searchPage = accPage.doPerformSearch("Samsung");
		productPage = searchPage.doGetProductsList("Samsung Galaxy Tab 10.1");
		Assert.assertEquals(productPage.imageCount(), Constants.PRODUCT_PAGE_IMAGE_COUNT);
	}

	@Test(priority = 3)
	public void verifyMetaDataTest() {
		searchPage = accPage.doPerformSearch("MacBook");
		productPage = searchPage.doGetProductsList("MacBook");
		Map<String, String> expData = productPage.verifyMetaData();
		expData.forEach((k, v) -> System.out.println(k + " : " + v));
		soft.assertEquals(expData.get("Brand"), "Apple");
		soft.assertEquals(expData.get("Product Code"), "Product 16");
		soft.assertEquals(expData.get("Availability"), "In Stock");
		soft.assertEquals(expData.get("Price"), "$602.00");
		soft.assertAll();
	}

}
