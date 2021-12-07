package testcases;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.openqa.selenium.Capabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;

public class TestCase2 extends BaseTest {
	public static AppiumDriver<MobileElement> iosDriver;

	public static void main(String args[]) throws FileNotFoundException, Exception {

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy_HH:mm-ss");
		System.out.println("Manohar");
		Date date = new Date();
		System.out.println(formatter.format(date));
		String deviceTime = iosDriver.getDeviceTime();
		
	}
}
