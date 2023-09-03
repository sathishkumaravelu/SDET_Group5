package group5.Selenium.Salesforce.Dashboard;

import java.time.Duration;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class EditDashboard {
	
	
	@Test(dependsOnMethods = "group5.Selenium.Salesforce.Dashboard.CreateNewDashboard.createNewDashboard")
	public void editDashboard() throws InterruptedException {
		
		//Setting Chrome Options to to disable browser level notifications
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		
		//Log in to salesforce
		
		driver.get("https://login.salesforce.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("username")).sendKeys("subha216@gmail.com");
		driver.findElement(By.id("password")).sendKeys("Sales@17431");
		driver.findElement(By.id("Login")).click();
		
		//Navigating to the Dashboard Page
		
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		driver.findElement(By.xpath("//button[@aria-label='View All Applications']")).click();
		JavascriptExecutor jsExecute = (JavascriptExecutor) driver;
		WebElement dashBoard = driver.findElement(By.xpath("//p[text()='Dashboards']"));
		jsExecute.executeScript("arguments[0].scrollIntoView(true);", dashBoard);
		dashBoard.click();
		
		//Search and edit dashboard
		
		driver.findElement(By.xpath("//input[contains(@class,'search-text-field')]")).sendKeys("Salesforce Automation by");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[contains(@class,'slds-align_absolute-center slds-grid_align-spread')]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Edit']")).click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='dashboard']")));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[contains(@class,'editProperties')]")).click();
		driver.findElement(By.id("dashboardDescriptionInput")).sendKeys("SalesForce");
		driver.findElement(By.id("submitBtn")).click();
		
		//Save the edit and verify
		
		driver.findElement(By.xpath("//button[text()='Done']")).click();
		driver.findElement(By.xpath("(//button[text()='Save'])[2]")).click();
		String description = driver.findElement(By.xpath("//p[@class='slds-page-header__info']")).getText();
		System.out.println("Actual Description is: "+description);
		Assert.assertTrue(description.equals("SalesForce"), "Entered Description is not displayed");
		driver.close();
	}

}
