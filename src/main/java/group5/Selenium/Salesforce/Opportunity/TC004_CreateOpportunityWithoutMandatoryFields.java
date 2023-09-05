package group5.Selenium.Salesforce.Opportunity;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC004_CreateOpportunityWithoutMandatoryFields {
	
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
		driver.findElement(By.xpath("//div[@title='New']")).click();
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
		driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
		List<WebElement> errors= driver.findElements(By.xpath("//ul[contains(@class,'errorsList')]//descendant::a"));
		System.out.println(errors.get(0).getText()+" "+errors.get(1).getText());
		Assert.assertTrue(errors.get(0).getText().equals("Opportunity Name"));
		Assert.assertTrue(errors.get(1).getText().equals("Stage"));
		driver.close();
	}

}
