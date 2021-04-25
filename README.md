# automation

The FlipkartAutomation project is using Selenium Webdriver with Java, Page Object Model Design Pattern with Page Factory, Maven as build tool, TestNG for annotations & executing the testcases,

This project has been divided into 3 source packages

1. src/main/java - 

It has the following packages - 

 a. com.util 
 This package has all the driver intialisation, wrapper functions like click,enterText etc.,report functions etc.
 It also has Test data generation functions & excel functions
 
 b. com.web.pages.flipkart
 It has all the page object model classes and functions, validations etc.

2. src/test/java 

It has the following package -

  a. com.web.locus.testcases
  This package has the test runner class with TestNG annotations.
  
3. src/main/resources

It has the following folder - 
 
 a. prod - prod.properties
 
Which contains url,other creds if required

Apart from this we have the following files - 

 * testngxml/flipkarttestng.xml - which helps us to execute the testcases
 * pom.xml - for adding up the dependencies
 * ExtentReports - for reports
 
 
