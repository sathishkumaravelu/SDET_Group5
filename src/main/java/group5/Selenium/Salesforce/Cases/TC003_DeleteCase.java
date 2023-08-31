package group5.Selenium.Salesforce.Cases;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC003_DeleteCase {

	/*
	Test Steps:
		1. Login to https://login.salesforce.com
		2. Click on toggle menu button from the left corner
		3. Click view All and click Sales from App Launcher
		4. Click on Cases tab 
		5. Click on the Dropdown icon and select Delete from the case you created by reffering "case owner alias"
		6. Verify the case with your name is deleted or not
	 */
	
	//@Test
	@Test(dependsOnMethods = "mandatoryHomeWork.selenium.Salesforce.TC002_EditCase.tc002_EditCase")
	public void tc003_DeleteCase() {
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(op);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		
		driver.get("https://sdet3-dev-ed.develop.my.salesforce.com/");
		driver.findElement(By.id("username")).sendKeys("sathishsdet95@2023.com");
		driver.findElement(By.id("password")).sendKeys("iamgoingtobeSDET2023");
		driver.findElement(By.id("Login")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='slds-icon-waffle']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='View All Applications']"))).click();
		driver.findElement(By.xpath("//p[text()='Sales']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='More']")));
		WebElement accountsElement = driver.findElement(By.xpath("//span[text()='More']"));
		wait.until(ExpectedConditions.elementToBeClickable(accountsElement));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();",accountsElement);
				
		driver.findElement(By.xpath("(//*[@data-id='Case'])[last()]")).click();
		String caseNo = driver.findElement(By.xpath("(//*[@data-aura-class='forceInlineEditCell'])[1]")).getText();
		System.out.println(caseNo);
		driver.findElement(By.xpath("(//*[contains(@class,'rowActionsPlaceHolder')])[1]")).click();
		driver.findElement(By.xpath("(//a[@title='Delete'])[last()]")).click();
		
		driver.findElement(By.xpath("//button[@title='Delete']")).click();
				
		driver.findElement(By.xpath("//input[@placeholder='Search this list...']")).sendKeys(caseNo,Keys.ENTER);
		
		driver.findElement(By.xpath("//*[@name='refreshButton']")).click();
		boolean displayed = driver.findElement(By.xpath("//span[text()='No items to display.']")).isDisplayed();
		
		Assert.assertEquals(displayed, true);
	}

}
