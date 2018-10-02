package com.rbs.framework.helper.PageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.rbs.framework.settings.ObjectRepo;
import com.rbs.framework.utility.GenericHelper;



public class HomePage extends PageBase{

	private WebDriver driver;
	private final Logger log = com.rbs.framework.utility.LoggerHelper.getLogger(HomePage.class);
	public GenericHelper commonMethods;
	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		this.commonMethods = new GenericHelper(driver);
	}
	
	/* Locators specific to home page */
	
	@FindBy(how=How.ID,using="email")
	private WebElement userName;
	
	public WebElement getUserName(){
		return userName;
	}
	@FindBy(how=How.ID_OR_NAME,using="passwd")
	private WebElement password;
	
	public WebElement getPassword(){
		return password;
	}
	
	@FindBy(how=How.ID,using="SubmitLogin")
	private WebElement submit;
	
	public WebElement getSubmitBtn(){
		return submit;
	}
	
	@FindBy(how=How.CSS,using="#header > div.nav > div > div > nav > div.header_user_info > a")
	private WebElement userProfileLink;
	
	public WebElement getUserProfLink(){
		return userProfileLink;
	}
	
	@FindBy(how=How.XPATH,using="//*[@id='block_top_menu']/ul/li[3]/a")
	private WebElement tshirtLink;
	
	public WebElement getTshirtLink(){
		return tshirtLink;
	}
	
	@FindBy(how=How.XPATH,using="//a[contains(@class,'product-name')][contains(@title,'Faded Short Sleeve T-shirts')]")
	private WebElement tshirtImg;
	
	public WebElement getLoadedItem(){
		return tshirtImg;
	}
	
	public void userLogin() {
		log.info("Submit user details and login to website");
		commonMethods.waitForElement(getUserName(), ObjectRepo.reader.getExplicitWait());
		getUserName().sendKeys(ObjectRepo.reader.getUserName());
		getPassword().sendKeys(ObjectRepo.reader.getPassword());
		getSubmitBtn().click();
		log.info("user logged in sucessfully");
	}
	
	
	public CartPage clickOnTshirtOption(){
		log.info("Click on Tshirt menu on top of the page");
		commonMethods.waitForElement(tshirtLink, ObjectRepo.reader.getExplicitWait());
		tshirtLink.click();
		CartPage cart = new CartPage(driver);
		return cart;
	}

	
}
