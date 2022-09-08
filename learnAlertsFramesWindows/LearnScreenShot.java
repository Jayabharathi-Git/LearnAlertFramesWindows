package learnAlertsFramesWindows;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LearnScreenShot {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver=new ChromeDriver(options);
		
		
		driver.get("https://www.irctc.co.in/nget/train-search");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//to screenshot one element alone
		WebElement eleButton = driver.findElement(By.xpath("//button[text()='OK']"));
		File source1 = eleButton.getScreenshotAs(OutputType.FILE);
		File desc1=new File("./screenshots/screenshotButton.png");
		FileUtils.copyFile(source1, desc1);
		
		File source = driver.getScreenshotAs(OutputType.FILE);
		File desc=new File("./screenshots/screenshot.png");
		FileUtils.copyFile(source, desc);
		
		
		
		driver.close();
	}

}
