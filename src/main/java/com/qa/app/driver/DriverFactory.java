package com.qa.app.driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

	public WebDriver driver;
	public WebElement element;
	public Properties prop;
	public OptionsManager options;
	public static String highlight;
	public static ThreadLocal<WebDriver> thread = new ThreadLocal<WebDriver>();

	public WebDriver browserSetup(Properties prop) {

		options = new OptionsManager(prop);

		highlight = prop.getProperty("highlight");

		String browName = prop.getProperty("browser");

		switch (browName) {

		case "chrome":
			// driver = new ChromeDriver(options.getChromeOptions());
			thread.set(driver = new ChromeDriver(options.getChromeOptions()));
			break;

		case "firefox":
			// driver = new FirefoxDriver(options.getFirefoxOptions());
			thread.set(driver = new FirefoxDriver(options.getFirefoxOptions()));
			break;

		case "edge":
			// driver = new EdgeDriver(options.getEdgeOptions());
			thread.set(driver = new EdgeDriver(options.getEdgeOptions()));
			break;

		default:
			System.out.println("Please pass the correct browser");

		}

		getThreadLocal().manage().window().maximize();
		getThreadLocal().manage().deleteAllCookies();
		getThreadLocal().get(prop.getProperty("url"));

		return getThreadLocal();

	}

	public synchronized WebDriver getThreadLocal() {
		return thread.get();
	}

	public Properties setProp() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/Config File/Configuration.properties");
			prop.load(ip);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return prop;

	}

	public String takeScreenshot() {
		File src = ((TakesScreenshot) getThreadLocal()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/ScreenShots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
