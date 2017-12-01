package functionality;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.server.logic.tables.CourseTable;

public class CourseCreation {

	@BeforeClass
	//set up the initial table
	public static void initialTitle(){
		CourseTable.getInstance();
	}
	
	@Test
	//case 1 :Successfully create new course
	public void test() {
		assertEquals(true,CourseTable.getInstance().createCourse(false,1,2,true,25,"Mobile Computing",201345));
		}
	
	@Test
	//case 2 :fail to create new title with existed Course Code
	public void test1() {
		assertEquals(false,CourseTable.getInstance().createCourse(false,1,2,true,25,"Mobile Computing",201345));
		}
	


}
