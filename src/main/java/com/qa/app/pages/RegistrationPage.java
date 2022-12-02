package com.qa.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.app.utilities.Constants;
import com.qa.app.utilities.ElementUtil;

public class RegistrationPage {

	private ElementUtil ele;

	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");

	private By password = By.id("input-password");
	private By cnfrmPwd = By.id("input-confirm");

	private By subscYes = By.xpath("(//div//label[@class='radio-inline']/input)[1]");
	private By subscNo = By.xpath("(//div//label[@class='radio-inline']/input)[2]");

	private By agreePolicy = By.xpath("//div//div[@class='pull-right']//input[@name='agree']");
	private By continueBtn = By.cssSelector("input.btn.btn-primary");

	private By successMsg = By.cssSelector("div#content h1");
	private By logoutBtn = By.linkText("Logout");
	private By registerBtn = By.linkText("Register");

	public RegistrationPage(WebDriver driver) {
		ele = new ElementUtil(driver);
	}

	public boolean doRegisterAccount(String fn, String ln, String email, String tel, String pwd, String subsc) {
		ele.doSendKeys(this.firstName, fn);
		ele.doSendKeys(this.lastName, ln);
		ele.doSendKeys(this.email, email);
		ele.doSendKeys(this.telephone, tel);
		ele.doSendKeys(this.password, pwd);
		ele.doSendKeys(this.cnfrmPwd, pwd);

		if (subsc.equals("yes")) {
			ele.doClick(subscYes);
		} else {
			ele.doClick(subscNo);
		}
		ele.doClick(agreePolicy);
		ele.doClick(continueBtn);

		String msg = ele.doGetText(successMsg, Constants.STANDRAD_TIME_OUT);
		
		if (msg.contains(Constants.ACCOUNT_REGISTRATION_SUCCESS_MESSAGE)) {
			ele.doClick(logoutBtn);
			ele.doClick(registerBtn);
			return true;
		}
		return false;
	}
}
