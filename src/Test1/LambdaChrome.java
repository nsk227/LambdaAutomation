package Test1;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.junit.Assert;

public class LambdaChrome {
	String username = "nkhadka";
	String accesskey = "BeiP3sJl6pQydQrBPWBIxc24ho5wEzQCpZneQfuP4ZTJbG9rrN";
	static RemoteWebDriver driver = null;
	String gridURL = "@hub.lambdatest.com/wd/hub";
	boolean status = false;

	public static void main(String[] args) {
		new LambdaChrome().test();


	}
	public void test() {
		// To Setup driver
		setUp();

		try {
			//Change it to production page
			driver.get("https://lambdatest.com");
			driver.manage().window().maximize() ;

			String PageTitle = driver.getTitle();
			String FinalTitle = "Next-Generation Mobile Apps and Cross Browser Testing Cloud" ;

			driver.get("https://lambdatest.com/" );

			driver.manage().window().maximize() ;

			FinalTitle = driver.getTitle();

			if (PageTitle.contentEquals(FinalTitle))
			{
				System.out.println( "Page Title Passed") ;
			}
			else {
				System.out.println( "Page Title Failed" );
			}

			WebElement popup = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/div/span[1]"));
			popup.click();
			System.out.println("Popup Close");

			WebElement link = driver.findElement(By.xpath("/html/body/div[1]/div[1]/section[8]/div/div/div/div/a")); 
			Actions actions = new Actions(driver); actions.moveToElement(link).click().perform();




			System.out.println("Open Link to new Page");

			String actualUrl = "https://www.lambdatest.com/integrations";
			String testUrl = driver.getCurrentUrl();

			Assert.assertEquals(testUrl,actualUrl);

			System.out.println("Acutal URL matches with Test URL");

			if (actualUrl.contentEquals(testUrl))
			{
				System.out.println( "URL Validation Passed") ;
			}
			else {
				System.out.println( "URL Validation Failed" );
			}

			System.out.println(testUrl);
			System.out.println("Test Execution Complete");

			driver.close();
			driver.quit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			tearDown();
		}

	}

	private void setUp() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browserName", "Chrome");
		capabilities.setCapability("browserVersion", "86.0");
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("username", "nkhadka");
		ltOptions.put("accessKey", "BeiP3sJl6pQydQrBPWBIxc24ho5wEzQCpZneQfuP4ZTJbG9rrN");
		ltOptions.put("platformName", "Windows 10");
		ltOptions.put("project", "LambdaTest");
		capabilities.setCapability("LT:Options", ltOptions);

		try {
			driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities);
		} catch (MalformedURLException e) {
			System.out.println("Invalid grid URL");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	private void tearDown() {
		if (driver != null) {
			((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
			driver.quit(); //really important statement for preventing your test execution from a timeout.
		}
	}
}
