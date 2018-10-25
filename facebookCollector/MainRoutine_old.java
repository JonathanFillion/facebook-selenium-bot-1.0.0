package facebookCollector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

public class MainRoutine {

	private WebDriver driver;
	JavascriptExecutor jse = (JavascriptExecutor) driver;
	int currentHead;
	String currentHeadUrl;
	int lastFoundProfileInteruption;
	private String[] interfaceProfile = { "nom", "lieu", "head", "url" };
	private String[] interfaceIndic = { "", "", "" };

	/*public MainRoutine() throws InterruptedException {
		driver = setupDriver();
		getFacebook();
		loginFacebook();
		getRoot();
		load();
		getLocationFromMain();
	}*/

	private void getProfileInfoFromMain() throws InterruptedException {
		driver.get("");
		Thread.sleep(200);

		WebElement location = driver.findElement(By.id("intro_container_id"));
		System.out.println(location.getText());
	}

	private void load() {
		driver.get(currentHeadUrl);
	}

	private void getHead() {
		if (SQLiteJDBCDriverConnection.getTableLength("head") == 0) {
			getRoot();
		} else {
			currentHead = SQLiteJDBCDriverConnection.getTableLength("head");
			currentHeadUrl = SQLiteJDBCDriverConnection.getUrlHead(currentHead);
			lastFoundProfileInteruption = SQLiteJDBCDriverConnection.getTotalInternalFollowers(currentHead);
		}
	}

	/*private void saveFriendList() throws InterruptedException {

		getFriendsTabFromMain();
		scrollDown();
		getAllNamesFromFriendList(lastFoundProfileInteruption);
	}
*/
	

	/*private void getAllNamesFromFriendList(int lastFoundProfileInteruption) {
		List<WebElement> liste = driver.findElements(By.className("uiProfileBlockContent"));
		// System.out.println(liste.size());
		System.out.println(liste.size());
		for (int i = lastFoundProfileInteruption; i < liste.size(); i++) {
			String nameData = liste.get(i).getText();
			String url = liste.get(i).findElement(By.tagName("a")).getAttribute("href");
			String[] nameDataSplit = nameData.split("\n");
			if (nameDataSplit.length == 2) {
				String[] data = { nameDataSplit[0], nameDataSplit[1], "" + currentHead, url };
				// SQLiteJDBCDriverConnection.insert("body", interfaceProfile, data);
			} else {

			}
		}
	}*/

	/*private int getClientWindowSize() {
		WebElement html = driver.findElement(By.tagName("body"));
		int inner_height = Integer.parseInt(html.getAttribute("clientHeight"));
		return inner_height;
	}*/

	/*private void scrollDown() throws InterruptedException {
		Thread.sleep(2000);
		int temp = 0;
		for (;;) {
			Thread.sleep(650);

			System.out.println(temp);
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
			System.out.println(getClientWindowSize());
			if (temp == getClientWindowSize())
				break;
			temp = getClientWindowSize();
		}
		Thread.sleep(700);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(1000);
		if (temp == getClientWindowSize())

			return;
		else
			scrollDown();
	}*/

	private void getRoot() {
		currentHeadUrl = "https://www.facebook.com/mazin.abduladhim";
		currentHead = 0;
		lastFoundProfileInteruption = 0;
	}

	/*private void getFacebook() {
		driver.get("https://www.facebook.com");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}*/

	/*private void getFriendsTabFromMain() {
		WebElement friends = driver.findElement(By.linkText("Friends"));
		friends.click();
	}*/

	/*private WebDriver setupDriver() {
		System.setProperty("webdriver.chrome.driver", "/usr/local/share/chromedriver");
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		WebDriver driver = new ChromeDriver(options);
		return driver;
	}
*/
	/*private void loginFacebook() {
		WebElement emailInput = driver.findElement(By.name("email"));
		WebElement passInput = driver.findElement(By.name("pass"));
		emailInput.sendKeys("fillion.jonathan@gmail.com");
		passInput.sendKeys("narsimcomir");
		passInput.submit();
		return;
	}*/
}