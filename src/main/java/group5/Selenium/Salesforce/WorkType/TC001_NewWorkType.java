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

public class TC001_NewWorkType {

	/*
		Test Steps:
		1) Launch the app
		2) Click Login
		3) Login with the credentials
		4) Click on the App Laucher Icon left to Setup
		5) Click on View All
		6) Click on Work Types
		7) Click on New
		8) Enter Work Type Name as 'Salesforce Project'
		9) Enter Description as 'Specimen'
		10) Create new operating hours by Entering a name as 'UK Shift'
		11) Enter Estimated Duration as '7'
		12) Click on Save
		13) Verify the Created message 
	 */
	
	public WebDriverWait wait;
	public ChromeDriver driver;

	
	@Test
	public void tc001_NewWorkType() {
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
		driver.findElement(By.xpath("//div[text()='New']")).click();
		//work type name field 
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("((//span[text()='Work Type Name'])[2]/following::input)[1]"))));
		driver.findElement(By.xpath("((//span[text()='Work Type Name'])[2]/following::input)[1]")).sendKeys("Salesforce Project");
		//description
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//span[text()='Description'])[last()]/following::textarea")))).sendKeys("Specimen");
		driver.findElement(By.xpath("(//span[text()='Operating Hours']/following::input)[1]")).sendKeys("UK Shift");
		driver.findElement(By.xpath("//div[@title='UK Shift']")).click();
		driver.findElement(By.xpath("((//span[text()='Estimated Duration'])[2]/following::input)[1]")).sendKeys("7");
		driver.findElement(By.xpath("//button[@title='Save']")).click();
		String confirmationText = driver.findElement(By.xpath("//span[@data-aura-class='forceActionsText']")).getText();
		System.out.println(confirmationText);
		Assert.assertEquals(confirmationText,"Work Type \"Salesforce Project\" was created." );
		
	}
	@AfterTest
	public void afterSetup() {
		
		driver.close();
	}

}
