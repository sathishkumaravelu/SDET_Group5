package group5.Selenium.Salesforce.Cases;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class TC004_CreateCaseWithoutMandate {

	/*		
		Test Steps:
		1. Login to https://login.salesforce.com
		2. Click on toggle menu button from the left corner
		3. Click view All and click Sales from App Launcher
		4. Click on Cases tab 
		5) Click on New button
		6) Choose Contact Name from DropDown
		7) Select status as None
		8) Enter Subject as 'Testing' and description as 'Automation testing'
		9) Click 'Save'
		10)Get the text of Error message Displayed and Verify the message
		
	 */
	public WebDriverWait wait;
	public ChromeDriver driver;

	
	@Test(dependsOnMethods = "group5.Selenium.Salesforce.Cases.TC003_DeleteCase.tc003_DeleteCase")
	//@Test
	public void tc004_CreateNewCaseWithoutMandate() {
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--disable-notifications");
		driver = new ChromeDriver(op);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		driver.get("https://sdet3-dev-ed.develop.my.salesforce.com/");
		driver.findElement(By.id("username")).sendKeys("sathishsdet95@2023.com");
		driver.findElement(By.id("password")).sendKeys("iamgoingtobeSDET2023");
		driver.findElement(By.id("Login")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[text()='Setup Home']"))));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='slds-icon-waffle']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='View All Applications']"))).click();
		driver.findElement(By.xpath("//p[text()='Sales']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='More']")));
		WebElement accountsElement = driver.findElement(By.xpath("//span[text()='More']"));
		wait.until(ExpectedConditions.elementToBeClickable(accountsElement));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();",accountsElement);
		driver.findElement(By.xpath("(//*[@data-id='Case'])[last()]")).click();
		driver.findElement(By.xpath("//a[@title='New']")).click();
		driver.findElement(By.xpath("//*[@placeholder='Search Contacts...']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@title='Morkus tomas']"))).click();		
		driver.findElement(By.xpath("(//label[text()='Status']/following-sibling::div)[1]//button")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@title='--None--']"))).click();				
		driver.findElement(By.xpath("//*[@name='Subject']")).sendKeys("Testing");
		driver.findElement(By.xpath("(//*[text()='Description']/following::textarea)[1]")).sendKeys("Automation testing");	
		driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
		boolean displayed = driver.findElement(By.xpath("//*[text()='We hit a snag.']")).isDisplayed();
		Assert.assertEquals(displayed, true);
	
	}
	
	@AfterTest
	public void afterSetup() {
		
		driver.close();
	}


}
