package com.seleniumeg_pomcom.seleniumeg_pom;
import static org.junit.jupiter.api.Assertions.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Duration;
import java.util.stream.Stream;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.excel.utils.ExcelReadUtils;
import com.excel.utils.ExcelWriteUtils;
//import com.seleniumEg_pomm.AboutPage;
//import com.seleniumEg_pomm.ContactPage;
//import com.seleniumEg_pomm.HomePage;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MainTestAppJUnitExcel {
	private  static WebDriver driver;
	private static WebDriverWait wait;
		
	@BeforeAll
	public static void setUp() {
		//set driver properly
				//set the path for the chromeDriver
				System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\Downloads\\chromedriver-win32\\chromedriver.exe");
				//creare a new instance of the chrome driver 
				
				driver = new ChromeDriver();
			
			//wait
				wait=new WebDriverWait(driver,Duration.ofSeconds(10));
				System.out.println("setUp....1");
				ExcelReadUtils.init();
		ExcelWriteUtils.init();
		
	}
	//test case
		//home page to About Page
		@Disabled
		//@Test
		public void testNavigateHomeAbout() {
			
			//load web page
			//open html page
		    driver.get("file:///C:\\Users\\Administrator\\eclipse-study\\com.seleniumEg_pomm\\src\\main\\resources\\Home.html");
			//create homepage instance
		 // create homepage instance
		 		HomePage homePage = new HomePage(driver);

		 		// on homepage click aboutlink
		 		AboutPage aboutPage = homePage.gotoAboutPage();
			
					
			//wait until about page is loaded
			wait.until(ExpectedConditions.titleContains("About"));
			
			//assert with page title
			
			assertTrue(driver.getTitle().contains("About"));
			
		}
		
		//Homepage to ContactPage
		@Disabled
		//@Test
		public void testNavigateHomeContact() {
			
			//load web page
			//open html page
		    driver.get("file:///C:\\Users\\Administrator\\eclipse-study\\com.seleniumEg_pomm\\src\\main\\resources\\Home.html");
			//create homepage instance
		 // create homepage instance
		 		HomePage homePage = new HomePage(driver);

		 		// on homepage click aboutlink
		 		ContactPage contactPage = homePage.gotoContactPage();
			
					
			//wait until about page is loaded
			wait.until(ExpectedConditions.titleContains("Contact"));
			
			//assert with page title
			
			assertTrue(driver.getTitle().contains("Contact"));
			
		}
		
		//test case Home->About->contact
		@Disabled
		//@Test
		public void testNavigateHomeAboutContact() {
			
			//load web page
			//open html page
		    driver.get("file:///C:\\Users\\Administrator\\eclipse-study\\com.seleniumEg_pomm\\src\\main\\resources\\Home.html");
			//create homepage instance
		 // create homepage instance
		 		HomePage homePage = new HomePage(driver);

		 		// on homepage click aboutlink
		 		AboutPage aboutPage = homePage.gotoAboutPage();
			
					
			//wait until about page is loaded
			wait.until(ExpectedConditions.titleContains("About"));
			//navigate from about to contact page
			ContactPage contactPage=aboutPage.gotoContactPage();
			
			wait.until(ExpectedConditions.titleContains("Contact"));
			//assert with page title
			
			assertTrue(driver.getTitle().contains("Contact"));
			
		}
	
	static Stream<Arguments> getContactFormData() {
		//invoke ExcelReadUtils method
		Stream<Arguments> contactdata = ExcelReadUtils.readContactFormData();
		return contactdata;
	}
	
	@ParameterizedTest
	@MethodSource("getContactFormData")
	public void CheckContactForm(String tcid,String name,String email,String details, String sucessmsg) throws Throwable{
		try {
		//Open the HTML page (replace with actual URL)
			driver.get("file:///C:\\Users\\Administrator\\eclipse-study\\com.seleniumEg_pomm\\src\\main\\resources\\Home.html");
		
		//create HomePage
		HomePage homePage = new HomePage(driver);

		ContactPage contactPage = homePage.gotoContactPage();
		//wait until About page is loaded
		wait.until(ExpectedConditions.titleContains("Contact"));
				
		//assert with page title
		assertTrue(driver.getTitle().contains("Contact"));

		contactPage.fillContactForm(name,email,details);
		Thread.sleep(2000);
		String message= contactPage.checkSubmission();
		assertTrue(message.contains(sucessmsg));//Mail Sent Successfully
		ExcelWriteUtils.writeTCResult(tcid, "PASSED", "");
		}catch(AssertionError ae) {
			//testcase failed
			ExcelWriteUtils.writeTCResult(tcid, "FAILED", ae.getMessage()+getStackTrace(ae));
			ae.printStackTrace();
			throw ae;
		
		}catch(Exception e) {
			ExcelWriteUtils.writeTCResult(tcid, "ERROR", e.getMessage()+getStackTrace(e));
			//testcase error
			e.printStackTrace();
			throw e;
		}

	}
	
	public static String getStackTrace(AssertionError e) {
	    StringWriter sw = new StringWriter();
	    PrintWriter pw = new PrintWriter(sw);
	    e.printStackTrace(pw);
	    return sw.toString();
	}
	
	public static String getStackTrace(Exception e) {
	    StringWriter sw = new StringWriter();
	    PrintWriter pw = new PrintWriter(sw);
	    e.printStackTrace(pw);
	    return sw.toString();
	}
	
	@AfterAll
	public static void tearDown() {
		ExcelWriteUtils.generateExcel();
		if(driver !=null) {
			driver.quit();
		}
	}

}
