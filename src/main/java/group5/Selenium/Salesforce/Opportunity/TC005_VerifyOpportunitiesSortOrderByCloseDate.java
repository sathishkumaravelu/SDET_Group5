package group5.Selenium.Salesforce.Opportunity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
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

public class TC005_VerifyOpportunitiesSortOrderByCloseDate {
	
	@Test
	public void test1() throws InterruptedException, ParseException
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
		driver.findElement(By.xpath("//div[contains(@class,'forceListViewManagerDisplaySwitcher')]//descendant::lightning-primitive-icon[1]")).click();
		WebElement tableView=driver.findElement(By.xpath("//a[@role='menuitemcheckbox']//descendant::lightning-primitive-icon[1]"));
		wait.until(ExpectedConditions.elementToBeClickable(tableView)).click();
		Thread.sleep(5000);
		WebElement sort=driver.findElement(By.xpath("//th[@title='Close Date']//descendant::a"));
		driver.executeScript("arguments[0].click();", wait.until(ExpectedConditions.elementToBeClickable(sort)));
		Thread.sleep(5000);
		WebElement sort1= driver.findElement(By.xpath("//span[text()='Close Date']")); 
		driver.executeScript("arguments[0].click();", wait.until(ExpectedConditions.elementToBeClickable(sort1)));
		Thread.sleep(3000);
		List<WebElement> closingDate= driver.findElements(By.xpath("//span[contains(@class,'sfaOpportunityDealMotionCloseDate')]//child::span[1]"));
		int size=closingDate.size();
		SimpleDateFormat dFormat= new SimpleDateFormat("dd/MM/yyyy");
		Date date1;
		Date date2;
		for(int i=0;i<size-2;i++)
		{
			date1=dFormat.parse(closingDate.get(i).getText());
			date2=dFormat.parse(closingDate.get(i+1).getText());
			System.out.println(date1+" , "+date2);
			Assert.assertTrue(date1.before(date2)||date1.equals(date2));
		}
		driver.close();
	}

}
