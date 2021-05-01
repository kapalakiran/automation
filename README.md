# Flipkart Automation

# Project Description

This project deals with automating the Flipkart Application's functionalities. 
Test Cases are added to verify search, verify filter and verify add to cart functionalities. 

# Project Description
Web Application testing done using : Selenium Webdriver
Programming Language used : Java
Design Pattern : Page Object Model with Page Factory
Build Management Tool : Mavane
Framework, Annotations and Execution : TestNG

# source packages

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
          
Other important files in the project are as follows- 

# testngxml/flipkarttestng.xml 
 
         It helps us to execute the testcases
          
# pom.xml
 
        It holds the dependencies required for the project execution
        
# ExtentReports
        
        It is the Open source reporting library used to depict the results of the test execution
 
 
