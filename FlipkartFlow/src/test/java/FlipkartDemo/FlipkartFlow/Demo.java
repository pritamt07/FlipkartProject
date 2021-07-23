package FlipkartDemo.FlipkartFlow;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Flipkart workflow
 *
 */
public class Demo {
	// Instantiate a ChromeDriver class.
	@DataProvider(name = "dataprovider")
	public Object[][] getDataFromDataProvider() {
		return new Object[][] { { "Samsung Galaxy S10" }, { "Samsung Galaxy Note 10" },
				{ "Samsung Galaxy S21 Ultra" } };
	}

	@Test(dataProvider = "dataprovider")
	public void demoFlow(String mobileName) throws InterruptedException {
		ArrayList<String> list = new ArrayList<String>();
		String path = System.getProperty("user.dir");
		String chromedriver_path ="\\resources\\chromedriver.exe";
		System.out.println("Actual Path is "+path+chromedriver_path);
		System.setProperty("webdriver.chrome.driver", path+chromedriver_path);
		WebDriver driver = new ChromeDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 35);
		// Launch Website
		driver.navigate().to("https://www.flipkart.com/");

		// Maximize the browser
		driver.manage().window().maximize();

		try {
			driver.findElement(By.xpath("//button[@class='_2KpZ6l _2doB4z']")).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Enter product to search
		driver.findElement(By.xpath("//input[@title='Search for products, brands and more']")).sendKeys(mobileName);

		// click on Search
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		// Click on Category Mobile
		driver.findElement(By.xpath("//a[text()='Mobiles']")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='SAMSUNG']//..//div")));
		Thread.sleep(3000);
		// Click on Brand Samsung
		driver.findElement(By.xpath("//div[text()='SAMSUNG']//..//div")).click();

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("(//section[@class='_2hbLCH _24gLJx']//label//div)[1]")));
		Thread.sleep(3000);
		// Click on Flipkart Assured
		WebElement flipkartAssuredClick = driver
				.findElement(By.xpath("(//section[@class='_2hbLCH _24gLJx']//label//div)[1]"));
		js.executeScript("arguments[0].click();", flipkartAssuredClick);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Price -- High to Low']")));

		// Click Price High to Low
		driver.findElement(By.xpath("//div[text()='Price -- High to Low']")).click();

		Thread.sleep(10000);
		// Read
		List<WebElement> we = driver
				.findElements(By.xpath("//a[@class='_1fQZEK']//div[@class='_3pLy-c row']//div//div[@class='_4rR01T']"));

		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class='col col-5-12 nlI3QM']//div//div//div[@class='_30jeq3 _1_WHN1']")));

		List<WebElement> we1 = driver
				.findElements(By.xpath("//div[@class='col col-5-12 nlI3QM']//div//div//div[@class='_30jeq3 _1_WHN1']"));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='_1fQZEK']")));

		List<WebElement> we2 = driver.findElements(By.xpath("//a[@class='_1fQZEK']"));

		for (int i = 0; i < we.size(); i++) {
			String product = we.get(i).getText();
			System.out.println("The product on the page is " + product);
			String cost = we1.get(i).getText().replaceAll("[^A-Za-z0-9]", "");
			System.out.println("The product cost on the page is " + cost);
			String url = we2.get(i).getAttribute("href");
			System.out.println("The product URL on the page is " + url);
			list.add(product);
			list.add(cost);
			list.add(url);
			System.out.println(" ");
		}
		System.out.println("The entrie list is " + list);

		// close the web browser
		driver.close();
	}
}