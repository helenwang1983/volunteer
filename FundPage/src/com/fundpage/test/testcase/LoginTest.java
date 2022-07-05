package com.fundpage.test.testcase;

import java.util.Base64;
import java.util.Properties;

import org.testng.annotations.*;

import com.fundpage.test.page.HomePage;
import com.fundpage.test.page.LoginPage;
import com.fundpage.test.page.MenuPage;

public class LoginTest {
    private StringBuffer verificationErrors = new StringBuffer();
    private HomePage homepage = new HomePage();
    private MenuPage menupage = new MenuPage();
    private LoginPage loginpage = new LoginPage();

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() throws Throwable {
        // open fundpage
        homepage.openHomePage();
        homepage.maximizeHomePage();
    }

    @Test
    public void testLogin() throws Throwable {
        // click login icon
        homepage.clickLoginIcon();

        // input username and password to login
        Properties prop = loginpage.readPropertyFile("src\\com\\fundpage\\test\\util\\users.properties");
        //loginpage.login(prop.getProperty("test.username"), prop.getProperty("test.password"));
        byte[] decodedBytes = Base64.getDecoder().decode(prop.getProperty("test.password"));
		String pwdString = new String(decodedBytes);
        loginpage.login(prop.getProperty("test.username"), pwdString);
        try {
            // verify login is succeeded
            menupage.verifyLogOutIconIsDisplayed();
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() throws Exception {
        homepage.closeBrowser();
    }
}
