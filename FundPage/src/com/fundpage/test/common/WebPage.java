package com.fundpage.test.common;

import org.openqa.selenium.WebDriver;

import com.fundpage.test.util.IConfig;

public abstract class WebPage implements IConfig {

	private static final int TINY_TIMEOUT = 1 /* seconds */;
	private static final int PAUSE_TIMEOUT = 2 /* seconds */;
	private static final int SHORT_TIMEOUT = 10 /* seconds */;
	private static final int TIMEOUT = 30 /* seconds */;

	private static int getPauseTimeout() {
		return PAUSE_TIMEOUT;
	}

	public static void pause() {
		sleep(getPauseTimeout());
	}

	public static void sleep(final int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException ie) {
			// skip
		}
	}

	public WebDriver driver = null;

	public int getShortTimeout() {
		return SHORT_TIMEOUT;
	}

	public int getTimeout() {
		return TIMEOUT;
	}

	public int getTinyTimeout() {
		return TINY_TIMEOUT;
	}

}
