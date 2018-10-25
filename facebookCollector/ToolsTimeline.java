package facebookCollector;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dataStructures.Comment;
import dataStructures.Discussion;

public class ToolsTimeline {

	public void getTimeline(WebDriver driver) {
		WebElement timeline = driver.findElement(By.partialLinkText("Timeline"));
		timeline.click();
	}

	public void getTimelinePosts(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		ArrayList<String> returnData = new ArrayList<String>();
		ArrayList<Discussion> chat = new ArrayList<Discussion>();

		int compteur = 0;
		while (compteur < 5) {
			// System.out.println("capture : " + compteur);
			ToolsWindowControl.scrollDown(driver);
			ToolsWindowControl.scrollUp(driver);
			ToolsWindowControl.delay(400);
			// Get timeline
			WebElement Ol = driver.findElement(By.cssSelector("ol[class='_2t4u clearfix']"));
			// Get article
			List<WebElement> listDiv = Ol.findElements(By.cssSelector("div[class='_5pcb _4b0l']"));
			ArrayList<WebElement> dataDiv = new ArrayList<WebElement>();
			// Comment section
			for (int i = 0; i < listDiv.size(); i++) {
				if (listDiv.get(i).findElements(By.cssSelector("div[class='_4-u2 mbm _4mrt _5jmm _5pat _5v3q _4-u8']"))
						.size() == 0) {
					continue;
				} else {
					dataDiv.addAll(listDiv.get(i)
							.findElements(By.cssSelector("div[class='_4-u2 mbm _4mrt _5jmm _5pat _5v3q _4-u8']")));
				}
			}

			// text: span UFICommentBody
			// urlProfile: a UFICommentActorName
			// urlProfile: a UFICommentActorName .text
			// numberOflikes : span UFICommentLikeButton
			// each sujet du profile
			for (int i = 0; i < dataDiv.size(); i++) {
				Discussion currentThread = new Discussion();
				expandPost1(driver, dataDiv.get(i), null);
				// Get comments inside comment section
				if (dataDiv.get(i)
						.findElements(By.cssSelector("div[aria-label='Comment'], div[aria-label='Comment reply']"))
						.size() != 0) {
					List<WebElement> listeComments = dataDiv.get(i)
							.findElements(By.cssSelector("div[aria-label='Comment'], div[aria-label='Comment reply']"));

					// Associate the reply to the correct comment
					for (int j = 0; j < listeComments.size(); j++) {
						String type = listeComments.get(j).getAttribute("aria-label");
						String name = listeComments.get(j)
								.findElement(By.cssSelector("a[class=' UFICommentActorName']")).getText();
						System.err.println(name);
						String profileUrl = listeComments.get(j)
								.findElement(By.cssSelector("a[class=' UFICommentActorName']")).getAttribute("href");
						String text = listeComments.get(j).findElement(By.cssSelector("span[data-ft='{\"tn\":\"K\"}'], span[class='UFICommentBody']"))
								.getText();

						// String numberOfLikes;
						// if
						// (listeComments.get(j).findElements(By.cssSelector("span[class='UFICommentLikeButton']"))
						// .size() != 0) {
						// numberOfLikes = listeComments.get(j)
						// .findElement(By.cssSelector("span[class='UFICommentLikeButton']")).getText();
						// } else {
						// numberOfLikes = "0";
						// }

						if (type.equals("Comment")) {
							currentThread.addComment(new Comment(name, profileUrl, text, "", null));
						} else if (type.equals("Comment reply")) {
							currentThread.addToLastAsReply(new Comment(name, profileUrl, text, "", null));
						}
					}
				}
				deleteElement(driver, dataDiv.get(i));
				compteur++;
			}
			ToolsWindowControl.scrollUp(driver);
		}
	}

	private void deleteElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js;
		js = (JavascriptExecutor) driver;
		js.executeScript("return document.getElementById('" + element.getAttribute("id") + "').remove();");
	}

	// click on some stuff to expand
	// need a loop everything
	public void expandPost(WebDriver driver, WebElement element) {
		boolean viewPreviousComment = true;
		boolean viewMoreComment = true;
		boolean viewMoreReplies = true;
		boolean replied = true;
		if (viewPreviousComment && element.getText().contains("View previous comments")) {
			List<WebElement> liste = element.findElements(By.partialLinkText("View previous comments"));
			ToolsWindowControl.clickList(liste, driver);
			System.err.println("1.View previous comments must be no more");
		} else {
			viewPreviousComment = false;
			if (viewMoreComment && element.getText().contains("View more replies")) {
				List<WebElement> liste = element.findElements(By.partialLinkText("View more replies"));
				ToolsWindowControl.clickList(liste, driver);
				viewMoreComment = false;
				viewMoreReplies = false;
				System.err.println("2.View more replies must be no more");
			} else {
				if (viewMoreReplies && element.getText().contains("more comment")) {
					List<WebElement> liste = element.findElements(By.partialLinkText("more comment"));
					ToolsWindowControl.clickList(liste, driver);
					viewMoreComment = false;
					viewMoreReplies = false;
					System.err.println("3.more comment must be no more");
				}
			}
		}
		if (!(viewPreviousComment && viewMoreComment && viewMoreReplies) && replied
				&& element.getText().contains("replied")) {
			List<WebElement> liste = element.findElements(By.partialLinkText("replied"));
			ToolsWindowControl.clickList(liste, driver);
			replied = false;
			System.err.println("4.replied must be no more");
		}
		if (!(viewPreviousComment && viewMoreComment && viewMoreReplies && replied)
				&& element.getText().contains("See More")) {
			List<WebElement> liste = element.findElements(By.partialLinkText("See More"));
			ToolsWindowControl.clickList(liste, driver);
			System.err.println("5.See more must be no more");
		}
		if (!(viewPreviousComment && viewMoreComment && viewMoreReplies && replied)
				&& element.getText().contains("See Translation")) {
			List<WebElement> liste = element.findElements(By.partialLinkText("See Translation"));
			ToolsWindowControl.clickList(liste, driver);
			System.err.println("6.See translation must be no more");
		}
	}

	public void expandPost1(WebDriver driver, WebElement element, boolean indic[]) {

		if (indic == null) {
			indic = new boolean[6];
			indic = setAllTrue(indic);
		}

		while (indic[0] && element.getText().contains("View previous comments")) {
			List<WebElement> liste = element.findElements(By.partialLinkText("View previous comments"));
			ToolsWindowControl.clickList(liste, driver);
			ToolsWindowControl.delay(2000);
		}
		System.err.println("1.View previous comments must be no more");

		while (indic[1] && element.getText().contains("View more replies")) {
			System.out.println("Getting in ?");
			List<WebElement> liste = element.findElements(By.partialLinkText("View more replies"));
			ToolsWindowControl.clickList(liste, driver);
			ToolsWindowControl.delay(1000);
		}
		System.err.println("2.View more replies must be no more");

		while (indic[2] && element.getText().contains("more comment")) {
			List<WebElement> liste = element.findElements(By.partialLinkText("more comment"));
			ToolsWindowControl.clickList(liste, driver);
		}
		System.err.println("3.more comment must be no more");

		if (indic[3] && element.getText().contains("replied")) {
			List<WebElement> liste = element.findElements(By.partialLinkText("replied"));
			ToolsWindowControl.clickList(liste, driver);
		}
		System.err.println("4.replied must be no more");

		if (indic[4] && element.getText().contains("See More")) {
			List<WebElement> liste = element.findElements(By.partialLinkText("See More"));
			ToolsWindowControl.clickList(liste, driver);
		}
		System.err.println("5.See more must be no more");
		if (indic[5] && element.getText().contains("See Translation")) {
			List<WebElement> liste = element.findElements(By.partialLinkText("See Translation"));
			ToolsWindowControl.clickList(liste, driver);
		}
		System.err.println("6.See translation must be no more");

		ToolsWindowControl.delay(450);
		indic[0] = element.getText().contains("View previous comments");
		indic[1] = element.getText().contains("View more replies");
		indic[2] = element.getText().contains("more comment");
		indic[3] = element.getText().contains("replied");
		indic[4] = element.getText().contains("See More");
		indic[5] = element.getText().contains("See Translation");
		if (anyoneIsTrue(indic)) {
			expandPost1(driver, element, indic);
		}
	}

	private boolean anyoneIsTrue(boolean[] indic) {

		for (int i = 0; i < indic.length; i++) {
			if (indic[i])
				return true;
		}
		return false;
	}

	private boolean[] setAllTrue(boolean[] indic) {

		for (int i = 0; i < indic.length; i++) {
			indic[i] = true;
		}

		return indic;
	}

	public String getProfileName(WebDriver driver) {
		WebElement name = driver.findElement(By.cssSelector("span[data-testid='profile_name_in_profile_page']"));
		return name.getText();
	}

	public String getProfileInfoFromTimeline(WebDriver driver) {
		WebElement profileInfo = driver.findElement(By.id("intro_container_id"));
		System.out.println("Profile info done");
		return profileInfo.getText();
	}
}
