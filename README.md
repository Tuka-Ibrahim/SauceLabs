
# SWAG Labs apk Test Automation 

Program to automate tests on SauceLabs Apk on Andriod devices Using Appium and by following the Page Object Model design pattern





![Logo](https://seeklogo.com/images/S/sauce-labs-logo-413E1BDE63-seeklogo.com.png)


## Installation Dependencies
- Java
- Maven
- Node JS

## Deployment

To deploy this project you have to install the following

```bash
// To install Latest appium version 2.xx
npm install -g appium@next
 
// To upgrade NPM version 
npm install -g npm@9.2.0

// download the drivers
appium driver install uiautomator2

// To Launch the server 
appium

// Download apk
https://github.com/saucelabs/sample-app-mobile/releases
```
## Test Casses Implemented Inside

- TC1: Login with Valid Email and password and validate that login is performed successfully
- TC2: Login with invalid email or password
- TC3: Add Any Item to the cart and validate that Title and price of the Item at Home page equals the item and price at the cart
- TC4: Validate Removing Items from the cart and validate that the cart is empty
- TC5: Online Ordering and complete the flow from adding element to cart till the checkout, Also Validate the price and success purchase.


## Tools and Frameworks Used
Due to a known issue with IntelliJ you need to edit your run configuration templates before running your tests by following these steps:
-  Java
-  TestNG
-  Appium
-  Appium Inspector
-  Allure Reporting Framework
- JSON to store test data

## N.B
Due to a known issue with IntelliJ you need to edit your run configuration templates before running your tests by following these steps:
- Open 'Edit Run/Debug Configurations' dialog > Edit Configurations... > Edit configuration templates...
- Select TestNG > Listeners > and add these listeners one by one:
com.shaft.tools.listeners.AlterSuiteListener, com.shaft.tools.listeners.SuiteListener, com.shaft.tools.listeners.InvokedMethodListener
