package group5.Selenium.Salesforce.WorkTypeGroup;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class TC003 {
	ChromeDriver driver;

	@Test (dependsOnMethods ={"group5.Selenium.Salesforce.WorkTypeGroup.TC001.CreateWork_Type_Group"}) 
	public void Delete_Type_Group() throws InterruptedException
	{
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		 driver=new ChromeDriver(options);  
		driver.manage().window().maximize(); 
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));   

		driver.get("https://login.salesforce.com/");	   
		driver.findElement(By.id("username")).sendKeys("ramyaseshan01@gmail.com");
		driver.findElement(By.id("password")).sendKeys("Appa@1948");	    
		driver.findElement(By.id("Login")).click();

		driver.findElement(By.xpath("//div[@role='navigation']")).click();
		
		WebElement ViewApp = driver.findElement(By.xpath("//button[@aria-label='View All Applications']"));
		wait.until(ExpectedConditions.elementToBeClickable(ViewApp));
		ViewApp.click();

		WebElement WT_Groups = driver.findElement(By.xpath("//p[text()='Work Type Groups']"));
		Actions act = new Actions(driver);
		act.scrollToElement(WT_Groups).perform();
		wait.until(ExpectedConditions.elementToBeClickable(WT_Groups));	
		WT_Groups.click();

		//Thread.sleep(3000);		
		driver.findElement(By.xpath("//div[@part='input-container']/input[@type='search']")).sendKeys("Ramya");
		driver.findElement(By.xpath("//div[@part='input-container']/input[@type='search']")).sendKeys(Keys.TAB);
		

		Thread.sleep(5000);
		//driver.findElement(By.xpath("//a[@title='Show 2 more actions']")).click();
		WebElement ActionBtn = driver.findElement(By.xpath("//a[contains(@class,'slds-button slds-button--icon-x-small slds-button--icon')]"));
		wait.until(ExpectedConditions.elementToBeClickable(ActionBtn));	
		ActionBtn.click();
		
		driver.findElement(By.xpath("//a[@title='Delete']")).click();
		driver.findElement(By.xpath("//span[text()='Delete']")).click();
		String text = driver.findElement(By.xpath("//span[text()='Salesforce Automation by Ramya']")).getText();
		System.out.println(text);


		String ConfirmationText = driver.findElement(By.xpath("//lightning-formatted-rich-text[@class='slds-rich-text-editor__output']/span")).getText();
		//span[text()='Salesforce Automation by Ramya']
		System.out.println(ConfirmationText); 

	}


	@AfterTest
	public void afterSetup() {		
		driver.close();
	}
}