package testcases.mobile;

import java.io.FileNotFoundException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.bigbasket.BigBMobile;

import testcases.BaseTest;

public class BigBasketTestcase extends BaseTest {
	BigBMobile bigBMobile;

	@BeforeMethod
	public void beforeMethod() throws FileNotFoundException {
		bigBMobile = PageFactory.initElements(driver, BigBMobile.class);
	}

	@Test
	public void bigBasketSearchTest() throws InterruptedException {
		System.out.println("in Mobile test case");
		bigBMobile.searchActivity();
	}

}
