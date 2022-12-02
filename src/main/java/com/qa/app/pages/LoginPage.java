package com.qa.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.app.utilities.Constants;
import com.qa.app.utilities.ElementUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil ele;

	private By userName = By.name("email");
	private By password = By.name("password");
	private By loginBtn = By.cssSelector("input.btn.btn-primary");
	private By forgotPwd = By.linkText("Forgotten Password");
	private By signUp = By.linkText("Continue");
	private By loginFailMsg = By.cssSelector("div.alert.alert-danger.alert-dismissible");
	private By registerLink=By.linkText("Register");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		ele = new ElementUtil(driver);
	}

	public boolean doVerifyCurrentUrl() {
		return ele.waitForURLToContain(Constants.LOGIN_PAGE_URL_FRACTION, Constants.STANDRAD_TIME_OUT);
	}

	public boolean doVerifyTitle() {
		return ele.waitForTitleContains(Constants.LOGIN_PAGE_TITLE_FRACTION, Constants.STANDRAD_TIME_OUT);
	}

	public AccountsPage doLogin(String user, String pass) {
		ele.doSendKeys(userName, user);
		ele.doSendKeys(password, pass);
		ele.doClick(loginBtn);
		return new AccountsPage(driver);
	}

	public boolean doLoginWithInvalidCredentials(String un, String pwd) {
		ele.doSendKeys(userName, un);
		ele.doSendKeys(password, pwd);
		ele.doClick(loginBtn);

		String failMsg = ele.doGetText(loginFailMsg, Constants.STANDRAD_TIME_OUT);
		System.out.println(failMsg);

		if (failMsg.contains(Constants.LOGIN_FAIL_MESSAGE)) {
			System.out.println("Login failed as the credentials are not valid");
			return false;
		}
		return true;
	}

	public boolean doVerifyForgotPassword() {
		return ele.isElementExist(forgotPwd);
	}

	public boolean doVerifySignupLink() {
		return ele.isElementExist(signUp);
	}

	public RegistrationPage registerAccount() {
		ele.doClick(registerLink);
		return new RegistrationPage(driver);
	}
}
