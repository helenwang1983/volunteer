package com.fundpage.test.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.AssertJUnit;

import com.google.common.base.Function;

public class BasePage extends WebPage {

	public static enum LocatorType {
		ID("id"), NAME("name"), XPATH("xpath"), LINK_TEXT("linkText"), CLASS("class"),
		PARTIAL_LINK_TEXT("partialLinkText"), TAG_NAME("tagName");

		public static LocatorType toEnum(final String label) {
			for (LocatorType value : values()) {
				if (label.equalsIgnoreCase(value.label))
					return value;
			}
			return null;
		}

		public String label;

		LocatorType() {
			this.label = name();
		}

		LocatorType(final String label) {
			this.label = label;
		}

		public String getLabel() {
			return this.label;
		}
	}

	protected static By getBy(final String locator) {
		By by = null;
		if (locator != null) {
			String[] arrLocator = locator.split("=");
			LocatorType locatorTag = LocatorType.toEnum(arrLocator[0].trim());
			String objectLocator = locator.substring(locator.indexOf("=") + 1, locator.length());

			switch (locatorTag) {
			case ID:
				by = By.id(objectLocator);
				break;
			case NAME:
				by = By.name(objectLocator);
				break;
			case XPATH:
				by = By.xpath(objectLocator);
				break;
			case LINK_TEXT:
				by = By.linkText(objectLocator);
				break;
			case CLASS:
				by = By.className(objectLocator);
				break;
			case PARTIAL_LINK_TEXT:
				by = By.partialLinkText(objectLocator);
				break;
			case TAG_NAME:
				by = By.tagName(objectLocator);
				break;
			}
		}
		return by;
	}

	private StringBuffer verificationErrors = new StringBuffer();

	public void clickBtn(final String locator) {
		findSeleniumElement(locator).click();
	}

	public void closeBrowser() {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			Assert.fail(verificationErrorString);
		}
	}

	public List<WebElement> findList(final String locator) {
		return driver.findElements(getBy(locator));
	}

	public WebElement findSeleniumElement(final String locator) {
		List<WebElement> webElements = driver.findElements(getBy(locator));
		return (webElements.size() == 0) ? null : webElements.get(0);
	}

	public String getTextValue(final String locator) {
		return findSeleniumElement(locator).getText();
	}

	public void getURL(final String baseUrl) throws Exception {
		driver = (driver == null) ? new ChromeDriver() : driver;
		setImplicitWait();
		driver.get(baseUrl);
		waitPageToLoad();
	}

	public void inputValue(final String locator, final String value) {
		WebElement webElement = findSeleniumElement(locator);
		webElement.clear();
		webElement.sendKeys(Keys.CONTROL, "a");
		webElement.sendKeys(value);
		pause();
	}

	public void maximizeWindow() {
		driver.manage().window().maximize();
	}

	public void mouseHoverMenuAndClickSubMenu(final String menuLocator, final String submenuLocator)
			throws InterruptedException {
		WebElement settings = driver.findElement(getBy(menuLocator));
		Actions action = new Actions(driver);
		action.moveToElement(settings).perform();
		sleep(5);
		findSeleniumElement(submenuLocator).click();
		sleep(5);
	}

	public Properties readPropertyFile(String filePathname) throws IOException {
		InputStream input = new FileInputStream(filePathname);
		Properties prop = new Properties();

		// load a properties file
		prop.load(input);
		return prop;
	}

	public void setImplicitWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(getTimeout()));
	}

	public boolean verifyElementIsExisted(final String element) {
		return findSeleniumElement(element).isDisplayed();
	}

	public void verifyTextValue(final String xpath, final String expectedValue) {
		AssertJUnit.assertEquals(findSeleniumElement(xpath).getText(), expectedValue);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void waitPageToLoad() {
		(new FluentWait(driver).withTimeout(Duration.ofSeconds(getShortTimeout()))
				.pollingEvery(Duration.ofSeconds(getTinyTimeout()))).until(new Function<WebDriver, Boolean>() {
					public Boolean apply(WebDriver driver) {
						return ((JavascriptExecutor) driver).executeScript("return document.readyState")
								.equals("complete");
					}
				});
	}

}
