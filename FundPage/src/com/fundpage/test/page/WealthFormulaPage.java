package com.fundpage.test.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.fundpage.test.common.BasePage;
import com.fundpage.test.util.FundPageTopology;

public class WealthFormulaPage extends BasePage {

	private final String PAGE_TITLE_LOCATOR = "xpath=//h3[contains(text(),'Wealth Formula')]";
	private final String btnRefresh = "id=wealth-calc";
	private final String txtSecurity = "id=search-sec-id";
	private final String txtStartDate = "id=search-from-datre";
	private final String txtDollarPerDay = "id=search-amount";
	private final String DETAIL_INVEST_MONTHLY_TABLE_LOCATOR = "xpath=//div[.='Detail - Invest Monthly']/following::table[1]";

	public final int DATE_COLUMN_NUM = 1;
	public final int INVESTMENT_COLUMN_NUM = 7;
	public final int INVESTMENT_IN_TOTAL_COLUMN_NUM = 8;
	public final int INITIAL_INVESTMENT_ROW_NUM = 1;

	public void checkWFPageOpened() {
		Assert.assertEquals(verifyElementIsExisted(this.PAGE_TITLE_LOCATOR), true);
	}

	public WebElement getDetailInvestMonthlyTable() {
		return this.findSeleniumElement(DETAIL_INVEST_MONTHLY_TABLE_LOCATOR);
	}

	public String getInvestmentColumnLocator() {
		return "td[" + this.INVESTMENT_COLUMN_NUM + "]";
	}

	public String getInvestmentInTotalColumnLocator() {
		return "td[" + this.INVESTMENT_IN_TOTAL_COLUMN_NUM + "]";
	}

	public int getNumOfRows(final WebElement table) {
		List<WebElement> rows = table.findElements(By.xpath(".//tr"));

		return rows.size();
	}

	public String getTableCell(final WebElement table, final int row, final int column) {
		WebElement rowElement = getTableRow(table, row);
		if (rowElement == null || rowElement.findElements(By.xpath("./td")).size() < column) {
			return null;
		}

		return getTableRow(table, row).findElement(By.xpath("./td[" + column + "]")).getText();

	}

	public WebElement getTableRow(final WebElement table, final int row) {
		return (getNumOfRows(table) != 0) ? table.findElements(By.xpath(".//tr")).get(row) : null;
	}

	public void inputSearchConditionsAndRefresh(String security, String startDate, String dollarPerDay)
			throws Throwable {
		inputValue(txtSecurity, security);
		inputValue(txtStartDate, startDate);
		inputValue(txtDollarPerDay, dollarPerDay);
		clickBtn(btnRefresh);
		waitPageToLoad();
	}

	public void openWealthFormulaPage() throws Throwable {
		openWealthFormulaURL();
		checkWFPageOpened();
		maximizeWindow();
	}

	private void openWealthFormulaURL() throws Throwable {
		getURL(FundPageTopology.getWealthFormulaPageUrl());
	}
}
