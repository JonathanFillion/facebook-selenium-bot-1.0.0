import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class imageCapture {

	WebDriver driver = null;

	try
	{
		driver = new FirefoxDriver();
		driver.get("http://agiletesters.com.br");

		WebElement logo = driver.findElement(By.cssSelector(".forum-logo"));
		String logoSRC = logo.getAttribute("src");

		URL imageURL = new URL(logoSRC);
		BufferedImage saveImage = ImageIO.read(imageURL);

		ImageIO.write(saveImage, "png", new File("logo-forum.png"));

	}catch(
	Exception e)
	{
		e.printStackTrace();
	}finally
	{
		driver.close();
	}
}}