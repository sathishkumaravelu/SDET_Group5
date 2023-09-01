package group5.Selenium.Salesforce.WorkTypeGroupI;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class TC004 {


	@Test
	public void CreateWork_Validate_Inline_Error() throws InterruptedException 
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

		driver.findElement(By.xpath("//div[@title='New']")).click();
		driver.findElement(By.xpath("//textarea[@class='slds-textarea']")).sendKeys("This is to chk...");	

		WebElement Work_Grp_Type = driver.findElement(By.xpath("//button[@role='combobox']"));
		driver.executeScript("arguments[0].click();", Work_Grp_Type);

		WebElement dd_Capacity = driver.findElement(By.xpath("//span[@title='Capacity']"));
		wait.until((ExpectedConditions.elementToBeClickable(dd_Capacity)));
		driver.executeScript("arguments[0].click();", dd_Capacity);
		//dd_Capacity.click();

		String Inline_Error  = driver.findElement(By.xpath("(//div[@role='alert'])[1] ")).getText();
		System.out.println(Inline_Error);

	}


}
