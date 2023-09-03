package group5.Selenium.Salesforce.LegalEntities;

import java.time.Duration;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Optional;


public class TC003_Create_Legal_Entity_without_mandatory_fields { 
	
	@Test

	public void test1() {
		
		ChromeOptions options = new ChromeOptions();

		ChromeDriver driver = new ChromeDriver(options);
		options.addArguments("--disable-notifications");
		
		driver.get("https://login.salesforce.com/");
		driver.findElement(By.id("username")).sendKeys("learners@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Leaf@1234");
		driver.findElement(By.id("Login")).click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(4000));
		

		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();// clicking waffle icon
		driver.findElement(By.xpath("//div[@class='slds-size_medium']//button")).click();// clicking "View all" button

		driver.findElement(By.xpath("(//input[@class='slds-input'])[2]")).sendKeys("legal");
		// Clicking the text box and entering search keyword

		driver.findElement(By.xpath("(//p[@class='slds-truncate'])[2]")).click();
		// clicking on the Legal Entity ( 02 index)

		WebElement automationName = driver.findElement(By.xpath("(//a[text()='Salesforce Automation by Hem Prasad'])[1]"));
		// clicking on the name to enter into the edit page
		
		JavascriptExecutor execute = (JavascriptExecutor) driver;
		execute.executeScript("arguments[0].click()", automationName);

		driver.findElement(By.xpath("(//button[@title='Edit Name'])[2]")).click();
		// click on edit option in front of the text box

		// driver.findElement(By.xpath("//li[@class='uiMenuItem']")).click();
		// clicking on edit button on the arrow mark

		driver.findElement(By.xpath("(//input[@class='slds-input'])[2]")).clear();
		// clearing the existing text

		driver.findElement(By.xpath("(//input[@class='slds-input'])[2]")).sendKeys("Testleaf007");
		// entering the text "Testleaf"

		driver.findElement(By.xpath("//button[text()=\"Save\"]")).click();
		// clicking on save button
		// below is for validating whether the entered text is saved successfully
		
		WebElement fetchResultText = driver.findElement(By.xpath("//a[@data-refid='recordId']"));
		JavascriptExecutor validateTest = (JavascriptExecutor) driver;
		validateTest.executeScript("arguments[0].click()", fetchResultText);
		//driver.findElement(By.xpath("(//button[@title='Edit Name'])[2]")).click();
		String actualValue = driver.findElement(By.xpath("//lightning-formatted-text[text()='Testleaf007']")).getText();
		if (actualValue.equals("Testleaf007")) {
			System.out.println("Test Case Passed & the legal entry is created sucessfully");
		}

		else
			System.out.println("Test Case failed");
		
		driver.quit();

	}
	}
