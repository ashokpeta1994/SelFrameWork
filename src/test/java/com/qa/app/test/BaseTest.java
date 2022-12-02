package com.qa.app.test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.app.driver.DriverFactory;
import com.qa.app.pages.AccountsPage;
import com.qa.app.pages.LoginPage;
import com.qa.app.pages.ProductInfoPage;
import com.qa.app.pages.RegistrationPage;
import com.qa.app.pages.SearchResultsPage;

public class BaseTest {

	LoginPage lp;
	DriverFactory df;
	Properties prop;
	WebDriver driver;
	AccountsPage accPage;
	SearchResultsPage searchPage;
	ProductInfoPage productPage;
	SoftAssert soft;
	RegistrationPage regPage;

	@BeforeTest
	public void beforeTest() {
		df = new DriverFactory();
		prop = df.setProp();
		driver = df.browserSetup(prop);
		lp = new LoginPage(driver);
		soft = new SoftAssert();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
