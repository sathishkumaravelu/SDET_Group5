package group5.Selenium.Salesforce.Task;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC001_CreateTask {
	
	
	@Test
	public void createTask() throws InterruptedException {
		
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
		
		//Navigating to the Task Page
		
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label='View All Applications']"))).click();
		driver.findElement(By.xpath("//input[@placeholder='Search apps or items...']")).sendKeys("task");
		driver.findElement(By.xpath("//p[@class='slds-truncate']")).click();
		
		//Creating a New Task
		
		driver.findElement(By.xpath("(//span[text()='Tasks']//following::lightning-icon[contains(@class,'slds-icon-utility-chevrondown')])[1]")).click();
		Thread.sleep(2000);
		WebElement newTask = driver.findElement(By.xpath("//span[text()='New Task']"));
		driver.executeScript("arguments[0].click();", newTask);
		driver.findElement(By.xpath("//input[@class='slds-combobox__input slds-input']")).sendKeys("Bootcamp");
		driver.findElement(By.xpath("(//a[@class='select'])[1]")).click();
		driver.findElement(By.xpath("//a[text()='Waiting on someone else']")).click();
		
		//Save and Verify
		
		driver.findElement(By.xpath("//button[@title='Save']")).click();
		Thread.sleep(2000);
		String text = driver.findElement(By.xpath("//span[contains(@class,'toastMessage')]")).getText();
		System.out.println(text);
		Assert.assertEquals(text, "Task \"Bootcamp\" was created.", "Task not created");
		driver.close();
	}

}
