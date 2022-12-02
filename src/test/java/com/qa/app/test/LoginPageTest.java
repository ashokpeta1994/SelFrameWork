package com.qa.app.test;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.app.listeners.TestAllureListener;

@Listeners(TestAllureListener.class)
public class LoginPageTest extends BaseTest {

	@Test(priority = 1)
	public void loginPageUrlTest() {
		Assert.assertTrue(lp.doVerifyCurrentUrl());
	}

	@Test(priority = 2)
	public void loginPageTitleTest() {
		Assert.assertTrue(lp.doVerifyTitle());
	}

	@Test(priority = 5)
	public void doLoginTest() {
		accPage = lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(accPage.doVerifyAccountsPageTitle());
	}

	@Test(priority = 3)
	public void verifyForPwdTest() {
		Assert.assertTrue(lp.doVerifyForgotPassword());
	}

	@Test(priority = 4)
	public void verifySignupLink() {
		Assert.assertTrue(lp.doVerifySignupLink());
	}

}
