package facebookCollector;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ToolsAbout {

	public String captureFromAboutTab(WebDriver driver) {
		WebElement ul = driver.findElement(By.cssSelector("ul[data-testid='info_section_left_nav']"));
		List<WebElement> li = ul.findElements(By.tagName("li"));
		String returnData = "";
		for (int i = 1; i < li.size(); i++) {
			WebElement feed = driver.findElement(By.id("u_fetchstream_1_0"));
			returnData += feed.getText();
			returnData += "\n";
			li.get(i).click();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("About Info done");
		return returnData;
	}


	public void getAboutTab(WebDriver driver) {
		WebElement buttonAbout = driver.findElement(By.partialLinkText("About"));
		buttonAbout.click();
	}

}
