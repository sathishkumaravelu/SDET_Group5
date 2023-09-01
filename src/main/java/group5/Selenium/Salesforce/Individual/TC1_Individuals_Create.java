package group5.Selenium.Salesforce.Individual;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class TC1_Individuals_Create extends BaseSalesForce {
	
	@Test
	public void tc1() throws InterruptedException
	{
		wait = new WebDriverWait(driver,Duration.ofSeconds(15));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//div[contains(@class,'appLauncher slds-context-bar__icon')]//div[@class='tooltipTrigger tooltip-trigger uiTooltip']")).click();
		driver.findElement(By.xpath("//button[@aria-label='View All Applications']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Search apps or items...']")).sendKeys("individuals");
		WebElement individual = driver.findElement(By.xpath("//a[@data-label='Individuals']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", individual);
		//Thread.sleep(2000);
		//driver.findElement(By.xpath("//one-app-nav-bar-item-root//one-app-nav-bar-item-root/div/one-app-nav-bar-menu-button[contains(@class,'slds-dropdown-trigger slds-d')]")).click();
		//WebElement indiDropDown =  driver.findElement(By.xpath("//one-app-nav-bar-item-root//one-app-nav-bar-item-root/div/one-app-nav-bar-menu-button[contains(@class,'slds-dropdown-trigger slds-d')]"));
		//wait.until(ExpectedConditions.elementToBeClickable(indiDropDown)).click();
		driver.findElement(By.xpath("//div[@title='New']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Last Name']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys("Kumar123");
		driver.findElement(By.xpath("(//span[text()='Save'])[2]")).click();
		String text = driver.findElement(By.xpath("//span[contains(@class,'toastMessage slds-text-heading--')]")).getText();
		System.out.println(text);
		WebElement ele = driver.findElement(By.xpath("//one-app-nav-bar-item-root/a[@title='Kumar123']"));
		JavascriptExecutor js1 = (JavascriptExecutor)driver;
		js1.executeScript("arguments[0].click();", ele);
		//driver.findElement(By.xpath("//one-app-nav-bar-item-root/a[@title='Kumar123']")).click();
		String actualRes = driver.findElement(By.xpath("(//a[@title='Kumar123'])[2]")).getText();
		//String actualRes = driver.findElement(By.xpath("//span[text()='kumar123']")).getText();
		AssertJUnit.assertEquals(actualRes, "Kumar123");
	}

}
