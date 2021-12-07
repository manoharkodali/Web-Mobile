package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MainPage {
	public WebDriver driver;

	public MainPage(WebDriver webDriver) {
		this.driver = webDriver;
	}

	@FindBy(xpath = "//*[qa='bbLogo']")
	public WebElement logo;

	@FindBy(xpath = "//*[@qa='searchBar']")
	public WebElement searchTextBox;

	@FindBy(xpath = "//*[@qa='searchBtn']")
	public WebElement searchBtn;
	
	@FindBy(xpath = "//*[@qa='product_name']")
	public List<WebElement> products;
	
	@FindBy(xpath = "//*[@qa='product_name']/a") 
	public List<WebElement> prodElements;

	public void searchProduct(String product) {
		System.out.println("Entering the product as " + product + " in search text box");
		searchTextBox.sendKeys(product);
		searchBtn.click();

	}

}
