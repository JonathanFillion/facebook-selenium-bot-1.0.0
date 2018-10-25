package facebookCollector;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ToolsWindowControl {

	public static int getClientWindowHeigthSize(WebDriver driver) {
		WebElement html = driver.findElement(By.tagName("body"));
		int inner_height = Integer.parseInt(html.getAttribute("clientHeight"));
		return inner_height;
	}

	public static void clickList(List<WebElement> liste, WebDriver driver) {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		for (int i = 0; i < liste.size(); i++) {
			try {
				Thread.sleep(100);
				liste.get(i).click();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (StaleElementReferenceException e) {
			} catch (WebDriverException e) {
				liste.get(i).sendKeys("\n");
			}
		}
		delay(250);
	}

	public static void scrollUp(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -document.body.scrollHeight)");

	}

	public static boolean scrollDown(WebDriver driver) {
		int temp = getClientWindowHeigthSize(driver);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		try {
			Thread.sleep(350);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (temp == getClientWindowHeigthSize(driver))
			return false;
		else
			return true;
	}

	public static void scrollDownUntilBottom(WebDriver driver, int delayExtra) {
		try {
			Thread.sleep(2000);
			int temp = 0;
			for (;;) {
				Thread.sleep(650);
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
				if (temp == getClientWindowHeigthSize(driver))
					break;
				temp = getClientWindowHeigthSize(driver);
				// return;
			}
			Thread.sleep(700);
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

			Thread.sleep(1000 + delayExtra);
			if (temp == getClientWindowHeigthSize(driver))
				return;
			else
				scrollDownUntilBottom(driver, delayExtra);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void delay(int timeMills) {
		try {
			Thread.sleep(timeMills);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
