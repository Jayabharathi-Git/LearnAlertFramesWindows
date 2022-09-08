package learnAlertsFramesWindows;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class IRCTCPractice {
	
	public ChromeDriver setUpDriver() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		
		return driver;
	}
	
	public void startApp(ChromeDriver driver) {
		driver.get("https://www.irctc.co.in/nget/train-search");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		//close sweet alert - clicking ok
		driver.findElement(By.xpath("//button[text()='OK']")).click();
		
		//parent window handle
		String parentWindow = driver.getWindowHandle();
		
		//click on flights

		driver.findElement(By.xpath("//span[@class='allcircle circleone']/following-sibling::label[text()='FLIGHTS']"))
				.click();
		
		//get windowHandles
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> winList = new ArrayList<String>(windowHandles);
		System.out.println(windowHandles);

		//switch to new window
		driver.switchTo().window(winList.get(1));
		System.out.println("New Window Title : "+driver.getTitle());
		driver.close();
		
		//switch to parent window
		driver.switchTo().window(parentWindow);
		System.out.println("Parent window title :"+driver.getTitle());
		driver.close();
	}

	public static void main(String[] args) {

		IRCTCPractice windHand= new IRCTCPractice();
		windHand.startApp(windHand.setUpDriver());
		
		

		
		
	}
}
