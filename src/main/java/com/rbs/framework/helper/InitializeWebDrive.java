
package com.rbs.framework.helper;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.rbs.framework.configreader.PropertyFileReader;
import com.rbs.framework.configuration.browser.BrowserType;
import com.rbs.framework.configuration.browser.ChromeBrowser;
import com.rbs.framework.configuration.browser.FirefoxBrowser;
import com.rbs.framework.exception.NoSutiableDriverFoundException;
import com.rbs.framework.settings.ObjectRepo;
import com.rbs.framework.utility.GenericHelper;
import com.rbs.framework.utility.LoggerHelper;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;


public class InitializeWebDrive {

	private Logger oLog = LoggerHelper.getLogger(InitializeWebDrive.class);

	public InitializeWebDrive(PropertyFileReader reader) {
		ObjectRepo.reader = reader;
	}

	public WebDriver standAloneStepUp(BrowserType bType) throws Exception {
		try {
			oLog.info(bType);

			switch (bType) {

			case Chrome:
				ChromeBrowser chrome = ChromeBrowser.class.newInstance();
				return chrome.getChromeDriver(chrome.getChromeCapabilities());

			case Firefox:
				FirefoxBrowser firefox = FirefoxBrowser.class.newInstance();
				return firefox.getFirefoxDriver(firefox
						.getFirefoxCapabilities());


			default:
				throw new NoSutiableDriverFoundException(" Driver Not Found : "
						+ ObjectRepo.reader.getBrowser());
			}
		} catch (Exception e) {
			oLog.equals(e);
			throw e;
		}
	}
	
	@Before({"~@firefox","~@chrome"})
	public void before() throws Exception {
		setUpDriver(ObjectRepo.reader.getBrowser());
		oLog.info(ObjectRepo.reader.getBrowser());
	}

	@After({"~@firefox","~@chrome"})
	public void after(Scenario scenario) throws Exception {
		tearDownDriver(scenario);
		oLog.info("");
	}
	
	

	@Before(order=2,value={"@firefox"})
	public void beforeFirefox() throws Exception {
		setUpDriver(BrowserType.Firefox);
		oLog.info(BrowserType.Firefox);
	}

	@After(order=2,value={"@firefox"})
	public void afterFirefox(Scenario scenario) throws Exception {
		tearDownDriver(scenario);
		oLog.info("");
	}

	@Before(order=1,value={"@chrome"})
	public void beforeChrome() throws Exception {
		setUpDriver(BrowserType.Chrome);
		oLog.info(BrowserType.Chrome);
	}

	@After(order=1,value={"@chrome"})
	public void afterChrome(Scenario scenario) throws Exception {
		tearDownDriver(scenario);
		oLog.info("");
	}

	

	public void setUpDriver(BrowserType bType) throws Exception {
		ObjectRepo.driver = standAloneStepUp(bType);
		oLog.debug("InitializeWebDrive : " + ObjectRepo.driver.hashCode());
		ObjectRepo.driver
				.manage()
				.timeouts()
				.pageLoadTimeout(ObjectRepo.reader.getPageLoadTimeOut(),
						TimeUnit.SECONDS);
		ObjectRepo.driver
				.manage()
				.timeouts()
				.implicitlyWait(ObjectRepo.reader.getImplicitWait(),
						TimeUnit.SECONDS);
		ObjectRepo.driver.manage().window().maximize();

	}

	public void tearDownDriver(Scenario scenario) throws Exception {
		
		try {
			if (ObjectRepo.driver != null) {
				
				if(scenario.isFailed())
					scenario.write(new GenericHelper(ObjectRepo.driver).takeScreenShot(scenario.getName()));
				
				ObjectRepo.driver.quit();
				ObjectRepo.reader = null;
				ObjectRepo.driver = null;
				oLog.info("Shutting Down the driver");
			}
		} catch (Exception e) {
			oLog.error(e);
			throw e;
		}
	}

}
