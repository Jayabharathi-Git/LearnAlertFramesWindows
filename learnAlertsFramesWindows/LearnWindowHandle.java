package learnAlertsFramesWindows;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LearnWindowHandle {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);

		driver.get("http://leafground.com/pages/Window.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		System.out.println(driver.getTitle());
		driver.findElement(By.id("home")).click();
		
		//get number of windows
		String parentWindow = driver.getWindowHandle();
		
		Set<String> windowHandles = driver.getWindowHandles();
		//Set to List
		List<String> winList=new ArrayList<String>(windowHandles);
		System.out.println(windowHandles);
		System.out.println(winList);
		String expectedTitle ="TestLeaf - Interact with Windows";
		for(String s:windowHandles) {
			driver.switchTo().window(s);
			if(driver.getTitle().equals(expectedTitle)) {
				System.out.println(driver.getTitle());
				break;
			}
			else
			{
				driver.close();
				driver.switchTo().window(parentWindow);
			}
			
		}
		
		driver.switchTo().window(winList.get(1));
		System.out.println(driver.getTitle());

	}

}
