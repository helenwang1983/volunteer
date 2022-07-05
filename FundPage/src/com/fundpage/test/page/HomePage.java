package com.fundpage.test.page;

import com.fundpage.test.common.BasePage;

public class HomePage extends BasePage {
    private String baseUrl = "https://www.fundpage.com/";
    private String loginIconXpath = "xpath=//a[contains(text(),'Login')]";
    private String signUpIconXpath = "xpath=//a[contains(text(),'Sign Up')]";
    private String investOppMenu = "xpath=//span[contains(text(),'Investment Opportunities')]";
    private String WFMenu = "xpath=//span[contains(text(),'Wealth Formula')]";
    private String searchSecurity = "xpath=//span[contains(text(),'Search Securities')]";

    public void openHomePage() throws Exception {
        getURL(baseUrl);
    }

    public void clickLoginIcon() {
        clickBtn(loginIconXpath);
    }

    public void clickSignUpIcon() {
        clickBtn(signUpIconXpath);
    }

    public void maximizeHomePage() {
        maximizeWindow();
    }

    public void mouseHoverInvestmentOppAndClickWF() throws InterruptedException {
        mouseHoverMenuAndClickSubMenu(investOppMenu, WFMenu);
    }

    public void mouseHoverInvestmentOppAndClickSecuritySearch() throws InterruptedException {
        mouseHoverMenuAndClickSubMenu(investOppMenu, searchSecurity);
    }
}
