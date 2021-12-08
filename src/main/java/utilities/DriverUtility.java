package utilities;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.xml.XmlTest;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.TapOptions;

public class DriverUtility {
	static String browserName;
	static String platform;
	static String gridEnabled;
	static String remoteServerIP;
	static String remoteServerPort;
	protected static ThreadLocal<RemoteWebDriver> dockerDriver = new ThreadLocal<RemoteWebDriver>();
	public static WebDriver driver;
	static DesiredCapabilities cap;
	public static String baseDir = System.getProperty("user.dir");

	public static WebDriver openDriver(XmlTest Config) throws Exception {
		platform = Config.getParameter("platform").toLowerCase();
		if (platform.equalsIgnoreCase("web")) {
			gridEnabled = Config.getParameter("gridEnabled").toLowerCase();
			browserName = Config.getParameter("browser").toLowerCase();
			if ("false".equalsIgnoreCase(gridEnabled)) {
				if (browserName.equalsIgnoreCase("firefox")) {
					System.setProperty("webdriver.gecko.driver",
							baseDir + "\\src\\main\\resources\\drivers\\geckodriver.exe");
					driver = new FirefoxDriver();
				} else if (browserName.equalsIgnoreCase("chrome")) {
					System.setProperty("webdriver.chrome.driver",
							baseDir + "\\src\\main\\resources\\drivers\\chromedriver.exe");
					driver = new ChromeDriver();
				} else {
					throw new Exception("Browser parameter not given in property file");
				}
			} else {
				if (browserName.equalsIgnoreCase("firefox")) {
					if (Config.getParameter("grid_type").equalsIgnoreCase("docker")) {
						String remote_url_firefox = "http://localhost:4446/wd/hub";
						FirefoxOptions options = new FirefoxOptions();
						dockerDriver.set(new RemoteWebDriver(new URL(remote_url_firefox), options));
					} else if (Config.getParameter("grid_type").equalsIgnoreCase("linux")) {
						FirefoxOptions options = new FirefoxOptions();
						driver = new RemoteWebDriver(new URL("http://192.168.0.142:6666/wd/hub"), options);
					} else {
						FirefoxOptions firefoxOptions = new FirefoxOptions();
						driver = new RemoteWebDriver(new URL("http://localhost:5556/wd/hub"), firefoxOptions);
					}
				} else if (browserName.equalsIgnoreCase("chrome")) {
					if (Config.getParameter("grid_type").equalsIgnoreCase("docker")) {
						String remote_url_chrome = "http://localhost:4446/wd/hub";
						ChromeOptions options = new ChromeOptions();
						dockerDriver.set(new RemoteWebDriver(new URL(remote_url_chrome), options));
					} else if (Config.getParameter("grid_type").equalsIgnoreCase("linux")) {
						ChromeOptions options = new ChromeOptions();
						driver = new RemoteWebDriver(new URL("http://192.168.0.142:6666/wd/hub"), options);
					} else {
						ChromeOptions options = new ChromeOptions();
						driver = new RemoteWebDriver(new URL("http://localhost:5555/wd/hub"), options);

					}
				} else {
					throw new Exception("Browser parameter not given in property file");
				}
			}

		} else if (platform.equalsIgnoreCase("mobile")) {
			if (true) {
				DesiredCapabilities caps = new DesiredCapabilities();
				caps.setCapability("deviceName", "Manohar");
				caps.setCapability("udid", "3bb5e3ff"); // DeviceId from "adb
														// devices" command
				caps.setCapability("platformName", "Android");
				caps.setCapability("platformVersion", "11.0");
				caps.setCapability("app", baseDir + "\\src\\com.bigbasket.mobileapp_2021-09-09.apk");
				caps.setCapability("appPackage", "com.bigbasket.mobileapp");

				caps.setCapability("noReset", "true");

				MobileDriver<MobileElement> driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
				
				//****************** Below Extra code you can remove later *********************
				String time = driver.getDeviceTime();
				CharSequence cs = time.split("\\+")[0];
				LocalDateTime current = LocalDateTime.parse(cs);
				System.out.println(current);
				DateTimeFormatter entryDatesFormat = DateTimeFormatter.ofPattern("MMM d");
				/*HashMap<String, Object> params2 = new HashMap<String, Object>();
				params2.put("name", "Settings");
				driver.execute("mobile:application:open", params2);*/
				
				String m = current.format(entryDatesFormat);
				System.out.println(m);
				
				MobileElement addbtn = driver.findElement(By.xpath(""));
				
				TouchAction Action = new TouchAction((MobileDriver) driver);
				Action.tap(TapOptions.tapOptions()).perform();
				
				//****************** Above Extra code you can remove later *********************

			}

		} else {
			throw new Exception("Platform not mentioned in config file, please check and update");
		}
		return driver;
	}

	public static WebDriver getDriver() {
		return dockerDriver.get();
	}

	public void tearDownDriver() {
		getDriver().quit();
	}

	/*public static String takeSnapShot(String fileName) throws Exception {

		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		String scrBase64  = scrShot.getScreenshotAs(OutputType.BASE64);
		File SrcFile = OutputType.FILE.convertFromBase64Png(scrBase64);
		String filePath = baseDir + "\\ExtentReports\\Screenshots\\" + fileName + ".jpeg";
		File DestFile = new File(filePath);
		FileUtils.copyFile(SrcFile, DestFile);
		return filePath;
	}*/
}
