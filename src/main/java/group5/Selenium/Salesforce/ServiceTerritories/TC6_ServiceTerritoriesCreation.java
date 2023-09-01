package group5.Selenium.Salesforce.ServiceTerritories;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC6_ServiceTerritoriesCreation  extends BaseSalesForce
{
		@Test
		public void tc6Serviceterr()
		{
			wait = new WebDriverWait(driver,Duration.ofSeconds(15));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.findElement(By.xpath("//div[contains(@class,'appLauncher slds-context-bar__icon')]//div[@class='tooltipTrigger tooltip-trigger uiTooltip']")).click();
			driver.findElement(By.xpath("//button[@aria-label='View All Applications']")).click();
			driver.findElement(By.xpath("//input[@placeholder='Search apps or items...']")).sendKeys("Service Territories");
			
			driver.findElement(By.xpath("//a[@data-label='Service Territories']//*//mark[contains(text(),'Territories')]")).click();
			WebElement newBut = driver.findElement(By.xpath("//div[@title='New']"));
			wait.until(ExpectedConditions.elementToBeClickable(newBut)).click();
			
			driver.findElement(By.xpath("//input[@name='Name']")).clear();
			driver.findElement(By.xpath("//input[@name='Name']")).sendKeys("SDET");
			
			driver.findElement(By.xpath("//input[@placeholder='Search Operating Hours...']")).click();
			WebElement timings = driver.findElement(By.xpath("//lightning-icon[@icon-name='standard:operating_hours']"));
			wait.until(ExpectedConditions.elementToBeClickable(timings)).click();
			
			driver.findElement(By.xpath("//input[@name='IsActive']")).click();
			driver.findElement(By.xpath("//input[@name='city']")).sendKeys("Chennai");
			
			driver.findElement(By.xpath("//input[@name='province']")).sendKeys("Tamilnadu");
			driver.findElement(By.xpath("//input[@name='country']")).sendKeys("India");
			
			driver.findElement(By.xpath("//input[@name='postalCode']")).sendKeys("600126");
			
			driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
			String text = driver.findElement(By.xpath("//span[contains(@class,'toastMessage slds-text-heading--')]")).getText();
			System.out.println(text);
			
			WebElement serviceLink = driver.findElement(By.xpath("//a[contains(@title,'Service Territory')]"));
			JavascriptExecutor je = (JavascriptExecutor) driver;
			je.executeScript("arguments[0].click();", serviceLink);
			
			WebElement element = driver.findElement(By.xpath("(//table[@role='grid']//*/a[@data-refid='recordId'])[1]"));
			Assert.assertEquals(element.getText(), "SDET");
		}

	
}
