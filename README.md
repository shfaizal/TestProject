# TestProject

### Selenium Framework with Cucumber

Adapted the base framework structure which was referred online to implement the requirement 


### Here is the basic code:

To use the class for handling the web component create the object and use it

```java
	GenericHelper common = new GenericHelper(driver);
	common.getElement(locator);
```

### Add the Feature file 

Add the feature file under `test\resources\featurefile`

```java
Feature: Adding a product to the Cart

  @chrome
  Scenario: Search Tshirt and add it to the cart
    Given : user is on the home page
    When : user clicks on the order Tshirt option
    Then : user should be able to navigate to cart
    
```

use the tag `@chrome` to launch the specific browser or no-tag to use the browser form the `config.properties` file

### Create the Runner

```java

package com.rbs.framework.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = { "classpath:featurefile/OrderItem.feature" }, glue = {
		"classpath:com.rbs.framework.stepdefinition",
		"classpath:com.rbs.framework.helper" }, plugin = { "pretty",
		"json:target/OrderItemFeatureRunner.json" })
public class OrderItemFeatureRunner extends AbstractTestNGCucumberTests {
}
``` 

### Use the testng.xml file to run the test cases

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite name="Suite">
	<listeners>
		<listener
			class-name="com.rbs.framework.listeners.reportlistener.CucumberReport" />
	</listeners>
	<test name="Test - 1">
		<classes>
			<class name="com.rbs.framework.runner.OrderItemFeatureRunner" />	
		</classes>
	</test>
</suite> 
```



### Cucumber Report

There is a feature overview page:

![feature overview page](https://github.com/damianszczepanik/cucumber-reporting/raw/master/.README/feature-overview.png)

