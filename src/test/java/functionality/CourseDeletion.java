package functionality;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.server.logic.tables.CourseTable;

public class CourseDeletion {

	@BeforeClass
	//set up the initial table
	public static void initialTitle(){
		CourseTable.getInstance();
	}
	
	@Test
	//case 1 :Successfully destroy/delete a course
	public void test() {
		assertEquals("success",CourseTable.getInstance().destroyCourse(405060));
		}
	
	@Test
	//case 2 :fail to destroy/delete Course Code as course code is invalid
	public void test1() {
		assertEquals("Course Doesnot exist",CourseTable.getInstance().destroyCourse(405067));
		}
	
}
