package com.qa.app.test;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.app.listeners.TestAllureListener;
import com.qa.app.utilities.Constants;
import com.qa.app.utilities.ExcelUtil;

@Listeners(TestAllureListener.class)
public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void registerPageTest() {
		regPage = lp.registerAccount();
	}

	@DataProvider
	public Object[][] testDataTest() {
		return ExcelUtil.getTestData(Constants.REGISTER_EXCEL_SHEET_NAME);
	}

	public String randomEmailGen() {
		Random rand = new Random();
		String email = "ashokpeta" + rand.nextInt(5000) + "@gmail.com";
		return email;
	}

	@Test(dataProvider = "testDataTest")
	public void registerAccountTest(String fn, String ln, String tel, String pwd, String subsc) {
		Assert.assertTrue(regPage.doRegisterAccount(fn, ln, randomEmailGen(), tel, pwd, subsc));
	}
}
