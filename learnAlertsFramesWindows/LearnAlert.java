package learnAlertsFramesWindows;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LearnAlert {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		
		driver.get("http://leafground.com/pages/Alert.html");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		driver.findElement(By.xpath("//button[text()='Alert Box']")).click();
		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();
		
		
		driver.findElement(By.xpath("//button[text()='Confirm Box']")).click();
		Alert confAlert = driver.switchTo().alert();
		System.out.println(alert.getText());
		confAlert.dismiss();
		
		
		driver.findElement(By.xpath("//button[text()='Prompt Box']")).click();
		Alert promptAlert = driver.switchTo().alert();
		promptAlert.sendKeys("YYYY");
		System.out.println(promptAlert.getText());
		promptAlert.accept();
		
		
		//driver.close();
		
		
	}

}
