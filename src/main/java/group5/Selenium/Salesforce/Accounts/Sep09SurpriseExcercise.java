package group5.Selenium.Salesforce.Accounts;

import java.time.Duration;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Sep09SurpriseExcercise {
	public String inputText = "Hem - First Automation";
	@Test
	public void test() {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
		driver.get("https://api-training.atlassian.net/");
		driver.manage().window().maximize();
		driver.findElement(By.id("username")).sendKeys("hari.radhakrishnan@testleaf.com");
		driver.findElement(By.xpath("//span[text()='Continue']")).click();
		driver.findElement(By.xpath("//form[@id='form-login']//input")).sendKeys("India@123");
		driver.findElement(By.xpath("//span[text()='Log in']")).click();
		driver.findElement(By.xpath("//button[@id='createGlobalItem']")).click();
		driver.findElement(By.id("summary-field")).sendKeys(inputText);
		driver.findElement(By.xpath("(//span[text()='Create'])[2]")).click();
		driver.findElement(By.xpath("//p[text()='SDET-5']")).click();
		driver.findElement(By.xpath("//span[text()='Backlog']")).click();
		driver.findElement(By.xpath("//input[@name='search']")).sendKeys(inputText);
		String testValidation = driver.findElement(By.xpath("//mark[text()='Hem - First Automation']")).getText();
		Assert.assertEquals(inputText, testValidation);
		driver.quit();
	}

}
