package group5.Selenium.Salesforce.Dashboard;

import java.time.Duration;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class CreateNewDashboard {
		
	@Test
	public void createNewDashboard() throws InterruptedException {
		
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
		
		//Creating a New Dashboard
		
		driver.findElement(By.xpath("//div[text()='New Dashboard']")).click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='dashboard']")));
		driver.findElement(By.id("dashboardNameInput")).sendKeys("Salesforce Automation by Subha_101");
		driver.findElement(By.id("submitBtn")).click();
		
		//Saving the newly created dashboard and verifications.
		
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='dashboard']")));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		driver.switchTo().defaultContent();
		Thread.sleep(2000);
		String successMessage = driver.findElement(By.xpath("//span[contains(@class,'toastMessage')]")).getText();
		System.out.println("Actual message is: "+successMessage);
		Assert.assertTrue(successMessage.equals("Dashboard saved"), "Dashboard is Not Saved");
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='dashboard']")));
		driver.findElement(By.xpath("//button[text()='Done']")).click();
		String dashboardName = driver.findElement(By.xpath("(//div[@class='slds-page-header__name-title']//span)[2]")).getText();
		System.out.println("Name of the Dashboard id: "+dashboardName);
		Assert.assertTrue(dashboardName.equals("Salesforce Automation by Subha_101"), "Dashboard name not the same as entered");
		driver.close();
	}
}
