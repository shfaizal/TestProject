
package com.rbs.framework.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = { "classpath:featurefile/OrderItem.feature" }, 
glue = {
		"classpath:com.rbs.framework.stepdefinition",
		"classpath:com.rbs.framework.helper" }, plugin = { "pretty",
		"json:target/OrderItemFeatureRunner.json" })
public class OrderItemFeatureRunner extends AbstractTestNGCucumberTests {
}
