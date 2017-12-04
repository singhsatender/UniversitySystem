package seleniumTest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Test1 {

	@Test
	public void test() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8085/home");
		driver.findElement(By.id("email")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("admin");
		driver.findElement(By.id("login")).click();
		Thread.sleep(1000);
		Select dropdown = new Select(driver.findElement(By.id("adminAction")));
		dropdown.selectByValue("CREATESTUDENT");
		driver.findElement(By.id("perform")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("studentemail")).sendKeys("satender@gmail.com");
		driver.findElement(By.id("studentpassword")).sendKeys("satender");
		dropdown = new Select(driver.findElement(By.id("status")));
		dropdown.selectByValue("FULLTIME");
		Thread.sleep(2000);
		driver.findElement(By.id("createStudent")).click();
		dropdown = new Select(driver.findElement(By.id("adminAction")));
		dropdown.selectByValue("CREATECOURSE");
		driver.findElement(By.id("perform")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("midterms")).sendKeys("2");
		driver.findElement(By.id("assignments")).sendKeys("0");
		dropdown = new Select(driver.findElement(By.id("finalExam")));
		dropdown.selectByValue("YES");
		driver.findElement(By.id("size")).sendKeys("25");
		driver.findElement(By.id("title")).sendKeys("Machine Learning");
		driver.findElement(By.id("classCode")).sendKeys("201099");
		Thread.sleep(2000);
		driver.findElement(By.id("createCourse")).click();
		
	}

}
