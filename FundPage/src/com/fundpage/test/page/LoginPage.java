package com.fundpage.test.page;

import com.fundpage.test.common.BasePage;

public class LoginPage extends BasePage {
	private String userNameId = "id=UserName";
	private String pwdId = "id=inputPassword";
	private String loginBtn = "xpath=//button[@type='submit']";

	public void login(String userName, String password) throws Throwable {
		// input username
		inputValue(userNameId, userName);
		// input password
		inputValue(pwdId, password);
		// click login button
		clickBtn(loginBtn);
	}
}
