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

public class TC002_EditCase {

	/*
	Test Steps:
		1. Login to https://login.salesforce.com
		2. Click on toggle menu button from the left corner
		3. Click view All and click Sales from App Launcher
		4. Click on Cases tab visible or select from more.
		5. Click on the Dropdown icon and select Edit from the case you created by reffering "case owner alias"
		6. Update Status as Working
		7. Update Priority to low
		8. Update Case Origin as Phone
		9. Update SLA violation to No
		10. Click on Save and Verify Status as Working
	 */
	
	public WebDriverWait wait;
	public ChromeDriver driver;
	

	
	@Test(dependsOnMethods = "group5.Selenium.Salesforce.Cases.TC001_CreateNewCase.tc001_CreateNewCase")
	public void tc002_EditCase() {
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
		driver.findElement(By.xpath("(//*[contains(@class,'rowActionsPlaceHolder')])[1]")).click();
		driver.findElement(By.xpath("(//a[@title='Edit'])[last()]")).click();
		driver.findElement(By.xpath("(//label[text()='Status']/following-sibling::div)[1]//button")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@title='Working']"))).click();		
		driver.findElement(By.xpath("(//label[text()='Priority']/following-sibling::div)[1]//button")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@title='Low']"))).click();		
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("(//label[text()='Case Origin']/following-sibling::div)[1]//button")));
		driver.findElement(By.xpath("(//label[text()='Case Origin']/following-sibling::div)[1]//button")).click();
		driver.findElement(By.xpath("//span[@title='Phone']")).click();		
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("(//label[text()='SLA Violation']/following-sibling::div)[1]//button")));	
		driver.findElement(By.xpath("(//label[text()='SLA Violation']/following-sibling::div)[1]//button")).click();
		driver.findElement(By.xpath("//span[@title='No']")).click();		
		driver.findElement(By.xpath("//*[@name='SaveEdit']")).click();	
		driver.findElement(By.xpath("//*[@name='refreshButton']")).click();
		String firstEleStatus = driver.findElement(By.xpath("((//td[4]/span)[1]//span)[1]")).getText();
		Assert.assertEquals(firstEleStatus,"Working");
		
	}
	
	@AfterTest
	public void afterSetup() {
		
		driver.close();
	}


}
