package com.fundpage.test.page;

import org.testng.Assert;
import com.fundpage.test.common.BasePage;

public class MenuPage extends BasePage {
    private String logoutIconXpath = "xpath=//a[contains(text(),'Log Out')]";

    public void verifyLogOutIconIsDisplayed() {
        Assert.assertEquals(verifyElementIsExisted(logoutIconXpath), true);
    }
}
