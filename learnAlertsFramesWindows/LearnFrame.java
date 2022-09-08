package learnAlertsFramesWindows;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LearnFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		driver.get("http://leafground.com/pages/frame.html");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		driver.switchTo().frame(0);
		
		driver.findElement(By.id("Click")).click();
		// to switch to main window
		driver.switchTo().defaultContent();
		System.out.println(driver.findElement(By.tagName("h1")).getText());
		
		WebElement eleFrame = driver.findElement(By.xpath("(//div[@id='wrapframe']/iframe)[2]"));
		//outer frame
		driver.switchTo().frame(eleFrame);
		//inner frame
		driver.switchTo().frame("frame2");
		
		driver.findElement(By.id("Click1")).click();
		//parent frame
		driver.switchTo().parentFrame();

	}

}
