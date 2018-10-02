package com.rbs.framework.stepdefinition;

import com.rbs.framework.helper.PageObject.CartPage;
import com.rbs.framework.helper.PageObject.HomePage;
import com.rbs.framework.settings.ObjectRepo;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class OrderItemStepDfn {
	private HomePage hPage;
	private CartPage cart;
	
	@When("^user clicks on Order T-shirts link on home page$")
	public void clickOnTshirtOptionHomePage() {
		hPage = (HomePage) ObjectRepo.data.get("HomePage");
		cart = hPage.clickOnTshirtOption();
	}
	
	
	@And("^click on Quick view and navigate to Cart page$")
	public void clickOnQuickViewandNavigateToCart(){
		cart.navigateToCart();
	}
	
	@When("^user completes the purchase order$")
	public void userCompletesPurchaseOrder(){
		cart.purchaseFlow();
	}
	
	@Then("^user should be able to view order reference in the order history$")
	public void validateOrderHistory(){
		cart.validateOrderReference();
		
	}
}
