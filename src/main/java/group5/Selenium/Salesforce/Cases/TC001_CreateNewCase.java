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

public class TC001_CreateNewCase {

	/*
		Test Steps:
		1) Launch the app
		2) Click Login
		3) Login with the credentials
		4) Click on Global Actions SVG icon
		5) Click on New Case
		6) Choose Contact Name from the dropdown
		7)  Select Case origin as email
		8) Select status as Escalated
		9) Enter Subject as 'Testing' and description as 'Dummy'
		10) Click 'Save' and verify the message
	 */
	public WebDriverWait wait;
	public ChromeDriver driver;
	
	
	@Test
	public void tc001_CreateNewCase() {
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
		driver.findElement(By.xpath("(//label[text()='Case Origin']/following-sibling::div)[1]//button")).click();
		driver.findElement(By.xpath("//span[@title='Email']")).click();				
		driver.findElement(By.xpath("(//label[text()='Status']/following-sibling::div)[1]//button")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@title='Escalated']"))).click();				
		driver.findElement(By.xpath("//*[@name='Subject']")).sendKeys("Testing");
		driver.findElement(By.xpath("(//*[text()='Description']/following::textarea)[1]")).sendKeys("Dummy");
		driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
		String caseNumber = driver.findElement(By.xpath("//span[@data-aura-class='forceActionsText']//a")).getAttribute("title");
		String expectedNumber = driver.findElement(By.xpath("(//p[@title='Case Number']/following::p)[1]")).getText();		
		Assert.assertEquals(caseNumber, expectedNumber,"case number not created");
		
	}
	
	@AfterTest
	public void afterSetup() {
		
		driver.close();
	}

}
