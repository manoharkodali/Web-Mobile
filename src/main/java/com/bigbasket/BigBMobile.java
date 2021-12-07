package com.bigbasket;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BigBMobile {
	
	public WebDriver driver;

	public BigBMobile(WebDriver webDriver) {
		this.driver = webDriver;
	}
	
	public void searchActivity() throws InterruptedException{
		Thread.sleep(10000);
		driver.findElement(By.id("com.bigbasket.mobileapp:id/homePageSearchBox")).sendKeys("fruits");
		Thread.sleep(10000);

	}

}
