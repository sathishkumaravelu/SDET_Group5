package group5.Selenium.Salesforce.LegalEntities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TC001_Create_Legal_Entity {

	public static void main(String[] args) {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		//options.addArguments("--headless");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(4000));
		driver.findElement(By.id("username")).sendKeys("learners@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Leaf@1234");
		driver.findElement(By.id("Login")).click();
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();//clicking waffle icon
		driver.findElement(By.xpath("(//button[@class='slds-button'])[2]")).click();//clicking "View all" button
		driver.findElement(By.xpath("//input[@class='slds-input']")).sendKeys("legal");//Clicking the text box and entering search keyword
		driver.findElement(By.xpath("(//p[@class='slds-truncate'])[2]")).click();//clicking on the Legal Entity ( 02 index)
		driver.findElement(By.xpath("//div[@title='New']")).click();//Clicking in the new button
		driver.findElement(By.xpath("(//input[@class='slds-input'])[2]")).sendKeys("Salesforce Automation by Hem Prasad");//entering the name of the person working on the automation
		driver.findElement(By.xpath("//button[@class='slds-button slds-button_brand']")).click();//clicking on save button
		String actualtext = driver.findElement(By.xpath("//lightning-formatted-text[text()=\"Salesforce Automation by Hem Prasad\"]")).getText(); //getting the text that is entered
		//below is for validating whether the entered text is saved sucessfully
		if(actualtext.equals("Salesforce Automation by Hem Prasad"))
		{
			System.out.println("Test Case Passed & the legal entry is created sucessfully");
		}

		else
			System.out.println("Test Case failed");
		
	driver.quit();
		
	}

}
