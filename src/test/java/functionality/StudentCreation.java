package functionality;

import static org.junit.Assert.*;

//import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import com.server.logic.tables.StudentTable;

public class StudentCreation {

	//private Logger logger = Trace.getInstance().getLogger("opreation_file");
	
	@BeforeClass
	//set up the initial table
	public static void initialUser(){
		StudentTable.getInstance();
	}

	@Test
	//case 1 :Successfully create user test
	public void test() {
		assertEquals(true,StudentTable.getInstance().createStudent("Jhon@carleton.ca","jhon","PARTTIME"));
		//logger.info("Operation:Test Student creation");
		}
	
	@Test
	//case 2 :Fail to create an existed user
	public void test1() {
		assertEquals(false,StudentTable.getInstance().createStudent("Superman@carleton.ca","superman","PARTTIME"));
		}
}
