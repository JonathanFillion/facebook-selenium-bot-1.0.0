package facebookCollector;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import dataStructures.Node;
import dataStructures.NodeInterface;

public class ToolsFollow {

	public NodeInterface getFriendFromMain(WebDriver driver) {

		ArrayList<Node> friends;
		ArrayList<Node> followers;
		ArrayList<Node> following;
		WebElement friendsTab = driver.findElement(By.linkText("Friends"));
		friendsTab.click();
		WebElement menu = driver.findElement(By.cssSelector("div[role='tablist']"));
		List<WebElement> menuList = menu.findElements(By.tagName("a"));

		ToolsWindowControl.delay(250);
		ToolsWindowControl.scrollDownUntilBottom(driver,0);
		ToolsWindowControl.scrollUp(driver);
		friends = getAllFriends(driver);

		ToolsWindowControl.delay(250);
		menuList.get(3).click();
		ToolsWindowControl.scrollDownUntilBottom(driver,0);
		ToolsWindowControl.scrollUp(driver);
		followers = getFollowers(driver);

		ToolsWindowControl.delay(250);
		menuList.get(4).click();
		ToolsWindowControl.scrollDownUntilBottom(driver,0);
		ToolsWindowControl.scrollUp(driver);
		following = getAllFriendsFromListByDataTestId(driver);

		NodeInterface inter = new NodeInterface(friends, followers, following);
		
		return inter;
	}

	public ArrayList<Node> getFollowers(WebDriver driver) {
		ArrayList<Node> followers = new ArrayList<Node>();
		WebElement ul = driver.findElement(By.className("fbProfileBrowserList"));
		List<WebElement> liste = ul.findElements(By.tagName("li"));
		System.out.println(liste.size());

		for (int i = 0; i < liste.size(); i++) {
			String data = liste.get(i).getText();
			String[] dataSplit = data.split("\n");

			if (dataSplit.length > 1)
				followers.add(new Node(dataSplit[1], liste.get(i).findElement(By.tagName("a")).getAttribute("href")));
			else
				followers.add(new Node(dataSplit[0], liste.get(i).findElement(By.tagName("a")).getAttribute("href")));
		}
		return followers;
	}

	public ArrayList<Node> getAllFriendsFromList2X(WebDriver driver) {
		List<WebElement> liste = driver.findElements(By.className("uiProfileBlockContent"));
		ArrayList<Node> followers = new ArrayList<Node>();
		for (int i = 0; i < liste.size(); i++) {
			// System.out.println(liste.get(i).getText());
			String nameData = liste.get(i).getText();
			String url = liste.get(i).findElement(By.tagName("a")).getAttribute("href");
			String[] nameDataSplit = nameData.split("\n");
			if (nameDataSplit.length == 2)
				followers.add(new Node(nameDataSplit[0], url, nameDataSplit[1]));
			else
				followers.add(new Node(nameDataSplit[0], url));
		}
		return followers;
	}

	public ArrayList<Node> getAllFriendsFromListByDataTestId(WebDriver driver) {
		List<WebElement> liste = driver.findElements(By.cssSelector("div[data-testid='friend_list_item']"));
		ArrayList<Node> nodeList = new ArrayList<Node>();
		for (int i = 0; i < liste.size(); i++) {
			String nameData = liste.get(i).getText();
			String url = liste.get(i).findElement(By.tagName("a")).getAttribute("href");
			String[] nameDataSplit = nameData.split("\n");

			if (!nameDataSplit[0].equals(""))
				if (nameDataSplit.length == 2)
					nodeList.add(new Node(nameDataSplit[0], url, nameDataSplit[1]));
				else
					nodeList.add(new Node(nameDataSplit[0], url));
		}
		return nodeList;
	}

	public ArrayList<Node> getAllFriends(WebDriver driver) {
		List<WebElement> liste = driver.findElements(By.cssSelector("div[data-testid='friend_list_item']"));
		ArrayList<Node> nodeList = new ArrayList<Node>();
		for (int i = 0; i < liste.size(); i++) {
			String nameData = liste.get(i).getText();
			String url = liste.get(i).findElement(By.tagName("a")).getAttribute("href");
			String[] nameDataSplit = nameData.split("\n");
			if (nameDataSplit[0].contains("Add Friend")) {
				String[] newArray = new String[nameDataSplit.length - 1];
				for (int j = 0; j < newArray.length; j++) {
					newArray[j] = nameDataSplit[j + 1];
				}
				nameDataSplit = newArray;
			}
			if (!nameDataSplit[0].equals("")) {
				if (nameDataSplit.length == 2)
					nodeList.add(new Node(nameDataSplit[0], url, nameDataSplit[1]));
				else
					nodeList.add(new Node(nameDataSplit[0], url));
			}
		}
		return nodeList;
	}

	public void getAllFriendsByDatapnref(WebDriver driver) {
		// data-pnref="friends
		List<WebElement> listeUl = driver.findElements(By.cssSelector("ul[data-pnref='friends']"));
		ArrayList<WebElement> fullList = new ArrayList<WebElement>();
		for (int i = 0; i < listeUl.size(); i++) {
			List<WebElement> listeLi = listeUl.get(i).findElements(By.tagName("li"));
			fullList.addAll(listeLi);
		}
		System.out.println(fullList.size());
		for (int i = 0; i < fullList.size(); i++) {
			System.out.println(fullList.get(i).getText());
			System.out.println();
		}

	}

}
