package group5.Selenium.Salesforce.Individual;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC5_IndividualSorting extends BaseSalesForce
{
	@Test
	public void tc5sorting() throws InterruptedException
	{
	wait = new WebDriverWait(driver,Duration.ofSeconds(20));
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.findElement(By.xpath("//div[contains(@class,'appLauncher slds-context-bar__icon')]//div[@class='tooltipTrigger tooltip-trigger uiTooltip']")).click();
	driver.findElement(By.xpath("//button[@aria-label='View All Applications']")).click();
	driver.findElement(By.xpath("//input[@placeholder='Search apps or items...']")).sendKeys("individuals");
	WebElement individual = driver.findElement(By.xpath("//a[@data-label='Individuals']"));
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("arguments[0].click();", individual);
	
	WebElement sortName = driver.findElement(By.xpath("//a//span[@title='Name']"));
	
	/* Ele Click intercepted exception for sorting and to be resolved using javascript executor*/
	
	JavascriptExecutor js1 = (JavascriptExecutor) driver;
	js1.executeScript("arguments[0].click();", sortName);

	Thread.sleep(2000);
	List<WebElement> elements = driver.findElements(By.xpath("//table[@role='grid']//tr"));
	//elements.sort(elements.get(0)<?)
	System.out.println(elements.size());
	
	for (int i = 2; i < elements.size()-1; i++) 
	{
		
		
		  String text = driver.findElement(By.xpath("//table[@role='grid']//tr["+i+"]//th[1]")).getText();
		  String text1 = driver.findElement(By.xpath("//table[@role='grid']//tr["+(i+1)+"]//th[1]")).getText();
		  int count = 0;
		  int j=0;
		  //text.equalsIgnoreCase(text1)
		 
		  if (text.toLowerCase().charAt(j) <= text1.toLowerCase().charAt(j))
		  {
			  System.out.println(text);
			  continue;
		  }else
		  {
			  count++;
		  }
		 
		  System.out.println(count);
		  Assert.assertTrue((count==0), "Table not sorted");
		//char[] charArray = text.toCharArray();
	
	}	
	}
	
}
