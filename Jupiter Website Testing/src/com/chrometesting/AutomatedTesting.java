package com.chrometesting;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.*;

public class AutomatedTesting {
	
	private static String url = "https://jupiter.cloud.planittesting.com/#/";
	private static String driverPath = "C:\\Users\\claud\\Selenium Files\\chromedriver_win32\\chromedriver.exe";
	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		launchBrowser();
		verifyWebsiteTitle();
		startShopping();
		Thread.sleep(2000);
		addProductsCart();
		viewCart();
		Thread.sleep(2000);
		removeItem();
		checkout();
		terminateBrowser();
	}
	
	public static void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", driverPath );
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
	}	
	
	public static void terminateBrowser() {
		driver.close();
	}
	
	public static void verifyWebsiteTitle() {
		String expectedTitle 	= "Jupiter Toys";
		String actualTitle		= driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
	}
	
	public static void startShopping() {
		driver.findElement(By.className("btn-success")).click();
		String currentUrl = driver.getCurrentUrl();
		String expectedUrl = "https://jupiter.cloud.planittesting.com/#/shop";
		Assert.assertEquals(currentUrl, expectedUrl);
	}
	
	public static void addProductsCart() {
		driver.findElement(By.className("btn-success")).click();
		String actualCountFirstP = driver.findElement(By.cssSelector("span[class='cart-count ng-binding']")).getText();
		Assert.assertEquals(actualCountFirstP, "1");
		driver.findElement(By.className("btn-success")).click();
		String actualCountSecondP = driver.findElement(By.cssSelector("span[class='cart-count ng-binding']")).getText();
		Assert.assertEquals(actualCountSecondP, "2");
	}
	
	public static void viewCart() {
		driver.findElement(By.cssSelector("span[class='cart-count ng-binding']")).click();
	}
	
	public static void removeItem() {
		driver.findElement(By.className("remove-item")).click();
		driver.findElement(By.xpath("/html/body/div[3]/div[3]/a[1]")).click();
		WebElement alert = driver.findElement(By.xpath("/html/body/div[2]/div/div/strong"));
		String actualText = alert.getText();
		String expectedText = "Your cart is empty";
		Assert.assertEquals(actualText, expectedText);
	}
	
	public static void checkout() {
		startShopping();
		addProductsCart();
		viewCart();
		driver.findElement(By.className("btn-checkout")).click();
	}
}
