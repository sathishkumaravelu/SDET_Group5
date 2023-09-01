package group5.Selenium.Salesforce.Individual;

import org.testng.annotations.Test;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class TC2_Individuals extends BaseSalesForce {
	/*
	 * Test Steps:
		1. Login to https://login.salesforce.com
		2. Click on the toggle menu button from the left corner
		3. Click View All and click Individuals from App Launcher 
		4. Click on the Individuals tab 
		5. Search the Individuals 'Kumar'
		6. Click on the Dropdown icon and Select Edit
		7. Select Salutation as 'Mr'
		8. Enter the first name as 'Ganesh'.
		9. Click on Save and Verify the first name as 'Ganesh'
	 */
	//@Test (dependsOnMethods = {"restful_booker.CreateToken.createtoken","restful_booker.CreateBooking.createBooking"})
	//@Test (dependsOnMethods = "mandatoryCW.selenium.salesforceTCs.TC1_Individuals_Create.tc1")
	@Test
	public void tc2()
	{
		wait = new WebDriverWait(driver,Duration.ofSeconds(15));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//div[contains(@class,'appLauncher slds-context-bar__icon')]//div[@class='tooltipTrigger tooltip-trigger uiTooltip']")).click();
		driver.findElement(By.xpath("//button[@aria-label='View All Applications']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Search apps or items...']")).sendKeys("individuals");
		WebElement individual = driver.findElement(By.xpath("//a[@data-label='Individuals']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", individual);
		
		driver.findElement(By.xpath("//input[@placeholder='Search this list...']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Search this list...']")).sendKeys("kumar123");
		driver.findElement(By.xpath("//input[@placeholder='Search this list...']")).sendKeys(Keys.TAB);
		
		//WebElement dropD =  driver.findElement(By.xpath("//a[contains(@class,'rowActionsPlaceHolder slds-button slds-button--icon-x-small slds-button--icon-border-')]"));
		//wait.until(ExpectedConditions.visibilityOf(dropD)).click();
		driver.findElement(By.xpath("//a[contains(@class,'rowActionsPlaceHolder slds-button slds-button--icon-x-small slds-button--icon-border-')]")).click();
		driver.findElement(By.xpath("//a[@title='Edit']")).click();
		WebElement dropD1 = driver.findElement(By.xpath("(//a[@class='select'])[1]"));
		wait.until(ExpectedConditions.elementToBeClickable(dropD1)).click();
		
		driver.findElement(By.xpath("//a[@title='Mr.']")).click();
		
		driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys("Ganesh");
		driver.findElement(By.xpath("(//span[text()='Save'])[2]")).click();
		String text = driver.findElement(By.xpath("//span[contains(@class,'toastMessage slds-text-heading--')]")).getText();
		System.out.println(text);
		if (text.contains("Ganesh"))
		{
			System.out.println("pass");
		}else {
			System.out.println("fail");
		}
		
		
		
	}

}
