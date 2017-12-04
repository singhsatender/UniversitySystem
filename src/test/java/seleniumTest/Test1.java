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
		
		//Clerk Login
		driver.findElement(By.id("email")).sendKeys("admin");	
		driver.findElement(By.id("password")).sendKeys("admin");
		Thread.sleep(1000);		
		driver.findElement(By.id("login")).click();						
		//login complete
		
		//Creating Student1
		Select dropdown = new Select(driver.findElement(By.id("adminAction")));	
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
		
		//Creating Student2
		dropdown = new Select(driver.findElement(By.id("adminAction")));	
		dropdown.selectByValue("CREATESTUDENT");
		Thread.sleep(1000);
		driver.findElement(By.id("perform")).click();
		driver.findElement(By.id("studentemail")).sendKeys("Mudit@gmail.com");
		driver.findElement(By.id("studentpassword")).sendKeys("Mudit");
		dropdown = new Select(driver.findElement(By.id("status")));
		dropdown.selectByValue("FULLTIME");
		Thread.sleep(2000);
		driver.findElement(By.id("createStudent")).click();				
		//Student created
		
		//Creating Course
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
		
		//Creating Course2
		dropdown = new Select(driver.findElement(By.id("adminAction")));
		dropdown.selectByValue("CREATECOURSE");
		Thread.sleep(1000);
		driver.findElement(By.id("perform")).click();
		driver.findElement(By.id("midterms")).sendKeys("2");
		driver.findElement(By.id("assignments")).sendKeys("0");
		dropdown = new Select(driver.findElement(By.id("finalExam")));
		dropdown.selectByValue("YES");
		driver.findElement(By.id("size")).sendKeys("25");
		driver.findElement(By.id("title")).sendKeys("Artificial Intelligence");
		driver.findElement(By.id("classCode")).sendKeys("201100");
		Thread.sleep(2000);
		driver.findElement(By.id("createCourse")).click();
		//Course Created
		
		//Creating Course3
		dropdown = new Select(driver.findElement(By.id("adminAction")));
		dropdown.selectByValue("CREATECOURSE");
		Thread.sleep(1000);
		driver.findElement(By.id("perform")).click();
		driver.findElement(By.id("midterms")).sendKeys("2");
		driver.findElement(By.id("assignments")).sendKeys("0");
		dropdown = new Select(driver.findElement(By.id("finalExam")));
		dropdown.selectByValue("YES");
		driver.findElement(By.id("size")).sendKeys("25");
		driver.findElement(By.id("title")).sendKeys("IOT");
		driver.findElement(By.id("classCode")).sendKeys("201101");
		Thread.sleep(2000);
		driver.findElement(By.id("createCourse")).click();
		//Course Created
		
		//Creating Course4
		dropdown = new Select(driver.findElement(By.id("adminAction")));
		dropdown.selectByValue("CREATECOURSE");
		Thread.sleep(1000);
		driver.findElement(By.id("perform")).click();
		driver.findElement(By.id("midterms")).sendKeys("2");
		driver.findElement(By.id("assignments")).sendKeys("0");
		dropdown = new Select(driver.findElement(By.id("finalExam")));
		dropdown.selectByValue("YES");
		driver.findElement(By.id("size")).sendKeys("25");
		driver.findElement(By.id("title")).sendKeys("ECommerce");
		driver.findElement(By.id("classCode")).sendKeys("201102");
		Thread.sleep(2000);
		driver.findElement(By.id("createCourse")).click();
		//Course Created
				
		//Deleting Student
		dropdown = new Select(driver.findElement(By.id("adminAction")));
		dropdown.selectByValue("DELETESTUDENT");
		Thread.sleep(1000);
		driver.findElement(By.id("perform")).click();
		driver.findElement(By.id("deleteStudentEmail")).sendKeys("satender@gmail.com");
		Thread.sleep(1000);
		driver.findElement(By.id("deleteStudent")).click();
		//Student Deleted
		
		//Delete course
		dropdown = new Select(driver.findElement(By.id("adminAction")));
		dropdown.selectByValue("DESTROYCOURSE");
		Thread.sleep(1000);
		driver.findElement(By.id("perform")).click();
		driver.findElement(By.id("Coursecode")).sendKeys("201099");
		Thread.sleep(1000);
		driver.findElement(By.id("deleteCourse")).click();
		//Course Deleted
		
		//Cancel course
		dropdown = new Select(driver.findElement(By.id("adminAction")));
		dropdown.selectByValue("CANCELCOURSE");
		Thread.sleep(1000);
		driver.findElement(By.id("perform")).click();
		driver.findElement(By.id("Coursecode")).sendKeys("201100");
		Thread.sleep(1000);
		driver.findElement(By.id("CancelCourse")).click();
		//Course Cancelled
				
		//Clerk logout
		driver.findElement(By.linkText("logout")).click();
		/*If above doesn't work put below code in place
		 * driver.findElement(By.id("logout")).click();
		 * */
		//Logout complete
		
		//Student Login
		driver.findElement(By.id("email")).sendKeys("Mudit@gmail.com");	
		driver.findElement(By.id("password")).sendKeys("Mudit");
		Thread.sleep(1000);		
		driver.findElement(By.id("login")).click();						
		//login complete
		
		//Student Registration
		dropdown = new Select(driver.findElement(By.id("studentAction")));
		dropdown.selectByValue("REGISTERCOURSE");
		Thread.sleep(1000);
		driver.findElement(By.id("StudentPerform")).click();
		dropdown = new Select(driver.findElement(By.id("course")));
		dropdown.selectByIndex(1);
		Thread.sleep(1000);		
		driver.findElement(By.id("Register")).click();
		//Registration complete
		
		//Student Registration2
		dropdown = new Select(driver.findElement(By.id("studentAction")));
		dropdown.selectByValue("REGISTERCOURSE");
		Thread.sleep(1000);
		driver.findElement(By.id("StudentPerform")).click();
		dropdown = new Select(driver.findElement(By.id("course")));
		dropdown.selectByIndex(2);
		Thread.sleep(1000);		
		driver.findElement(By.id("Register")).click();
		//Registration complete
		
		//Student Deregistration
		dropdown = new Select(driver.findElement(By.id("studentAction")));
		dropdown.selectByValue("DEREGISTERCOURSE");
		Thread.sleep(1000);
		driver.findElement(By.id("StudentPerform")).click();
		dropdown = new Select(driver.findElement(By.id("course")));
		dropdown.selectByIndex(2);
		Thread.sleep(1000);		
		driver.findElement(By.id("DeRegister")).click();
		//Deregistration Successful
		
		//Student Checking marks
		dropdown = new Select(driver.findElement(By.id("studentAction")));
		dropdown.selectByValue("CHECKMARKS");
		Thread.sleep(1000);
		driver.findElement(By.id("StudentPerform")).click();
		Thread.sleep(5000);
		//Check Marks complete
		
		//Student logout
		driver.findElement(By.linkText("logout")).click();
		/*If above doesn't work put below code in place
		 * driver.findElement(By.id("logout")).click();
		 * */
		//Logout complete
				
		//Clerk Login
		driver.findElement(By.id("email")).sendKeys("admin");	
		driver.findElement(By.id("password")).sendKeys("admin");
		Thread.sleep(1000);		
		driver.findElement(By.id("login")).click();						
		//login complete
		
		//Clerk Checking 85% above scored student
		dropdown = new Select(driver.findElement(By.id("adminAction")));	
		dropdown.selectByValue("CHECKMARKS");
		Thread.sleep(1000);
		driver.findElement(By.id("perform")).click();
		Thread.sleep(6000);
		//Complete
		
		//Clerk logout
		driver.findElement(By.linkText("logout")).click();
		/*If above doesn't work put below code in place
		 * driver.findElement(By.id("logout")).click();
		 * */
		//Logout complete
		driver.close();		
	}
	

}
