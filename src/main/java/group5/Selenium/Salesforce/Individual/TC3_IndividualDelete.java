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

public class TC3_IndividualDelete extends BaseSalesForce
{
	@Test
	public void tc3Delete() throws InterruptedException
	{
	wait = new WebDriverWait(driver,Duration.ofSeconds(20));
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.findElement(By.xpath("//div[contains(@class,'appLauncher slds-context-bar__icon')]//div[@class='tooltipTrigger tooltip-trigger uiTooltip']")).click();
	driver.findElement(By.xpath("//button[@aria-label='View All Applications']")).click();
	driver.findElement(By.xpath("//input[@placeholder='Search apps or items...']")).sendKeys("individuals");
	WebElement individual = driver.findElement(By.xpath("//a[@data-label='Individuals']"));
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("arguments[0].click();", individual);
	driver.findElement(By.xpath("//input[@placeholder='Search this list...']")).clear();
	driver.findElement(By.xpath("//input[@placeholder='Search this list...']")).sendKeys("Kumar");
	driver.findElement(By.xpath("//input[@placeholder='Search this list...']")).sendKeys(Keys.TAB);
	
	WebElement dDown = driver.findElement(By.xpath("(//span/div[contains(@class,'forceVirtualActionMarker')])[1]"));
	wait.until(ExpectedConditions.elementToBeClickable(dDown)).click();
	WebElement del = driver.findElement(By.xpath("//a[@title='Delete']"));
	JavascriptExecutor dD = (JavascriptExecutor) driver;
	dD.executeScript("arguments[0].click();", del);
	//wait.until(ExpectedConditions.visibilityOf(del)).click();
	driver.findElement(By.xpath("//span[text()='Delete']")).click();
	String text = driver.findElement(By.xpath("//span[contains(@class,'toastMessage slds-text-heading-')]")).getText();
	System.out.println(text);
	Assert.assertTrue(text.contains("was deleted"));
	//driver.findElement(By.xpath("//input[@placeholder='Search this list...']")).clear();
	//driver.findElement(By.xpath("//input[@placeholder='Search this list...']")).sendKeys("Kumar");
	//driver.findElement(By.xpath("//input[@placeholder='Search this list...']")).sendKeys(Keys.TAB);
	////span[text()='No items to display.']
	/*
	 * Thread.sleep(3000); WebElement result =
	 * driver.findElement(By.xpath("//span[text()='No items to display.']"));
	 * System.out.println(result);
	 * //wait.until(ExpectedConditions.visibilityOf(result))
	 * Assert.assertTrue(result.equals("No items to display."),
	 * "successfully deleted");
	 */
	
	}
	
}
