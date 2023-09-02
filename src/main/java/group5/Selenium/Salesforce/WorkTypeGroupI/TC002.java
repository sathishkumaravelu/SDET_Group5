package group5.Selenium.Salesforce.WorkTypeGroupI;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class TC002 {
	ChromeDriver driver;

	@Test
	public void CreateWork_Type_Group() throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		//1. Login to https://login.salesforce.com
		driver.get("https://login.salesforce.com/");
		driver.findElement(By.id("username")).sendKeys("ramyaseshan01@gmail.com");
		driver.findElement(By.id("password")).sendKeys("Appa@1948");
		driver.findElement(By.id("Login")).click();

		//2. Click on the toggle menu button from the left corner
		WebElement appLaunch = driver.findElement(By.xpath("//div[@role='navigation']"));
		wait.until(ExpectedConditions.elementToBeClickable(appLaunch)).click();

		//3. Click View All and click Work Type Groups from App Launcher
		WebElement ViewApp = driver.findElement(By.xpath("//button[@aria-label='View All Applications']"));
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@aria-label='View All Applications']")));
		wait.until(ExpectedConditions.elementToBeClickable(ViewApp)).click();

		WebElement WT_Groups = driver.findElement(By.xpath("//p[text()='Work Type Groups']"));
		wait.until(ExpectedConditions.visibilityOf(WT_Groups));
		Actions act = new Actions(driver);
		act.scrollToElement(WT_Groups).perform();
		WT_Groups.click();

		//4. Click on the Work Type Group tab 
		WebElement wt_TabHeader = driver.findElement(By.xpath("//a[@href='/lightning/o/WorkTypeGroup/home']"));
		driver.executeScript("arguments[0].click();", wt_TabHeader);

		//5. Search the Work Type Group 'Salesforce Automation by *Your Name*'
		Thread.sleep(2000); // only this works
		driver.findElement(By.xpath("(//input[@type='search'])[2]")).sendKeys("Ramya" );
		driver.findElement(By.xpath("(//input[@type='search'])[2]")).sendKeys(Keys. TAB);


		//6. Click on the Dropdown icon and Select Edit
		WebElement ActionBtn = driver.findElement(
				By.xpath("//a[contains(@class,'slds-button slds-button--icon-x-small slds-button--icon')]"));
		wait.until(ExpectedConditions.elementToBeClickable(ActionBtn)).click();
		//ActionBtn.click();

		driver.findElement(By.xpath("//a[@title='Edit']")).click();

		//7.Enter Description as 'Automation'.
		driver.findElement(By.xpath("//input[@type='text']")).clear();
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("Automation");

		//8.Select Group Type as 'Capacity'
		WebElement Work_Grp_Type = driver.findElement(By.xpath("//button[@role='combobox']"));
		driver.executeScript("arguments[0].click();", Work_Grp_Type);
		WebElement capacity = driver.findElement(By.xpath("//span[@title='Capacity']"));
		driver.executeScript("arguments[0].click();", capacity);
		
		//9.Click on Save
		driver.findElement(By.xpath("//button[text()='Save']")).click();
	}

	/*
	 * @AfterTest public void afterSetup() { driver.close(); }
	 */
}
