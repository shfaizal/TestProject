
package com.rbs.framework.helper.PageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PageBase{
	
	private final Logger log = com.rbs.framework.utility.LoggerHelper.getLogger(PageBase.class);
	private WebDriver driver;
	protected WebDriverWait explicitWait;
	
	/* Initializing the page objects */
	public PageBase(WebDriver driver) {
		if(driver == null)
			throw new IllegalArgumentException("Driver object is null");
		
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
		this.driver = driver;
		this.explicitWait = new WebDriverWait(driver, 30);
	}
	
	public boolean checkForTitle(String title){
		log.info(title);
		if(title == null || title.isEmpty())
			throw new IllegalArgumentException(title);
		return driver.getTitle().trim().contains(title);
	}
	
}
