package group5.Selenium.Salesforce.Accounts;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC004_Sorting {
	@Test
	public void sortTest() throws InterruptedException
	{
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
		driver.executeScript("arguments[0].click();", accountTab);
		List<WebElement> beforeSort = driver.findElements(By.xpath("//tbody/tr/th//a"));
		List<String> before = new ArrayList<String>();
		for (WebElement webElement : beforeSort) {
			before.add(webElement.getText());
		}
		Collections.sort(before);
		WebElement sort = driver.findElement(By.xpath("//span[@title='Account Name']"));
		driver.executeScript("arguments[0].click();", sort);
		Thread.sleep(2000);
		List<WebElement> afterSort = driver.findElements(By.xpath("//tbody/tr/th//a"));
		List<String> after = new ArrayList<String>();
		for (WebElement webElement : afterSort) {
			after.add(webElement.getText());
		}

		Assert.assertEquals(after, before);
		driver.quit();
	}

}
