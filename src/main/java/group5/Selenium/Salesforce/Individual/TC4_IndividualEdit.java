package group5.Selenium.Salesforce.Individual;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC4_IndividualEdit extends BaseSalesForce
{
	@Test
	public void tc4Edit() throws InterruptedException
	{
	wait = new WebDriverWait(driver,Duration.ofSeconds(20));
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.findElement(By.xpath("//div[contains(@class,'appLauncher slds-context-bar__icon')]//div[@class='tooltipTrigger tooltip-trigger uiTooltip']")).click();
	driver.findElement(By.xpath("//button[@aria-label='View All Applications']")).click();
	driver.findElement(By.xpath("//input[@placeholder='Search apps or items...']")).sendKeys("individuals");
	WebElement individual = driver.findElement(By.xpath("//a[@data-label='Individuals']"));
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("arguments[0].click();", individual);
	
	driver.findElement(By.xpath("//div[@title='New']")).click();
	WebElement selectdDown = driver.findElement(By.xpath("(//a[@class='select'])[1]"));
	selectdDown.click();
	WebElement salution = driver.findElement(By.xpath("//a[@title='Mr.']"));
	salution.click();
	
	driver.findElement(By.xpath("//input[@placeholder='First Name']")).clear();
	driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys("Ganesh");
	driver.findElement(By.xpath("(//span[text()='Save'])[2]")).click();
	
	String text = driver.findElement(By.xpath("//div[@role='alert']//ul/li")).getText();
	System.out.println(text);
	
	Assert.assertEquals(text, "These required fields must be completed: Last Name");
	
	}
	
}
