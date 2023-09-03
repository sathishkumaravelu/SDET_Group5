package group5.Selenium.Salesforce.Dashboard;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DashboardSubscribe {
	
	@Test(dependsOnMethods = "group5.Selenium.Salesforce.Dashboard.EditDashboard.editDashboard")
	public void dashboardSubscribe() throws InterruptedException {
		
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
		
		//Search and subscribe dashboard
		
		driver.findElement(By.xpath("//input[contains(@class,'search-text-field')]")).sendKeys("Salesforce Automation by");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[contains(@class,'slds-align_absolute-center slds-grid_align-spread')]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Subscribe']")).click();
		driver.findElement(By.xpath("//span[@class='slds-radio--faux button-group-button-label' and text()='Daily']")).click();
		driver.findElement(By.xpath("//span[text()='Save']")).click();
		Thread.sleep(2000);
				
		// Verify if Subscribed in dashboard
		
		String successMessage = driver.findElement(By.xpath("//span[contains(@class,'toastMessage')]")).getText();
		System.out.println("Actual message is: "+successMessage);
		Assert.assertTrue(successMessage.equals("You started a dashboard subscription."), "Setting Subscription message failed");
		String actualStatus = driver.findElement(By.xpath("//div[@class='listViewContainer']//table/tbody/tr/td[5]//span[@class='slds-assistive-text']")).getText();
		Assert.assertTrue(actualStatus.equals("True"), "Not Subscribed");
		driver.close();
	}

}
