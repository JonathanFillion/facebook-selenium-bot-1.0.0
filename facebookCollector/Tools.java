package facebookCollector;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import dataStructures.Node;
import dataStructures.NodeInterface;
import dataStructures.Profile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Tools {

	ToolsSetup toolsSetup = new ToolsSetup();
	ToolsAbout toolsAbout = new ToolsAbout();
	ToolsTimeline toolsTimeline = new ToolsTimeline();
	ToolsMore toolsMore = new ToolsMore();
	ToolsFollow toolsFollow = new ToolsFollow();

	public void loginFacebook(WebDriver driver) {
		toolsSetup.loginFacebook(driver);
	}

	public String getProfileName(WebDriver driver) {
		return toolsTimeline.getProfileName(driver);
	}

	public String getProfileInfoFromMain(WebDriver driver) {
		return toolsTimeline.getProfileInfoFromTimeline(driver);
	}

	public WebDriver createDriver() {
		return toolsSetup.createDriver();
	}

	public void getFacebookHomePage(WebDriver driver) {
		toolsSetup.getFacebookHomePage(driver);
	}

	public NodeInterface getFriendFromMain(WebDriver driver) {
		return toolsFollow.getFriendFromMain(driver);
	}

	public int getClientWindowHeigthSize(WebDriver driver) {
		return ToolsWindowControl.getClientWindowHeigthSize(driver);
	}

	private void scrollUp(WebDriver driver) {
		ToolsWindowControl.scrollUp(driver);
	}

	public void scrollDownUntilBottom(WebDriver driver) {
		ToolsWindowControl.scrollDownUntilBottom(driver, 0);
	}

	public ArrayList<Node> getAllFriendsFromList2X(WebDriver driver) {
		return toolsFollow.getAllFriendsFromList2X(driver);
	}

	public ArrayList<Node> getAllFriendsFromListByDataTestId(WebDriver driver) {
		return toolsFollow.getAllFriendsFromListByDataTestId(driver);
	}

	public Profile createProfile(WebDriver driver, String url) {
		driver.get(url);
		Profile profile = new Profile();
		getTimelinePosts(driver);
		//getTimelinePosts(driver);
	//	getTimelinePosts(driver);
		//getTimelinePosts(driver);
		/*
		 * profile.setName(getProfileName(driver)); String profileInformations =
		 * getProfileInfoFromMain(driver); System.out.println(profileInformations);
		 * System.out.println(); System.out.println(); String aboutInformation =
		 * captureFromAboutTab(driver); System.out.println(aboutInformation);
		 * getMoreFromMain(driver);
		 */

		// NodeInterface inter = getFriendFromMain(driver);
		return null;
	}

	public List<WebElement> getMoreTabMenu(WebDriver driver) {
		return toolsMore.getMoreTabMenu(driver);
	}

	private void delay(int time) {
		ToolsWindowControl.delay(time);
	}

	private void getMoreFromMain(WebDriver driver) {
		toolsMore.getMoreFromMain(driver);
	}

	public String captureFromAboutTab(WebDriver driver) {
		toolsAbout.getAboutTab(driver);
		return toolsAbout.captureFromAboutTab(driver);
	}

	public void getTimelinePosts(WebDriver driver) {
		// toolsTimeline.getTimeline(driver);
		toolsTimeline.getTimelinePosts(driver);
	}

}
