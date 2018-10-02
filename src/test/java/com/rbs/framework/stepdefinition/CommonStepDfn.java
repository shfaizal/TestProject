package com.rbs.framework.stepdefinition;

import com.rbs.framework.helper.PageObject.HomePage;
import com.rbs.framework.settings.ObjectRepo;

import cucumber.api.java.en.Given;

public class CommonStepDfn {
	
	private HomePage hPage;
	
	@Given("^User logins to automation practice website$")
	public void User_logins_to_automation_practice_website(){
		ObjectRepo.driver.get(ObjectRepo.reader.getWebsite());
		hPage = new HomePage(ObjectRepo.driver);
		hPage.userLogin();
		ObjectRepo.data.put("HomePage", hPage);
	}
}
