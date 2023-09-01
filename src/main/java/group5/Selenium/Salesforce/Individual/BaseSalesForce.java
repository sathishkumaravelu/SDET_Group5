package group5.Selenium.Salesforce.Individual;

import java.sql.Driver;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseSalesForce {
	
	public static WebDriver driver;
	public static WebDriverWait wait;
	
	@BeforeMethod
	public void salesforceBase()
	{
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		
		driver.get("https://login.salesforce.com");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		driver.findElement(By.id("username")).sendKeys("nagarajanmeenakshisundaram@gmail.com");
		driver.findElement(By.id("password")).sendKeys("Allthebest1");
		driver.findElement(By.id("Login")).click();
		
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
		//driver.close();
	}

}
