package com.qa.app.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.app.listeners.TestAllureListener;
import com.qa.app.utilities.Constants;

@Listeners(TestAllureListener.class)
public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void doLoginTest() {
		accPage = lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void verifyHeaderTest() {
		Assert.assertTrue(accPage.doVerifyPageHeader());
	}

	@Test(priority = 2)
	public void verifyTitleTest() {
		Assert.assertTrue(accPage.doVerifyAccountsPageTitle());
	}

	@Test(priority = 3)
	public void verifyUrlTest() {
		Assert.assertTrue(accPage.doVerifyAccountsPageUrl());
	}

	@Test(priority = 4)
	public void verifyDownloadsLinkTest() {
		Assert.assertTrue(accPage.doVerifyDownloadsLink());
	}

	@Test(priority = 5)
	public void verifyLogoutLinkTest() {
		Assert.assertTrue(accPage.doVerifyLogoutLink());
	}

	@Test(priority = 6)
	public void verifyPageSectionsTest() {
		List<String> expList = accPage.doVerifyAccountsPageSections();
		Assert.assertEquals(expList, Constants.actualList());
	}

	@DataProvider
	public Object[][] productsSearch() {
		return new Object[][] { { "MacBook" }, { "Apple" }, { "Samsung" } };
	}

	@Test(dataProvider = "productsSearch", priority = 7)
	public void verifySearchTest(String productName) {
		searchPage = accPage.doPerformSearch(productName);
		Assert.assertTrue(searchPage.getProductsCount() > 0);
	}

	@DataProvider
	public Object[][] productsList() {
		return new Object[][] { { "Macbook", "MacBook Air" }, { "Apple", "Apple Cinema 30\"" },
				{ "Samsung", "Samsung Galaxy Tab 10.1" } };
	}

	@Test(dataProvider = "productsList", priority = 8)
	public void verifyMainProductSearch(String product, String mainProduct) {
		searchPage = accPage.doPerformSearch(product);
		productPage = searchPage.doGetProductsList(mainProduct);
		Assert.assertEquals(productPage.getHeaderText(), mainProduct);

	}
}
