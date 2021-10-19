package variousConcept;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LearnTestNG {
	WebDriver driver;
	String browser;
	String url;

	@BeforeClass
	public void readConfig() {
		// BufferedRead // InputStream //FileReader //Scanner

		Properties prop = new Properties();

		try {

			InputStream input = new FileInputStream("src\\main\\java\\config\\config.properties");
			prop.load(input);
			browser = prop.getProperty("browser");
			System.out.println("Browser used : " + browser);
			url = prop.getProperty("url");

		} catch (IOException e) {
			e.printStackTrace();

		}

	}

	@BeforeMethod
	public void launchBrowser() {
		if (browser.equalsIgnoreCase("chroMe")) {
			System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browser.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		driver.get("https://techfios.com/billing/?ng=login/");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // This is to Execute "Add Customer"
	}

//	@Test(priority = 1)
	public void loginTest() throws InterruptedException {

//		Assert.assertEquals(driver.getTitle(), "Login - iBilling1", "Wrong page"); //Intentionally making wrong
		Assert.assertEquals(driver.getTitle(), "Login - iBilling", "Correct Landing page");

//		Element Lib		
		WebElement USER_NAME_ELEMENT = driver.findElement(By.xpath("//input[@id='username']"));
		WebElement PASSWORD_ELEMENT = driver.findElement(By.xpath("//input[@id='password']"));
		WebElement SUBMIT_BUTTON_ELEMENT = driver.findElement(By.xpath("//button[@type='submit']"));

//		login data
		String loginId = "demo@techfios.com";
		String password = "abc123";

		USER_NAME_ELEMENT.sendKeys(loginId);
		PASSWORD_ELEMENT.sendKeys(password);
		SUBMIT_BUTTON_ELEMENT.click();

		WebElement DASHBOARD_TITLE_ELEMENT = driver.findElement(By.xpath("//h2[contains(text(), 'Dashboard')]"));
		System.out.println("==================" + DASHBOARD_TITLE_ELEMENT.getText());
		Assert.assertEquals(DASHBOARD_TITLE_ELEMENT.getText(), "Dashboard", "Correct Landing Page");

	}

	@Test(priority = 2)
	public void addCustomer() {
		Assert.assertEquals(driver.getTitle(), "Login - iBilling", "Wrong Landing page");

//		Element Lib		
		WebElement USER_NAME_ELEMENT = driver.findElement(By.xpath("//input[@id='username']"));
		WebElement PASSWORD_ELEMENT = driver.findElement(By.xpath("//input[@id='password']"));
		WebElement SUBMIT_BUTTON_ELEMENT = driver.findElement(By.xpath("//button[@type='submit']"));

//		login data
		String loginId = "demo@techfios.com";
		String password = "abc123";

//		Test data or Mock data
		String fullname = "Luthfor Rahman";
		String companyName = "Techfios";
		String email = "techfios@gmail.com";
		String phone = "7187555584";

		USER_NAME_ELEMENT.sendKeys(loginId);
		PASSWORD_ELEMENT.sendKeys(password);
		SUBMIT_BUTTON_ELEMENT.click();

		WebElement DASHBOARD_TITLE_ELEMENT = driver.findElement(By.xpath("//h2[contains(text(), 'Dashboard')]"));
		System.out.println("==================" + DASHBOARD_TITLE_ELEMENT.getText());
		Assert.assertEquals(DASHBOARD_TITLE_ELEMENT.getText(), "Dashboard", "Correct Landing Page");

		WebElement CUSTOMER_ELEMENT = driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[3]/a/span[1]"));
		WebElement ADD_CUSTOMER_ELEMENT = driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[3]/ul/li[1]/a"));

		CUSTOMER_ELEMENT.click();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOf(ADD_CUSTOMER_ELEMENT));
		
		waitForElement(driver, 5, ADD_CUSTOMER_ELEMENT);
		
		
		ADD_CUSTOMER_ELEMENT.click();

		WebElement FULL_NAME_ELEMENT = driver.findElement(By.xpath("//*[@id=\"account\"]"));
		WebElement COMPANY_DROPDOWN_ELEMENT = driver.findElement(By.xpath("//select[@id='cid']"));
		WebElement EMAIL_ELEMENT = driver.findElement(By.xpath("//*[@id=\"email\"]"));

		FULL_NAME_ELEMENT.sendKeys(fullname);

		// DropDown case....we need to create an object
		Select sel = new Select(COMPANY_DROPDOWN_ELEMENT);
		sel.selectByVisibleText(companyName);

		// Generate RANDOM Number
		Random rnd = new Random();
		int randomNum = rnd.nextInt(999);

		// Fill email
		EMAIL_ELEMENT.sendKeys(randomNum + email);

	}

	public void waitForElement(WebDriver driver, int waitTime, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	//	@AfterMethod
	public void tearDown() {
		driver.close();
		driver.quit();

	}

}
