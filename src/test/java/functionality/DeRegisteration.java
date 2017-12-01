package functionality;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.server.logic.model.Course;
import com.server.logic.model.Student;
import com.server.logic.tables.CourseTable;
import com.server.logic.tables.StudentTable;

public class DeRegisteration {

	@BeforeClass
	//set up the initial table
	public static void initialTitle(){
		CourseTable.getInstance();
	}
	
	@Test
	//case 1 :Successfully able to register student for courses
	
	public void test() {
		Student student1 = StudentTable.getInstance().searchStudent(1);	
		CourseTable.getInstance().addStudentToCourse(student1,405060);
		assertEquals(true,CourseTable.getInstance().removeStudent(student1,405060));
		}
	
    @Test
	//case 2 :Successfully able to remove course from student bucket
	public void test1() {
    	 Course course = new Course(false,1,2,true,25,"test Subject",407894);
    	 StudentTable.getInstance().addCourseToStudent(4,course);
	     assertEquals("success",StudentTable.getInstance().deRegisterCourse(4,course));
		}
	
	@Test
	//case 3 :fail to to deregister student for courses without registration
	 public void test2() {
		Student student1 = StudentTable.getInstance().searchStudent(1);	
		assertEquals(true,CourseTable.getInstance().removeStudent(student1,405060));
		}
	
	@Test
	//case 4 :fail to remove student without prior registration
	public void test3() {
		 Course course = new Course(false,1,2,true,25,"test Subject",407894);
	     assertEquals("Course not registered yet",StudentTable.getInstance().deRegisterCourse(4,course));  
	     }

}
