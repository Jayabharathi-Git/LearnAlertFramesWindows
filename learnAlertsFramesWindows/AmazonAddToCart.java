package learnAlertsFramesWindows;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonAddToCart {

	public ChromeDriver setUpDriver() {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		return driver;

	}

	public void switchToNewWindow(ChromeDriver driver) {
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> winList = new ArrayList<String>(windowHandles);
		driver.switchTo().window(winList.get(1));

	}

	public void takeScreenShot(ChromeDriver driver) throws IOException {
		File source = driver.getScreenshotAs(OutputType.FILE);
		File desc = new File("./screenshots/screenshot.png");
		FileUtils.copyFile(source, desc);
	}

	public void verifyCartTotal(String price, String cartTotal) {
		
		if (cartTotal.contains(price))
			System.out.println("Price and cart total are same");
	}

	public void startApp(ChromeDriver driver) throws IOException, InterruptedException {
		// launch url
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// search as oneplus 9 pro
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro" + "\n");

		// Get the price of the first product
		String firstPrice = driver.findElement(By.xpath("//span[@class='a-price-whole']")).getText();
		System.out.println("Price of first product : " + firstPrice);

		// Print the number of customer ratings for the first displayed product
		Thread.sleep(5000);
		WebElement eleRate = driver.findElement(By.xpath("(//a[@target='_blank']/span/following::span)[5]"));
		
		// System.out.println(eleRate.isEnabled());
		String numOfRatings = eleRate.getText();
		System.out.println("Number of Customer ratings for first product : " + numOfRatings);

		// Click the first text link of the first image
		driver.findElement(By.xpath("//a[@target='_blank']/span")).click();
		switchToNewWindow(driver);
		// Take a screen shot of the product displayed
		
		takeScreenShot(driver);
		// Click 'Add to Cart' button
		driver.findElement(By.id("add-to-cart-button")).click();

		// Get the cart subtotal and verify if it is correct
		Thread.sleep(5000);

		String cartSubTotal = driver.findElement(By.xpath("(//div[@id='attach-added-to-cart-message']//span/span)[2]"))
				.getText();

		verifyCartTotal(firstPrice, cartSubTotal);
		
		driver.quit();
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		AmazonAddToCart cart = new AmazonAddToCart();
		cart.startApp(cart.setUpDriver());

	}

}
