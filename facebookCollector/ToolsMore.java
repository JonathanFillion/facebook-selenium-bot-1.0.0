package facebookCollector;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import dataStructures.Group;
import dataStructures.Like;

public class ToolsMore {

	public List<WebElement> getMoreTabMenu(WebDriver driver) {
		WebElement moreTab = driver.findElement(By.partialLinkText("More"));
		moreTab.click();
		List<WebElement> moreOptions = null;
		try {
			Thread.sleep(200);
			WebElement ul = driver.findElement(By.cssSelector("ul[role='menu']"));
			moreOptions = ul.findElements(By.tagName("li"));
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		return moreOptions;
	}

	public void getMoreFromMain(WebDriver driver) {
		List<WebElement> moreOptions = getMoreTabMenu(driver);

		ToolsWindowControl.delay(150);
		moreOptions.get(1).click();
		captureLikesFromMore(driver);
		moreOptions = getMoreTabMenu(driver);
		ToolsWindowControl.delay(150);
		moreOptions.get(3).click();
		captureGroupsFromMore(driver);
	}

	private ArrayList<Group> captureGroupsFromMore(WebDriver driver) {
		ArrayList<Group> groupes = new ArrayList<Group>();
		ToolsWindowControl.scrollDownUntilBottom(driver,0);
		ToolsWindowControl.scrollUp(driver);
		WebElement div = driver.findElement(By.cssSelector("div[role='tabpanel']"));
		List<WebElement> liste = div.findElements(By.tagName("li"));

		for (int i = 0; i < liste.size(); i++) {
			WebElement link = liste.get(i).findElement(By.tagName("a"));
			// System.out.println(link.getAttribute("href"));
			String[] data = liste.get(i).getText().split("\n");
			// System.out.println(java.util.Arrays.toString(data));
			data[3] = data[3].replaceAll("[a-zA-Z]", "");
			data[3] = data[3].replaceAll(",", "");
			data[3] = data[3].replaceAll(" ", "");
			if (data.length == 5)
				groupes.add(new Group(data[2], link.getAttribute("href"), data[4], Integer.parseInt(data[3])));
			else if (data.length < 5)
				groupes.add(new Group(data[2], link.getAttribute("href"), Integer.parseInt(data[3])));
		}
		System.out.println("Groups done");
		return groupes;
	}

	private ArrayList<Like> captureLikesFromMore(WebDriver driver) {
		ArrayList<Like> likes = new ArrayList<Like>();
		ToolsWindowControl.scrollDownUntilBottom(driver,0);
		ToolsWindowControl.scrollUp(driver);
		WebElement div = driver.findElement(By.cssSelector("div[role='tabpanel']"));
		List<WebElement> liste = div.findElements(By.tagName("li"));
		for (int i = 0; i < liste.size(); i++) {
			String[] data = liste.get(i).getText().split("\n");
			WebElement link = liste.get(i).findElement(By.tagName("a"));
			likes.add(new Like(data[0], data[1], link.getAttribute("href")));
		}
		System.out.println("Likes done");
		return likes;
	}
}
