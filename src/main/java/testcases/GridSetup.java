package testcases;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GridSetup {

	public static void main(String[] args) throws MalformedURLException {
		
		System.setProperty("webdriver.chrome.driver", "C:\\manohar_workspace\\Web-Mobile\\src\\main\\resources\\drivers\\chromedriver.exe");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setBrowserName("firefox");
		cap.setPlatform(Platform.WIN10);
		//WebDriver driver = new RemoteWebDriver(new URL(url),cap);
		
		WebDriver driver = new RemoteWebDriver(         

		        new URL("http://127.0.0.1:5566/wd/hub"),         

		        new ChromeOptions());   
		
		
		driver.get("https://google.com");

	}

}
