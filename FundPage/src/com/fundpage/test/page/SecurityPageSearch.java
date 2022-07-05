package com.fundpage.test.page;

import org.testng.Assert;

import com.fundpage.test.common.BasePage;

public class SecurityPageSearch extends BasePage {
    private String pageTitleText = "xpath=//h3[contains(text(),'Investment Opportunities Search')]";
    
	public void checkSearchSecurityPageOpened() {
		Assert.assertEquals(verifyElementIsExisted(pageTitleText), true);
	}
}
