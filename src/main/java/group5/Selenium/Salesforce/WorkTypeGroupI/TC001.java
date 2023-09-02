package group5.Selenium.Salesforce.WorkTypeGroupI;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class TC001 { 
	ChromeDriver driver;

	@Test
	public void CreateWork_Type_Group() 
	{
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		 driver=new ChromeDriver(options);  
		driver.manage().window().maximize();   
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));   
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
           
		//1. Login to https://login.salesforce.com
		driver.get("https://login.salesforce.com/");	   
		driver.findElement(By.id("username")).sendKeys("ramyaseshan01@gmail.com");
		driver.findElement(By.id("password")).sendKeys("Appa@1948");	    
		driver.findElement(By.id("Login")).click();

		//2. Click on the toggle menu button from the left corner
		WebElement appLaunch = driver.findElement(By.xpath("//div[@role='navigation']"));
		wait.until(ExpectedConditions.elementToBeClickable(appLaunch)).click();
		
		//3. Click View All and click Work Type Groups from App Launcher
		WebElement ViewApp = driver.findElement(By.xpath("//button[@aria-label='View All Applications']"));
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@aria-label='View All Applications']")));
		wait.until(ExpectedConditions.elementToBeClickable(ViewApp)).click();


		WebElement WT_Groups = driver.findElement(By.xpath("//p[text()='Work Type Groups']"));
		wait.until(ExpectedConditions.visibilityOf(WT_Groups));
	
		Actions act = new Actions(driver);
		act.scrollToElement(WT_Groups).perform();
		WT_Groups.click();

		//4. Click on the Dropdown icon in the Work Type Groups tab
		WebElement WorkTypeGrp = driver.findElement(By.xpath("//a[@title='Work Type Groups']/following-sibling::one-app-nav-bar-item-dropdown/div/one-app-nav-bar-menu-button"));
		wait.until(ExpectedConditions.elementToBeClickable(WorkTypeGrp)).click();
		//WorkTypeGrp.click();	

		//5. Click on New Work Type Group
		WebElement New_WT_Group = driver.findElement(By.xpath("//a[@role='menuitem']/span/span[text()='New Work Type Group']"));
		wait.until(ExpectedConditions.elementToBeClickable(New_WT_Group));
		driver.executeScript("arguments[0].click();", New_WT_Group);

		//6. Enter Work Type Group Name as 'Salesforce Automation by *Your Name*'
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("Salesforce Automation by Ramya");
		
		//7.Click save and verify Work Type Group Name
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		WebElement MsgText = driver.findElement(By.xpath("//div[contains(@title,'Salesforce Automation by Ramya')]"));	

		wait.until(ExpectedConditions.visibilityOf(MsgText)) ;
		String text = MsgText.getText();
		System.out.println(text);
		String Exp = "Salesforce Automation by Ramya";
		Assert.assertEquals(text, Exp);

	}
	
	/*
	 * @AfterTest public void afterSetup() { driver.close(); }
	 */

}
