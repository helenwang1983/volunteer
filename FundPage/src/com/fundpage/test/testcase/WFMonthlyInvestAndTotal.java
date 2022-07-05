package com.fundpage.test.testcase;

import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.fundpage.test.page.HomePage;
import com.fundpage.test.page.WealthFormulaPage;
import com.fundpage.test.util.*;

public class WFMonthlyInvestAndTotal extends TestCase {
	
	private final int DAYS_IN_MONTH = 30;

	HomePage homepage = new HomePage();
	WealthFormulaPage wfPage = new WealthFormulaPage();

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod() throws Throwable {
		wfPage.openWealthFormulaPage();
	}

	@Test
	public void wealthFormula() throws Throwable {

		Properties prop = wfPage.readPropertyFile(ScenarioData.getWealthFormulaSearchPropritiesFile());
		wfPage.inputSearchConditionsAndRefresh(prop.getProperty("security_1"), prop.getProperty("startDate_1"),
				prop.getProperty("dollarSavedPerDay_1"));

		WebElement detailInvestMonthlyTable = wfPage.getDetailInvestMonthlyTable();

		String startDate = wfPage.getTableCell(detailInvestMonthlyTable, wfPage.INITIAL_INVESTMENT_ROW_NUM,
				wfPage.DATE_COLUMN_NUM);
		int totalOnStartDate = Integer.parseInt(wfPage.getTableCell(detailInvestMonthlyTable,
				wfPage.INITIAL_INVESTMENT_ROW_NUM, wfPage.INVESTMENT_IN_TOTAL_COLUMN_NUM));
		int investmentOnStartDate = Integer.parseInt(wfPage.getTableCell(detailInvestMonthlyTable,
				wfPage.INITIAL_INVESTMENT_ROW_NUM, wfPage.INVESTMENT_COLUMN_NUM));

		// Calculate Investment and Investment Total
		int expectedInvestment = Integer.parseInt(prop.getProperty("dollarSavedPerDay_1")) * DAYS_IN_MONTH;
		int expectedInitialInvestmentTotal = (int) (expectedInvestment);

		// Compare the calculated Investment with the shown Investment
		softAssert.assertEquals(investmentOnStartDate, expectedInvestment,
				prop.getProperty("security_1") + " " + startDate + " Investment is incorrect");
		softAssert.assertEquals(totalOnStartDate, expectedInitialInvestmentTotal,
				prop.getProperty("security_1") + " " + startDate + " Investment Total is incorrect");

		for (int i = 2; i < wfPage.getNumOfRows(detailInvestMonthlyTable) - 1; i++) {
			String date = wfPage.getTableCell(detailInvestMonthlyTable, i, wfPage.DATE_COLUMN_NUM);
			int investment = Integer
					.parseInt(wfPage.getTableCell(detailInvestMonthlyTable, i, wfPage.INVESTMENT_COLUMN_NUM));
			int total = Integer
					.parseInt(wfPage.getTableCell(detailInvestMonthlyTable, i, wfPage.INVESTMENT_IN_TOTAL_COLUMN_NUM));

			int totalInPreviousRow = Integer.parseInt(
					wfPage.getTableCell(detailInvestMonthlyTable, i - 1, wfPage.INVESTMENT_IN_TOTAL_COLUMN_NUM));

			// Calculate Investment Total
			int expectedTotal = (int) (investment + totalInPreviousRow);

			// Compare the calculated Investment with the shown Investment
			softAssert.assertTrue(investment == expectedInvestment || investment == 0,
					prop.getProperty("security_1") + " " + date + " Investment is incorrect");
			// Compare the calculated Investment Total with the shown Investment Total
			softAssert.assertEquals(total, expectedTotal,
					prop.getProperty("security_1") + " " + date + " Investment Total is incorrect");

			softAssert.assertAll();
		}
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod() throws Exception {
		wfPage.closeBrowser();
	}
}
