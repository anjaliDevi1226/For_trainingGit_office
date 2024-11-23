package com.seleniumeg_pomcom.seleniumeg_pom;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainTestAppJUnit {
	private  static WebDriver driver;
	private static WebDriverWait wait;
		
	@BeforeAll
	public static void setUp() {
		//set driver property
		//load driver
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\Downloads\\chromedriver-win32\\chromedriver.exe");
		
		//create an instance of chrome driver
		driver = new ChromeDriver();
		
		//wait
		wait =new WebDriverWait(driver,Duration.ofSeconds(10));
		
	}
//testcases
	//Home page to About page
	@Test
	public void testNavigateHomeAbout() {
		//Open the HTML page (replace with actual URL)
		driver.get("File:///C:\\Users\\Administrator\\eclipse-workspace\\com.seleniumeg_pom\\src\\main\\resources\\Home.html");
		
		//create HomePage
		HomePage homePage = new HomePage(driver);

		//navigate to About page
		AboutPage aboutPage = homePage.gotoAboutPage();	
		
		//wait until About page is loaded
		wait.until(ExpectedConditions.titleContains("About"));
		
		//assert with page title
		assertTrue(driver.getTitle().contains("About"));

	}
	
	//Home Page to Contact Page
	@Test
	public void testNavigateHomeContact() {
		//Open the HTML page (replace with actual URL)
		driver.get("File:///C:\\Users\\Administrator\\eclipse-workspace\\com.seleniumeg_pom\\src\\main\\resources\\Home.html");
		
		//create HomePage
		HomePage homePage = new HomePage(driver);

		//navigate to Contact page
		ContactPage contactPage = homePage.gotoContactPage();	
		
		//wait until About page is loaded
		wait.until(ExpectedConditions.titleContains("Contact"));
		
		//assert with page title
		assertTrue(driver.getTitle().contains("Contact"));

	}
	
	//Test home to about, further to Contact page
	@Test
	public void testNavigateHomeAboutContact() {
		//Open the HTML page (replace with actual URL)
		driver.get("File:///C:\\Users\\Administrator\\eclipse-workspace\\com.seleniumeg_pom\\src\\main\\resources\\Home.html");
		
		//create HomePage
		HomePage homePage = new HomePage(driver);

		//navigate from Home to About page
		AboutPage aboutPage = homePage.gotoAboutPage();	
		
		//wait until About page is loaded
		wait.until(ExpectedConditions.titleContains("About"));
		
		//assert with page title
		assertTrue(driver.getTitle().contains("About"));
		
		//navigate from About to Contact page
		ContactPage contactPage = aboutPage.gotoContactPage();	
				
		//wait until About page is loaded
		wait.until(ExpectedConditions.titleContains("Contact"));
				
		//assert with page title
		assertTrue(driver.getTitle().contains("Contact"));

	}
	
	@Test
	public void CheckContactForm() {
		try {
		//Open the HTML page (replace with actual URL)
		driver.get("File:///C:\\Users\\Administrator\\eclipse-workspace\\com.seleniumeg_pom\\src\\main\\resources\\Home.html");
		
		//create HomePage
		HomePage homePage = new HomePage(driver);

		ContactPage contactPage = homePage.gotoContactPage();
		//wait until About page is loaded
		wait.until(ExpectedConditions.titleContains("Contact"));
				
		//assert with page title
		assertTrue(driver.getTitle().contains("Contact"));

		contactPage.fillContactForm("abcd", "abcd@gmail.com", "abcd added");
		Thread.sleep(2000);
		String message= contactPage.checkSubmission();
		assertTrue(message.contains("Mail Sent Successfully"));
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	@AfterAll
	public static void tearDown() {
		if(driver !=null) {
			driver.quit();
		}
	}

}
