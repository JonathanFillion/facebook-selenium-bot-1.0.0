package facebookCollector;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import dataStructures.DataTemp;

public class Main_routine {
	String rootUrl = "";
	private WebDriver driver;
	private DataTemp data = new DataTemp();
	private Tools tools = new Tools();

	public static void main(String[] args) throws InterruptedException {
		Main_routine run = new Main_routine();
		run.setupScraper();
		run.startRoutine();
	}

	public void setupScraper() {
		driver = tools.createDriver();
		tools.getFacebookHomePage(driver);
		tools.loginFacebook(driver);
	}

	public void startRoutine() {
		data.saveProfile(tools.createProfile(driver, data.getNewTarget()));
	}
}
