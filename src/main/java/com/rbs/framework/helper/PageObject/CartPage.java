package com.rbs.framework.helper.PageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.rbs.framework.settings.ObjectRepo;
import com.rbs.framework.utility.GenericHelper;

public class CartPage extends HomePage {
	private WebDriver driver;
	public GenericHelper commonmethods;
	private final Logger log = com.rbs.framework.utility.LoggerHelper.getLogger(CartPage.class);

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		this.commonmethods = new GenericHelper(driver);
	}

	/* Locators specific to cart page */
	@FindBy(how = How.XPATH, using = "//div/p[@id='add_to_cart']/button[@type='submit']/span[text()='Add to cart']")
	private WebElement addToCartBtn;

	public WebElement getAddToCart() {
		return addToCartBtn;
	}

	@FindBy(how = How.XPATH, using = "//div/a[contains(@title,'Proceed to checkout')]")
	private WebElement proceedToCheckOutBtn;

	public WebElement getProceedToCheckOut() {
		return proceedToCheckOutBtn;
	}

	@FindBy(how = How.XPATH, using = "//*[@id='center_column']/p[2]/a[1]/span")
	private WebElement summaryCheckout;

	public WebElement getSummaryCheckout() {
		return summaryCheckout;
	}

	@FindBy(how = How.XPATH, using = "//*[@id='center_column']/form/p/button/span")
	private WebElement address;

	public WebElement getAddress() {
		return address;
	}

	@FindBy(how = How.XPATH, using = "//*[@id='cgv']")
	private WebElement termsandCond;

	public WebElement getTermsCondCheckbox() {
		return termsandCond;
	}

	@FindBy(how = How.XPATH, using = "//*[@id='form']/p/button/span")
	private WebElement shippingCheckout;

	public WebElement getshippingCheckout() {
		return shippingCheckout;
	}

	@FindBy(how = How.XPATH, using = "//*[@id='HOOK_PAYMENT']/div[1]/div/p/a")
	private WebElement paymentByWireOption;

	public WebElement getPaymentByWire() {
		return paymentByWireOption;
	}

	@FindBy(how = How.CSS, using = "#cart_navigation > button")
	private WebElement confirmOrderLink;

	public WebElement getConfirmOrder() {
		return confirmOrderLink;
	}

	@FindBy(how = How.XPATH, using = "//p/a[@title='Back to orders']")
	private WebElement backToOrderLink;
	
	public void navigateToCart() {

		clickOnLoadedItems(driver, getLoadedItem());

	}

	public void purchaseFlow() {
		/* cart page flow */
	
		clickOnLoadedItems(driver, getAddToCart());
		try {
			commonmethods.switchToActiveElement();
			commonmethods.hardWait(3000);
			commonmethods.moveFocusToElementandClick(getProceedToCheckOut());
		} catch (ElementNotVisibleException ex) {
			System.out.println("no active element");
		} catch (Exception ex) {

		}

		commonmethods.moveFocusToElementandClick(getSummaryCheckout());
		commonmethods.moveFocusToElementandClick(getAddress());
		commonmethods.moveFocusToElementandClick(getTermsCondCheckbox());
		commonmethods.moveFocusToElementandClick(getshippingCheckout());
		commonmethods.moveFocusToElementandClick(getPaymentByWire());
		commonmethods.moveFocusToElementandClick(getConfirmOrder());
		commonmethods.executeScript("window.scrollBy(0,500)", "");

	}

	public void clickOnLoadedItems(WebDriver driver, WebElement element) {
		log.info("To click the dynamically loading elements");
		boolean reachedbottom = Boolean.parseBoolean(((JavascriptExecutor) driver)
				.executeScript("return $(document).height() == ($(window).height() + $(window).scrollTop());")
				.toString());

		while (!reachedbottom) {
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)", "");
			try {
				reachedbottom = Boolean.parseBoolean(((JavascriptExecutor) driver)
						.executeScript("return $(document).height() == ($(window).height() + $(window).scrollTop());")
						.toString());
				Wait<WebDriver> wait_element = new WebDriverWait(driver, ObjectRepo.reader.getExplicitWait());
				wait_element.until(ExpectedConditions.elementToBeClickable(element));
				element.click();
				log.info("Element is successfully clicked:" + element);
				break;
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	public void validateOrderReference(){
		log.info("To validate the generated order reference with Order history");
		String actualOrderRef = explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#center_column > div.box"))).getText();
		System.out.println(actualOrderRef);
		actualOrderRef = extractCaps(actualOrderRef); 
		commonmethods.moveFocusToElementandClick(backToOrderLink);
		String orderReference = explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='block-history']/table/tbody/tr/td[1]/a"))).getText();
		
		Assert.assertEquals(orderReference, actualOrderRef);
	}

	private String extractCaps(String actualOrderRef) {
		String output = "";
		actualOrderRef = actualOrderRef.substring(actualOrderRef.indexOf("reference"),actualOrderRef.indexOf("in"));
		for(int i=0;i<actualOrderRef.length();i++){
			char c = actualOrderRef.charAt(i);
			output += Character.isUpperCase(c) ? c : "";
		}
		return output;
	}
}