package com.fundpage.test.testcase;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fundpage.test.page.HomePage;
import com.fundpage.test.page.SecurityPageSearch;
import com.fundpage.test.page.WealthFormulaPage;

public class HomePageTest {
    HomePage homepage = new HomePage();

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() throws Exception {
        // open fundpage
        homepage.openHomePage();
        homepage.maximizeHomePage();
    }

    @Test(priority = 1)
    public void testOpenWF() throws Throwable {
        // open WF
        homepage.mouseHoverInvestmentOppAndClickWF();
        WealthFormulaPage wfPage = new WealthFormulaPage();
        wfPage.checkWFPageOpened();
    }

    @Test(priority = 2)
    public void testOpenSearchSecurity() throws Throwable {
        // open search security
        homepage.mouseHoverInvestmentOppAndClickSecuritySearch();
        SecurityPageSearch ssPage = new SecurityPageSearch();
        ssPage.checkSearchSecurityPageOpened();
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() throws Exception {
        homepage.closeBrowser();
    }
}
