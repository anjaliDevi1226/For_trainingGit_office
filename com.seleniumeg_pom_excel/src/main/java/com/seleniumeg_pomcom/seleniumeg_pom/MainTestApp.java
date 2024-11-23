package com.seleniumeg_pomcom.seleniumeg_pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MainTestApp {
public static void main(String[] args) throws Exception{
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\Downloads\\chromedriver-win32\\chromedriver.exe");
	
	//create an instance of chrome driver
	WebDriver driver = new ChromeDriver();
	
	//send get request for sample web page
	driver.get("File:///C:\\Users\\Administrator\\eclipse-workspace\\com.seleniumeg_pom\\src\\main\\resources\\Home.html");
	
	// create homepage instance
			HomePage homePage = new HomePage(driver);

			// on homepage click aboutlink
			AboutPage aboutPage = homePage.gotoAboutPage();

			Thread.sleep(3000);

			ContactPage contactPage = homePage.gotoContactPage();

			Thread.sleep(3000);

			aboutPage = contactPage.gotoAboutPage();
			Thread.sleep(2000);
			aboutPage.showMoreInfor();
			Thread.sleep(2000);
			aboutPage.validateShowMore();
			Thread.sleep(3000);
			
			// from above aboutlink goback to homepage
			aboutPage.gotoHomePage();
			Thread.sleep(2000);
			
			
			contactPage = aboutPage.gotoContactPage();
			Thread.sleep(3000);

			contactPage.fillContactForm("abcd", "abcd@gmail.com", "abcd added");
			Thread.sleep(3000);	
			contactPage.checkSubmission();
			Thread.sleep(3000);	
			
	
	
	driver.quit();
}
}
