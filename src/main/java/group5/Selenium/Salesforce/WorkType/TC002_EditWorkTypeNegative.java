package group5.Selenium.Salesforce.WorkType;

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

public class TC002_EditWorkTypeNegative {

	/*
	 
	Test Steps:
		1) Launch the app
		2) Click Login
		3) Login with the credentials
		4) Click on the App Laucher Icon left to Setup
		5) Click on View All
		6) Click on Work Types
		7) Click on the Arrow button at the end of the first result
		8) Click on Edit
		9) Enter Time Frame Start as '9'
		10) Enter Time Frame End as '6'
		11) Click on Save
		12) Verify the error message
		
	 */
	
	public WebDriverWait wait;
	public ChromeDriver driver;

	@Test(dependsOnMethods = "group5.Selenium.Salesforce.WorkType.TC001_NewWorkType.tc001_NewWorkType")
	public void tc002_EditWorkTypeNegative() {
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
		WebElement searchEle = driver.findElement(By.xpath("//input[@part='input']"));
		wait.until(ExpectedConditions.visibilityOf(searchEle));
		searchEle.sendKeys("Work Types");
		driver.findElement(By.xpath("//mark[text()='Work Types']")).click();
		driver.findElement(By.xpath("(//*[contains(@class,'rowActionsPlaceHolder')])[1]")).click();
		driver.findElement(By.xpath("(//a[@title='Edit'])[last()]")).click();		
		driver.findElement(By.xpath("(//span[text()='Timeframe Start']/following::input)[1]")).sendKeys("9");
		driver.findElement(By.xpath("(//span[text()='Timeframe End']/following::input)[1]")).sendKeys("6");		
		driver.findElement(By.xpath("//button[@title='Save']")).click();
		boolean isDisplayed = driver.findElement(By.xpath("//div[@class='genericNotification']/span")).isDisplayed();
		Assert.assertEquals(isDisplayed, true);
		
	}
	@AfterTest
	public void afterSetup() {
		
		driver.close();
	}

}
