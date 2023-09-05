package group5.Selenium.Salesforce.Opportunity;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC003_DeleteOpportunity {
	
	@Test
	public void test1() throws InterruptedException
	{
		ChromeOptions opt= new ChromeOptions();
		opt.addArguments("--disable-notifications");
		ChromeDriver driver= new ChromeDriver(opt);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		driver.get("https://login.salesforce.com");
		driver.findElement(By.id("username")).sendKeys("learners@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Leaf@1234");
		driver.findElement(By.id("Login")).click();
		driver.findElement(By.className("slds-icon-waffle")).click();
		WebElement viewAll=driver.findElement(By.xpath("//button[contains(text(),'View All')]"));
		wait.until(ExpectedConditions.elementToBeClickable(viewAll)).click();
		driver.findElement(By.xpath("//p[text()='Sales']")).click();
		WebElement opportunities= driver.findElement(By.xpath("//a[@title='Opportunities']"));
		driver.executeScript("arguments[0].click();", opportunities);
		driver.findElement(By.xpath("//input[@name='Opportunity-search-input']")).click();
		driver.findElement(By.xpath("//input[@name='Opportunity-search-input']")).sendKeys("Salesforce Automation by Benjamin");
		driver.findElement(By.xpath("//input[@name='Opportunity-search-input']")).sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//div[contains(@class,'forceListViewManagerHeader')]")).click();
		Thread.sleep(5000);
		WebElement dropDown=driver.findElement(By.xpath("//td[contains(@class,'slds-cell-edit cellContainer')][5]//descendant::span[2]"));
		driver.executeScript("arguments[0].click();", wait.until(ExpectedConditions.elementToBeClickable(dropDown)));
		WebElement edit=driver.findElement(By.xpath("//a[@data-target-selection-name='sfdc:StandardButton.Opportunity.Delete']"));
		driver.executeScript("arguments[0].click();", wait.until(ExpectedConditions.elementToBeClickable(edit)));
		driver.findElement(By.xpath("//button[@title='Delete']//child::span")).click();
		WebElement deleted=driver.findElement(By.xpath("//div[contains(@class,'emptyContent slds-is-absolute')]//descendant::span"));
		System.out.println(deleted.getText());
		Assert.assertTrue(deleted.getText().equals("No items to display."));
		driver.close();
	}

}
