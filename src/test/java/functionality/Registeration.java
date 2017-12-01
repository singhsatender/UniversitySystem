package functionality;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.server.logic.model.Course;
import com.server.logic.model.Student;
import com.server.logic.tables.CourseTable;
import com.server.logic.tables.StudentTable;

public class Registeration {

	@BeforeClass
	//set up the initial table
	public static void initialTitle(){
		CourseTable.getInstance();
	}
	
	@Test
	//case 1 :Successfully able to register student for courses
	
	public void test() {
		Student student1 = new Student(100,"sandy@carleton.ca","sandy","PARTTIME");
		assertEquals(true,CourseTable.getInstance().addStudentToCourse(student1,405060));
		}
	
    @Test
	//case 2 :Successfully able to add courses to student bucket
	public void test1() {
    	 Course course = new Course(false,1,2,true,25,"test Subject",407894);
	     assertEquals("success",StudentTable.getInstance().addCourseToStudent(4,course));
		}
	
	@Test
	//case 3 :fail to to register student for courses with Course Code less than 6 digits
	 public void test2() {
		Student student1 = new Student(100,"sandy@carleton.ca","sandy","PARTTIME");
		assertEquals(false,CourseTable.getInstance().addStudentToCourse(student1,405061));
		}
	
	@Test
	//case 4 :fail to create new title with Course Code not starting with department code [10,20,30 or 40]
	public void test3() {
		 Course course = new Course(false,1,2,true,25,"test Subject",407894);
		 StudentTable.getInstance().addCourseToStudent(4,course);
	     assertEquals("Courses already registered",StudentTable.getInstance().addCourseToStudent(4,course));
	     }

}
