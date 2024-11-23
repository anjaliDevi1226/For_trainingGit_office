package com.seleniumeg_pomcom.seleniumeg_pom;

import org.openqa.selenium.By;
import org.openqa.selenium.*;

public class HomePage {

	private WebDriver driver;
	//Create Locators
	private By  aboutLink=By.id("aboutLink");
	private By  contactLink=By.id("contactLink");
	//subscribe form
	private By subscribeForm=By.id("subscribeForm");
	//email field
	private By email=By.id("email");
	//subscribe button
	private By subscribeButton=By.id("subscribeButton");
	//accept terms
	private By acceptTerms=By.id("acceptTerms");

	//constructor expects WebDriver as parameter

	public HomePage(WebDriver driver) {
		System.out.println("Displaying Home Page "+driver.getTitle());
		this.driver=driver;
	}
	//method to perform actions on above webElements

	public AboutPage gotoAboutPage() {
		WebElement aboutLinkElement=driver.findElement(aboutLink);
		aboutLinkElement.click();
		return new AboutPage(driver);
	}

	public ContactPage gotoContactPage() {
		WebElement contactLinkElement=driver.findElement(contactLink);
		contactLinkElement.click();
		return new ContactPage(driver);
	}

	//more action method :TBD

}
