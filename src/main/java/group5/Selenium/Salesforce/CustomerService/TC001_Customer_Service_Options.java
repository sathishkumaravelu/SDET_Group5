package group5.Selenium.Salesforce.CustomerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import io.github.sukgu.Shadow;

public class TC001_Customer_Service_Options extends CustomerService_BaseClass{

	@Test
	public void tc_001_Customer_Service_Options() {
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[text()='Mobile Publisher']"))));
		driver.findElement(By.xpath("//span[text()='Mobile Publisher']")).isDisplayed();
		String parentWindow = driver.getWindowHandle();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Learn More']"))).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> listWindows = new ArrayList<String>(windowHandles);
		driver.switchTo().window(listWindows.get(1));
		driver.findElement(By.xpath("//button[text()='Confirm']")).click();
		//driver.findElement(By.xpath("")).click();
		System.out.println(driver.getTitle());
		Shadow dom=new Shadow(driver);
		
		dom.findElementByXPath("//span[text()='Products']").click();
		//WebElement serviceEle = dom.findElementByXPath("//span[text()='Service']");
		Actions act = new Actions(driver);
		wait.until(ExpectedConditions.visibilityOf(dom.findElementByXPath("//span[text()='Service']")));
		act.moveToElement(dom.findElementByXPath("//span[text()='Service']")).build().perform();
		//act.moveToElement(serviceEle).perform();
		String text = dom.findElementByXPath("//*[text()='Service Cloud']").getText();
		System.out.println(text);
		
	}
	
	
	
}
