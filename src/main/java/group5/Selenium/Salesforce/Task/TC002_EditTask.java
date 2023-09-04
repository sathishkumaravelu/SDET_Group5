package group5.Selenium.Salesforce.Task;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC002_EditTask {
	
	@Test
	public void editTask() throws InterruptedException {
		
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
		
		// Editing task
		
		driver.findElement(By.xpath("//div[contains(@class,'forceListViewManagerDisplaySwitcher')]")).click();
		driver.findElement(By.xpath("//li[@title='Table']")).click();
		driver.findElement(By.xpath("//button[@title='Select a List View: Tasks']")).click();
		driver.findElement(By.xpath("//span[contains(@class,'virtualAutocompleteOptionText') and text()='Recently Viewed']")).click();
		//driver.findElement(By.xpath("//input[@class='slds-input']")).sendKeys("Bootcamp");
		Thread.sleep(3000);
		Actions act = new Actions(driver);
		act.sendKeys(Keys.ENTER);
		Thread.sleep(7000);
		List<WebElement> table = driver.findElements(By.xpath("//table/tbody/tr"));
		int rows = table.size(), targerRowNo = 0;
		for (int i = 1; i <= rows; i++) {
			if(driver.findElement(By.xpath("//table/tbody/tr["+i+"]/th//a")).getText().equals("Bootcamp")) {
				targerRowNo = i;
				break;
			}
		}
		driver.findElement(By.xpath("//table/tbody/tr["+targerRowNo+"]/td[7]//a")).click();
		
		WebElement edit = driver.findElement(By.xpath("//div[@title='Edit']"));
		driver.executeScript("arguments[0].click();", edit);
		//driver.findElement(By.xpath("//div[@title='Edit']")).click();
		String today = String.valueOf(Instant.now().atZone(ZoneId.systemDefault()).toLocalDate());
		System.out.println(today);
		driver.findElement(By.xpath("(//input[@class='slds-input'])[2]")).click();
		driver.findElement(By.xpath("//table//td[@data-value='"+today+"']")).click();
		driver.findElement(By.xpath("(//a[@class='select'])[2]")).click();
		driver.findElement(By.xpath("//a[text()='Low']")).click();
		
		//Save and Verify
		
		driver.findElement(By.xpath("//button[@title='Save']")).click();
		Thread.sleep(2000);
		String text = driver.findElement(By.xpath("//span[contains(@class,'toastMessage')]")).getText();
		System.out.println(text);
		Assert.assertEquals(text, "Task \"Bootcamp\" was saved.", "Task not edited");
		driver.close();
	}

}
