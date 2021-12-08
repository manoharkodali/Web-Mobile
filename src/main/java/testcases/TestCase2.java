package testcases;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class TestCase2 extends BaseTest {
	public static AppiumDriver<MobileElement> iosDriver;

	public static void main(String args[]) throws FileNotFoundException, Exception {

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy_HH:mm-ss");
		System.out.println("Manohar");
		Date date = new Date();
		System.out.println(formatter.format(date));
		
	}
}
