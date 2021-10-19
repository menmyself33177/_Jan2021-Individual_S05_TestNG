package variousConcept;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LearnTestNG_ByClass {
	
	@Test(priority = 2)
	public void addCustomer() {
		System.out.println("Test-2");
		//Element Library
		By USER_NAME_FIELD = By.id("username");
		By PASSWORD_ELEMENT = By.id("password");
		By SIGNIN_BUTTON = By.name("login");
		
		
		
		
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
	
	
}
