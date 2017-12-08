package seleniumTest;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class InvalidPaths {

	@Test
	public void test() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8085/home");
		
		//Clerk failed Login
		driver.findElement(By.id("email")).sendKeys("admin");	
		driver.findElement(By.id("password")).sendKeys("add");
		Thread.sleep(2000);		
		driver.findElement(By.id("login")).click();						
		//login complete
		
		//Clerk Login
		driver.findElement(By.id("email")).sendKeys("admin");	
		driver.findElement(By.id("password")).sendKeys("admin");
		Thread.sleep(2000);		
		driver.findElement(By.id("login")).click();								
		//login complete
		
		//Creating Course
		Select dropdown = new Select(driver.findElement(By.id("adminAction")));	
        dropdown.selectByValue("CREATECOURSE");
		Thread.sleep(1000);
		driver.findElement(By.id("perform")).click();
		driver.findElement(By.id("midterms")).sendKeys("2");
		driver.findElement(By.id("assignments")).sendKeys("0");
		dropdown = new Select(driver.findElement(By.id("finalExam")));
		dropdown.selectByValue("YES");
		driver.findElement(By.id("size")).sendKeys("25");
		driver.findElement(By.id("title")).sendKeys("Machine Learning");
		driver.findElement(By.id("classCode")).sendKeys("201099");
		Thread.sleep(2000);
		driver.findElement(By.id("createCourse")).click();
		//Course Created
		
		//Creating same Course again
		dropdown = new Select(driver.findElement(By.id("adminAction")));	
        dropdown.selectByValue("CREATECOURSE");
		Thread.sleep(1000);
		driver.findElement(By.id("perform")).click();
		driver.findElement(By.id("midterms")).sendKeys("2");
		driver.findElement(By.id("assignments")).sendKeys("0");
		dropdown = new Select(driver.findElement(By.id("finalExam")));
		dropdown.selectByValue("YES");
		driver.findElement(By.id("size")).sendKeys("25");
		driver.findElement(By.id("title")).sendKeys("Machine Learning");
		driver.findElement(By.id("classCode")).sendKeys("201099");
		Thread.sleep(2000);
		driver.findElement(By.id("createCourse")).click();		
		//Course Created
		
		//Creating Student1
		dropdown = new Select(driver.findElement(By.id("adminAction")));
		dropdown.selectByValue("CREATESTUDENT");
		Thread.sleep(1000);
		driver.findElement(By.id("perform")).click();
		driver.findElement(By.id("studentemail")).sendKeys("satender@gmail.com");
		driver.findElement(By.id("studentpassword")).sendKeys("satender");
		dropdown = new Select(driver.findElement(By.id("status")));
		dropdown.selectByValue("FULLTIME");
		Thread.sleep(2000);
		driver.findElement(By.id("createStudent")).click();				
		//Student created
		
		//Creating same Student1
		dropdown = new Select(driver.findElement(By.id("adminAction")));
		dropdown.selectByValue("CREATESTUDENT");
		Thread.sleep(1000);
		driver.findElement(By.id("perform")).click();
		driver.findElement(By.id("studentemail")).sendKeys("satender@gmail.com");
		driver.findElement(By.id("studentpassword")).sendKeys("satender");
		dropdown = new Select(driver.findElement(By.id("status")));
		dropdown.selectByValue("FULLTIME");
		Thread.sleep(2000);
		driver.findElement(By.id("createStudent")).click();						
		//Student created
		
		//Delete non- existence course
		dropdown = new Select(driver.findElement(By.id("adminAction")));
		dropdown.selectByValue("DESTROYCOURSE");
		Thread.sleep(1000);
		driver.findElement(By.id("perform")).click();
		driver.findElement(By.id("Coursecode")).sendKeys("201000");
		Thread.sleep(2000);
		driver.findElement(By.id("deleteCourse")).click();				
		//Course Deleted
		
		//Deleting non-existence Student
		dropdown = new Select(driver.findElement(By.id("adminAction")));
		dropdown.selectByValue("DELETESTUDENT");
		Thread.sleep(1000);
		driver.findElement(By.id("perform")).click();
		driver.findElement(By.id("deleteStudentEmail")).sendKeys("saten@gmail.com");
		Thread.sleep(2000);
		driver.findElement(By.id("deleteStudent")).click();		
		//Student Deleted
		
		//Clerk Checking 85% above scored student before term ends
		dropdown = new Select(driver.findElement(By.id("adminAction")));	
		dropdown.selectByValue("CHECKMARKS");
		Thread.sleep(1000);
		driver.findElement(By.id("perform")).click();
		Thread.sleep(6000);
		//Complete		
				
		//Clerk logout
		driver.findElement(By.linkText("logout")).click();
		//Logout complete

		Thread.sleep(20000);
		
		//Student Login failed
		driver.findElement(By.id("email")).sendKeys("satender@gmail.com");	
		driver.findElement(By.id("password")).sendKeys("bat");
		Thread.sleep(2000);		
		driver.findElement(By.id("login")).click();						
		//login complete
		
		//Student Login
		driver.findElement(By.id("email")).sendKeys("satender@gmail.com");	
		driver.findElement(By.id("password")).sendKeys("satender");
		Thread.sleep(2000);		
		driver.findElement(By.id("login")).click();						
		//login complete		
			
		
		//Student Registration before registration started
		dropdown = new Select(driver.findElement(By.id("studentAction")));
		dropdown.selectByValue("REGISTERCOURSE");
		Thread.sleep(1000);
		driver.findElement(By.id("StudentPerform")).click();
		dropdown = new Select(driver.findElement(By.id("course")));
		dropdown.selectByIndex(1);
		Thread.sleep(2000);		
		driver.findElement(By.id("Register")).click();
		//Registration complete
		
		Thread.sleep(20000);
		
		//Student Registration
		dropdown = new Select(driver.findElement(By.id("studentAction")));
		dropdown.selectByValue("REGISTERCOURSE");
		Thread.sleep(1000);
		driver.findElement(By.id("StudentPerform")).click();
		dropdown = new Select(driver.findElement(By.id("course")));
		dropdown.selectByIndex(1);
		Thread.sleep(2000);		
		driver.findElement(By.id("Register")).click();
		//Registration complete		
		
		//Student Registration of same course again
		dropdown = new Select(driver.findElement(By.id("studentAction")));
		dropdown.selectByValue("REGISTERCOURSE");
		Thread.sleep(1000);
		driver.findElement(By.id("StudentPerform")).click();
		dropdown = new Select(driver.findElement(By.id("course")));
		dropdown.selectByIndex(1);
		Thread.sleep(2000);		
		driver.findElement(By.id("Register")).click();
		//Registration complete	
		
		//Student Checking marks
		dropdown = new Select(driver.findElement(By.id("studentAction")));
		dropdown.selectByValue("CHECKMARKS");
		Thread.sleep(2000);
		driver.findElement(By.id("StudentPerform")).click();
		//Check Marks complete			
		
		//Student logout
		driver.findElement(By.linkText("logout")).click();
		//Logout complete
		
		driver.close();		
	}
	

}
