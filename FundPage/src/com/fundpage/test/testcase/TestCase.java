package com.fundpage.test.testcase;

import org.testng.asserts.SoftAssert;

import com.fundpage.test.common.WebPage;

public abstract class TestCase extends WebPage {

	protected SoftAssert softAssert = null;
	
	public TestCase() {
		this.softAssert = new SoftAssert();
	}
}
