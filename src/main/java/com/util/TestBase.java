package com.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public WebDriver driver;
	static String propertiesFile;
	Properties property = new  Properties();
	static {
		WebDriverManager.chromedriver().setup();
	}
	
	/**
	 * @author kirankumar 
	 * @throws IOException 
	 * @description - To create driver object and to launch the url
	 */
	@BeforeSuite(alwaysRun = true)
	@Parameters({"Environment"})
	public void setUp(String propFile,ITestContext ctx) throws IOException {
	    propertiesFile = propFile;
	    System.out.println(propertiesFile);
		driver = new ChromeDriver();
		driver.get(getProperty(propFile,"URL"));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	public String getProperty(String key) throws IOException{
		return getProperty(propertiesFile,key);
		
	}
	public String getProperty(String propetyfilepath,String key) throws IOException {
		      FileInputStream fis = null;
		      Properties prop = null;
		      try {
		         fis = new FileInputStream(propetyfilepath);
		         prop = new Properties();
		         prop.load(fis);
		      } catch(FileNotFoundException fnfe) {
		         fnfe.printStackTrace();
		      } catch(IOException ioe) {
		         ioe.printStackTrace();
		      } finally {
		         fis.close();
		      }
		return prop.getProperty(key);
	}
}
