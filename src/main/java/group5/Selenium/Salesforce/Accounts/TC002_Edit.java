package group5.Selenium.Salesforce.Accounts;

import org.testng.annotations.Test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class TC002_Edit {
	
	@Test
	public void edit() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.navigate().to("https://login.salesforce.com");
		driver.findElement(By.id("username")).sendKeys("divyarbe@gmail.com",Keys.TAB,"Vithya@123",Keys.TAB,Keys.ENTER);

		WebElement launcher = driver.findElement(By.xpath("//div[@class='slds-icon-waffle']"));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(launcher));
		launcher.click();

		WebElement viewAll = driver.findElement(By.xpath("//button[normalize-space()='View All']"));
		viewAll.click();

		driver.findElement(By.xpath("//input[@placeholder='Search apps or items...']")).sendKeys("Sales");
		driver.findElement(By.xpath("(//mark[text()='Sales'])[3]")).click();
		WebElement accountTab = driver.findElement(By.xpath("//span[normalize-space()='Accounts']"));
		//JavascriptExecutor driver = (JavascriptExecutor) driver;
		driver.executeScript("arguments[0].click();", accountTab);

		driver.findElement(By.xpath("//a[normalize-space()='GokulAccount']/ancestor::tr/td[6]")).click();
		driver.findElement(By.xpath("//a[@title='Edit']")).click();
		WebElement type = driver.findElement(By.xpath("//label[normalize-space()='Type']/parent::lightning-combobox/div//button"));
		wait.until(ExpectedConditions.visibilityOf(type));
		driver.executeScript("arguments[0].click();", type);
		driver.findElement(By.xpath("//span[@title='Technology Partner']")).click();
		WebElement industryType = driver.findElement(By.xpath("//label[normalize-space()='Industry']/parent::lightning-combobox/div//button"));
		driver.executeScript("arguments[0].click();", industryType);

		WebElement hc = driver.findElement(By.xpath("//span[@title='Healthcare']"));
		driver.executeScript("arguments[0].scrollIntoView(true);", hc);
		hc.click();
		WebElement billAddress =driver.findElement(By.xpath("(//textarea[@name='street'])[1]"));
		billAddress.sendKeys("236",Keys.TAB,"sezimova",
				Keys.TAB,"14000",Keys.TAB,"prague",Keys.TAB,"CZ",Keys.TAB,"CZ",
				Keys.TAB,"14000",Keys.TAB,"paegue",Keys.TAB,"CZ");

		WebElement priority = driver.findElement(By.xpath("//label[normalize-space()='Customer Priority']/parent::lightning-combobox/div//button"));
		driver.executeScript("arguments[0].click();", priority);
		driver.findElement(By.xpath("//records-record-layout-item[@field-label='Customer Priority']//lightning-base-combobox-item[2]")).click();


		WebElement sla = driver.findElement(By.xpath("//label[normalize-space()='SLA']/parent::lightning-combobox/div//button"));
		driver.executeScript("arguments[0].click();", sla);
		driver.findElement(By.xpath("//records-record-layout-item[@field-label='SLA']//lightning-base-combobox-item[3]")).click();

		WebElement active = driver.findElement(By.xpath("//label[normalize-space()='Active']/parent::lightning-combobox/div//button"));
		driver.executeScript("arguments[0].click();", active);
		driver.findElement(By.xpath("//records-record-layout-item[@field-label='Active']//lightning-base-combobox-item[2]")).click();
		WebElement upsell =	driver.findElement(By.xpath("//label[normalize-space()='Upsell Opportunity']/parent::lightning-combobox/div//button"));
		driver.executeScript("arguments[0].click();", upsell);
		driver.findElement(By.xpath("//records-record-layout-item[@field-label='Upsell Opportunity']//lightning-base-combobox-item[3]")).click();
		driver.findElement(By.xpath("//input[@name='Phone']")).sendKeys("123456789");
		driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
		String num =driver.findElement(By.xpath("//tbody//tr//td[4]/span/span")).getText();
		System.out.println(num);
		Assert.assertEquals("123456789",num);
		driver.quit();
	}
	

}
