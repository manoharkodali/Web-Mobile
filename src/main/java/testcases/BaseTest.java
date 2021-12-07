package testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.xml.XmlTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import pages.MainPage;
import utilities.DriverUtility;
import utilities.ExtentManager;

public class BaseTest {
	public WebDriver driver;
	MainPage mainPage;
	ExtentTest test;
	static ExtentReports extent = ExtentManager.createInstance();

	@BeforeMethod(alwaysRun = true, groups = { "regression", "smoketest" })
	public void beforeClass(XmlTest config) throws Exception {
		driver = DriverUtility.openDriver(config);
		if (config.getParameter("grid_type").equalsIgnoreCase("docker"))
			driver = DriverUtility.getDriver();
		mainPage = PageFactory.initElements(driver, MainPage.class);
		driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://bigbasket.com");
	}

	@AfterMethod(alwaysRun = true)
	public void getResult(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			// String sreenshotName = result.getName();
			// String currentDate = new
			// SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());
			// String fileName = sreenshotName+"_"+ currentDate;
			String base64Screenshot = "data:image/png;base64"
					+ ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			test.addScreenCaptureFromBase64String(base64Screenshot).getModel().getId();
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " Test case FAILED due to below issues:",
					ExtentColor.RED));
			test.fail(result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + "Test Case PASSED", ExtentColor.GREEN));
		} else {
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + "Test Case SKIPPED", ExtentColor.ORANGE));
			test.skip(result.getThrowable());
		}
		test.log(Status.INFO, "Quitting thr browser");
		driver.quit();
	}

	@AfterSuite(alwaysRun = true)
	public void tearDown() {
		extent.flush();
	}
}
