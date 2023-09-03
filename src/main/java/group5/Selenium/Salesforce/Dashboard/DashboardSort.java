package group5.Selenium.Salesforce.Dashboard;

import java.text.Collator;
import java.time.Duration;
import java.util.Arrays;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class DashboardSort {
	
	@Test(dependsOnMethods = "group5.Selenium.Salesforce.Dashboard.DashboardSubscribe.dashboardSubscribe")
	public void dashboardSort() {
		
		//Setting Chrome Options to to disable browser level notifications
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		
		//Log in to salesforce
		
		driver.get("https://login.salesforce.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("username")).sendKeys("subha216@gmail.com");
		driver.findElement(By.id("password")).sendKeys("Sales@17431");
		driver.findElement(By.id("Login")).click();
		
		//Navigating to the Dashboard Page
		
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		driver.findElement(By.xpath("//button[@aria-label='View All Applications']")).click();
		JavascriptExecutor jsExecute = (JavascriptExecutor) driver;
		WebElement dashBoard = driver.findElement(By.xpath("//p[text()='Dashboards']"));
		jsExecute.executeScript("arguments[0].scrollIntoView(true);", dashBoard);
		dashBoard.click();
		
		//Getting the Dashboard Names before sorting and then creating a sorted array of dashboard names to compare after
		
		int dashboardNamesCount = driver.findElements(By.xpath("//div[@class='listViewContainer']//table/tbody/tr/th")).size();
		String[] dashboardNames = new String[dashboardNamesCount];
		for (int i = 0; i < dashboardNamesCount; i++) {
			dashboardNames[i] = driver.findElement(By.xpath("//div[@class='listViewContainer']//table/tbody/tr["+(i+1)+"]/th//a")).getText();
		}
		System.out.println(Arrays.toString(dashboardNames));
		Arrays.sort(dashboardNames, Collator.getInstance());
		System.out.println(Arrays.toString(dashboardNames));
		
		//Getting the sorted Dashboard Names after they have been sorted in Salesforce.
		
		driver.findElement(By.xpath("//span[text()='Dashboard Name']")).click();
		int sortedDashboardNamesCount = driver.findElements(By.xpath("//div[@class='listViewContainer']//table/tbody/tr/th")).size();
		String[] sortedDashboardNames = new String[sortedDashboardNamesCount];
		for (int i = 0; i < dashboardNamesCount; i++) {
			sortedDashboardNames[i] = driver.findElement(By.xpath("//div[@class='listViewContainer']//table/tbody/tr["+(i+1)+"]/th//a")).getText();
		}
		System.out.println(Arrays.toString(sortedDashboardNames));
		
		//Compare and Close
		
		Assert.assertTrue(Arrays.equals(dashboardNames, sortedDashboardNames), "The table is not sorted by Dashboard Names");
		driver.close();		
	}

}
