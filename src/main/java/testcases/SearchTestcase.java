package testcases;

import java.io.FileNotFoundException;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.HidesKeyboardWithKeyName;
import io.appium.java_client.MobileElement;
import sampletests.StaticTest;
import utilities.TestDataUtility;

public class SearchTestcase extends BaseTest{
	AppiumDriver<MobileElement> appiumdriver;
	HidesKeyboardWithKeyName hidesKeyboardWithKeyName;
	
	@Test(alwaysRun = true, groups = { "regression", "smoketest"})
	public void searchTest1() throws Exception{
		/*System.out.println(StaticTest.name = "Kodali");
		System.out.println("in search test ");
		Thread.sleep(2000);
		System.out.println(StaticTest.name);*/
		test = extent.createTest("searchTest1");
		test.log(Status.INFO, "Starting search test1");
		Map<String, Object> data = TestDataUtility.loadData("search.yml");
		test.log(Status.INFO, "Searching for Products in search test1");
		mainPage.searchProduct(data.get("product").toString());
		/*driver.findElements(By.xpath("man"));
		driver.findElement(By.xpath("man"));*/
		test.log(Status.PASS, "searchTest1 ==>> test passed");
		/*driver.switchTo().newWindow(WindowType.TAB);
		driver.navigate().to("https://www.google.com");
		driver.switchTo().newWindow(WindowType.WINDOW);
		driver.get("https://facebook.com");
		System.out.println();*/
	
		for(WebElement prodElemen : mainPage.products){
			System.out.println("Veg type==>>"+prodElemen.getText());
			WebDriverWait wait = new WebDriverWait(driver, 3);
			wait.until(ExpectedConditions.invisibilityOf(prodElemen));
		}
		
		for(WebElement prodElemen : mainPage.prodElements){
			System.out.println("Veg type==>>"+prodElemen.getText());
		}
	}
	
	@Test(alwaysRun = true)
	public void searchTest2() throws FileNotFoundException{
		System.out.println(StaticTest.name = "Kodali 1");
		System.out.println("in search test 1");
		test = extent.createTest("searchTest2");
		//Map<String, Object> data = TestDataUtility.loadData("search.yml");
		test.log(Status.INFO, "Starting searchTest2 test case");
		mainPage.searchProduct("laptop");
		Assert.fail("User failed");
		test.log(Status.PASS, "searchTest2 ==>> Searching for Product Laptop test passed");
		driver.getTitle();
			
	}

}
