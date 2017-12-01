package functionality;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import com.server.logic.tables.StudentTable;
import com.utilities.Trace;

public class StudentDeletion {

	private Logger logger = Trace.getInstance().getLogger("opreation_file");
	
	@BeforeClass
	//set up the initial table
	public static void initialUser(){
		StudentTable.getInstance();
	}

	@Test
	//case 1 :Successfully deleted existing student
	public void test() {
		assertEquals("success",StudentTable.getInstance().deleteStudent(1));
		logger.info("Operation:Test Student deletion");
		}
	
	@Test
	//case 2 :Fail to delete student since student id not valid
	public void test1() {
		assertEquals("The Student Does Not Exist",StudentTable.getInstance().deleteStudent(100));
		}	
	
}
