
package com.rbs.framework.configuration.browser;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.rbs.framework.utility.DateTimeHelper;
import com.rbs.framework.utility.ResourceHelper;


/** To get browser chrome driver instance */

public class ChromeBrowser {

	public Capabilities getChromeCapabilities() {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("start-maximized");
		DesiredCapabilities chrome = DesiredCapabilities.chrome();
		chrome.setJavascriptEnabled(true);
		chrome.setCapability(ChromeOptions.CAPABILITY, option);
		return chrome;
	}

	public WebDriver getChromeDriver(Capabilities cap) {
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver_win32\\chromedriver.exe");
		System.setProperty("webdriver.chrome.logfile",
				ResourceHelper.getResourcePath("logs/chromelogs/")
						+ "chromelog" + DateTimeHelper.getCurrentDateTime()
						+ ".log");
		return new ChromeDriver(cap);
	}
	
	

}
