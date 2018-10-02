@SanityTest
Feature: Purchase order flow in automation practice site	

Background: User is logged in to home page
Given User logins to automation practice website

@chrome
Scenario: Validation of Ordered item in the order history
When user clicks on Order T-shirts link on home page
And click on Quick view and navigate to Cart page
When user completes the purchase order
Then user should be able to view order reference in the order history