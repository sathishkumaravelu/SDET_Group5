package group5.Selenium.Salesforce.WorkTypeGroupI;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class TC005 {


	@Test
	public void CreateWork_Type_Group() throws InterruptedException 
	{
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		ChromeDriver driver=new ChromeDriver(options);  
		driver.manage().window().maximize();   
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));  
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		driver.get("https://login.salesforce.com/");	   
		driver.findElement(By.id("username")).sendKeys("ramyaseshan01@gmail.com");
		driver.findElement(By.id("password")).sendKeys("Appa@1948");	    
		driver.findElement(By.id("Login")).click();

		driver.findElement(By.xpath("//div[@role='navigation']")).click();
		driver.findElement(By.xpath("//button[@aria-label='View All Applications']")).click();

		WebElement WT_Groups = driver.findElement(By.xpath("//p[text()='Work Type Groups']"));
		wait.until(ExpectedConditions.visibilityOf(WT_Groups));
		Actions act = new Actions(driver);
		act.scrollToElement(WT_Groups).perform();
		WT_Groups.click();

		driver.findElement(By.xpath("(//a[@class='toggle slds-th__action slds-text-link--reset '])[1]")).click();

		//TBD	
		wait.until(ExpectedConditions.textToBe(By.xpath("//th[@title='Work Type Group Name']/div/span"), "Sorted Ascending"));
		String text = driver.findElement(By.xpath("//th[@title='Work Type Group Name']/div/span")).getText();
		System.out.println(text);
		String Exp = "Sorted Ascending";
		Assert.assertEquals(Exp, text);


	}
}
