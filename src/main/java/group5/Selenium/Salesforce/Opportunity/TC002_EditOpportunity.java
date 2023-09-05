package group5.Selenium.Salesforce.Opportunity;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC002_EditOpportunity {
	
	@Test
	public void test1() throws InterruptedException
	{
		ChromeOptions opt= new ChromeOptions();
		opt.addArguments("--disable-notifications");
		ChromeDriver driver= new ChromeDriver(opt);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		driver.get("https://login.salesforce.com");
		driver.findElement(By.id("username")).sendKeys("learners@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Leaf@1234");
		driver.findElement(By.id("Login")).click();
		driver.findElement(By.className("slds-icon-waffle")).click();
		WebElement viewAll=driver.findElement(By.xpath("//button[contains(text(),'View All')]"));
		wait.until(ExpectedConditions.elementToBeClickable(viewAll)).click();
		driver.findElement(By.xpath("//p[text()='Sales']")).click();
		WebElement opportunities= driver.findElement(By.xpath("//a[@title='Opportunities']"));
		driver.executeScript("arguments[0].click();", opportunities);
		driver.findElement(By.xpath("//input[@name='Opportunity-search-input']")).click();
		driver.findElement(By.xpath("//input[@name='Opportunity-search-input']")).sendKeys("Salesforce Automation by Benjamin");
		driver.findElement(By.xpath("//input[@name='Opportunity-search-input']")).sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//div[contains(@class,'forceListViewManagerHeader')]")).click();
		Thread.sleep(5000);
		WebElement dropDown=driver.findElement(By.xpath("//td[contains(@class,'slds-cell-edit cellContainer')][5]//descendant::span[2]"));
		driver.executeScript("arguments[0].click();", wait.until(ExpectedConditions.elementToBeClickable(dropDown)));
		WebElement edit=driver.findElement(By.xpath("//a[@data-target-selection-name='sfdc:StandardButton.Opportunity.Edit']"));
		driver.executeScript("arguments[0].click();", wait.until(ExpectedConditions.elementToBeClickable(edit)));

	
		WebElement closeDate = driver.findElement(By.xpath("//input[@name='CloseDate']"));
		closeDate.clear();
		closeDate.click();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		Calendar calender = Calendar.getInstance();
		System.out.println(Calendar.getInstance());
		calender.setTime(date);
		calender.add(Calendar.DAY_OF_YEAR, 1);
		String date1 = dateFormat.format(calender.getTime());
		closeDate.sendKeys(date1);
		driver.findElement(By.xpath("//label[contains(text(),'Stage')]//following-sibling::div")).click();
		WebElement stage=driver.findElement(By.xpath("//div[contains(@class,'slds-listbox slds-listbox_vertical slds-dropdown')]//child::lightning-base-combobox-item[7]"));
		wait.until(ExpectedConditions.elementToBeClickable(stage)).click();		
		Thread.sleep(3000);
		WebElement scroll = driver.findElement(By.xpath("//a[@title='Delivery Status']"));
		Actions act = new Actions(driver);
		act.scrollToElement(scroll).perform();
		WebElement delivery=driver.findElement(By.xpath("//records-record-layout-item[@field-label='Delivery/Installation Status']//descendant::button"));
		wait.until(ExpectedConditions.elementToBeClickable(delivery)).click();	
		WebElement DeliveryValue=driver.findElement(By.xpath("//div[contains(@class,'slds-dropdown slds-dropdown_fluid slds-dropdown_left slds-dropdown_length-with-icon-7')]//descendant::span[6]"));
		wait.until(ExpectedConditions.elementToBeClickable(DeliveryValue)).click();
		driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Opportunity.Description']//descendant::textarea")).sendKeys("SalesForce");
		driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
		Thread.sleep(2000);
		WebElement check=driver.findElement(By.xpath("//table[@data-aura-class='uiVirtualDataTable']//descendant::td[5]//child::span//child::span[1]"));
		System.out.println(check.getText());
		Assert.assertTrue(check.getText().equals("Perception Analysis"));
		driver.close();
		
	}
	

}
