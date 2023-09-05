package group5.Selenium.Salesforce.Opportunity;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC001_CreateNewOpportunity {

	@Test
	public void test1() throws InterruptedException {

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
		driver.findElement(By.xpath("//div[@title='New']")).click();
		driver.findElement(By.xpath("//label[contains(text(),'Opportunity')]//following-sibling::div")).click();
		driver.findElement(By.xpath("//label[contains(text(),'Opportunity')]//following-sibling::div")).sendKeys("Salesforce Automation by Benjamin D");
		WebElement closeDate = driver.findElement(By.xpath("//input[@name='CloseDate']"));
		closeDate.click();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		Calendar calender = Calendar.getInstance();
		System.out.println(Calendar.getInstance());
		calender.setTime(date);
		String date1 = dateFormat.format(calender.getTime());
		closeDate.sendKeys(date1);
		driver.findElement(By.xpath("//label[contains(text(),'Stage')]//following-sibling::div")).click();
		WebElement stage=driver.findElement(By.xpath("//div[@data-target-selection-name='sfdc:RecordField.Opportunity.StageName']//descendant::lightning-base-combobox-item[4]"));
		wait.until(ExpectedConditions.elementToBeClickable(stage)).click();
		driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
		//Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//slot[contains(@class,'slds-page-header__title')]//child::lightning-formatted-text")).getText().equals("Salesforce Automation by Benjamin D"));
		driver.close();
		

	}

}
