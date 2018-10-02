
package com.rbs.framework.configreader;

import java.io.FileReader;
import java.util.Properties;

import org.apache.log4j.Level;

import com.rbs.framework.configuration.browser.BrowserType;
import com.rbs.framework.interfaces.IconfigReader;


/* To load properties from external file */

public class PropertyFileReader implements IconfigReader {
	
	private Properties prop = null;

	public PropertyFileReader() {
		prop = new Properties();
		try {
			FileReader reader=new FileReader("D:\\propertyfile\\config.properties");
			prop.load(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	public String getUserName() {
		return prop.getProperty("Username");
	}

	public String getPassword() {
		return prop.getProperty("Password");
	}

	public String getWebsite() {
		return prop.getProperty("Website");
	}

	public int getPageLoadTimeOut() {
		return Integer.parseInt(prop.getProperty("PageLoadTimeOut"));
	}

	public int getImplicitWait() {
		return Integer.parseInt(prop.getProperty("ImplcitWait"));
	}

	public int getExplicitWait() {
		return Integer.parseInt(prop.getProperty("ExplicitWait"));
	}
	
	

	public BrowserType getBrowser() {
		return BrowserType.valueOf(prop.getProperty("Browser"));
	}
	
	public Level getLoggerLevel() {
		
		switch (prop.getProperty("Logger.Level")) {
		
		case "DEBUG":
			return Level.DEBUG;
		case "INFO":
			return Level.INFO;
		case "WARN":
			return Level.WARN;
		case "ERROR":
			return Level.ERROR;
		case "FATAL":
			return Level.FATAL;
		}
		return Level.ALL;
	}

}
