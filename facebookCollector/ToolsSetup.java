package facebookCollector;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ToolsSetup {
	
	public void loginFacebook(WebDriver driver) {
		WebElement emailInput = driver.findElement(By.name("email"));
		WebElement passInput = driver.findElement(By.name("pass"));
		emailInput.sendKeys("fillion.jonathan@gmail.com");
		passInput.sendKeys("narsimcomir");
		passInput.submit();
		return;
	}
	
	public void getFacebookHomePage(WebDriver driver) {
		driver.get("https://www.facebook.com");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public WebDriver createDriver() {
		System.setProperty("webdriver.chrome.driver", "/usr/local/share/chromedriver");
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		WebDriver driver = new ChromeDriver(options);
		return driver;
	}
	
}
