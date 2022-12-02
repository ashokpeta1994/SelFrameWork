package com.qa.app.utilities;

import java.util.ArrayList;
import java.util.List;

public class Constants {

	public static final String LOGIN_PAGE_TITLE_FRACTION = "Account";
	public static final String LOGIN_PAGE_URL_FRACTION = "account/login";

	public static final int STANDRAD_TIME_OUT = 20;

	public static final String ACCOUNTS_PAGE_TITLE_FRACTION = "My Account";
	public static final String ACCOUNTS_PAGE_URL_FRACTION = "route=account";

	public static final String SEARCH_RESULTS_PAGE_TITLE_FRACTION = "Search";
	public static final String SEARCH_RESULTS_PAGE_URL_FRACTION = "search&";
	public static final int PRODUCT_PAGE_IMAGE_COUNT = 7;
	public static final String LOGIN_FAIL_MESSAGE = "No match for E-Mail Address and/or Password";
	public static final String ACCOUNT_REGISTRATION_SUCCESS_MESSAGE = "Your Account Has Been Created!";
	public static final String REGISTER_EXCEL_SHEET_NAME = "Register";

	public static List<String> actualList() {

		List<String> list = new ArrayList<String>();

		list.add("My Account");
		list.add("My Orders");
		list.add("My Affiliate Account");
		list.add("Newsletter");

		return list;
	}

}
