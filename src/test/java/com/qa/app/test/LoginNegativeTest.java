package com.qa.app.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.app.listeners.TestAllureListener;

@Listeners(TestAllureListener.class)
public class LoginNegativeTest extends BaseTest {

	@DataProvider
	public Object[][] credList() {
		return new Object[][] { { "test@gmail.com", "test@123" }, { "test123@gmail.com", " " }, { " ", "test@123" },
				{ "123456", "awefvrgg" } };
	}

	@Test(dataProvider = "credList")
	public void negativeLoginTest(String user, String pwd) {
		Assert.assertFalse(lp.doLoginWithInvalidCredentials(user, pwd));
	}

}
