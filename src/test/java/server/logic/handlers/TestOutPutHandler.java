package server.logic.handlers;
import static org.junit.Assert.*;

import org.junit.Test;

import com.server.logic.handler.OutputHandler;
import com.server.logic.handler.model.Output;


public class TestOutPutHandler {
	
	OutputHandler outputhandler = new OutputHandler();
	
	@Test
	//case 1 :Successfully create new student
	public void test() {	
		Output output = new Output("Success!",2);
		assertEquals(output,outputhandler.createStudent("flash@carleton.ca,flash,PARTTIME"));
		}
	
	@Test
	//case 2 :failed to create new student with less data
	public void test1() {	
		Output output = new Output("Your input should in this format:'StudentEmail,password,Status [FULLTIME or PARTTIME]'",4);
		assertEquals(output,outputhandler.createStudent("flash@carleton.ca,flash"));
		}
	
	
	@Test
	//case 3 :Successfully create new course
	public void test2() {	
		Output output = new Output("Success!",2);
		assertEquals(output,outputhandler.createCourse("false,1,2,true,25,test subject,201345"));
		}
	
	@Test
	//case 4 :failed to create new course with less data
	public void test3() {	
		Output output = new Output("Your input should in this format:'enforcePrereqs: true or false,numberOfMidterms, numberOfAssignments,hasAFinal:true or false,MaxStudents,title,CourseCode\n"
	            			+ "Note: Course code should conatin 6 digits, first 2 digits contains department code [CS-10,EC-20,EBT-30,EE-40] and last 4 course code.'",5);
		assertEquals(output,outputhandler.createCourse("false,1,2,true,25,test subject"));
		}
	
	@Test
	//case 5 :failed to create new course with course code less than 6 digits
	public void test4() {	
		Output output = new Output("Your input should in this format:'enforcePrereqs: true or false,numberOfMidterms, numberOfAssignments,hasAFinal:true or false,MaxStudents,title,CourseCode\n"
        			+ "Note: Course code should conatin 6 digits, first 2 digits contains department code [CS-10,EC-20,EBT-30,EE-40] and last 4 course code.'"
        			+ "\n Error: Course code length is wrong",5);
		assertEquals(output,outputhandler.createCourse("false,1,2,true,25,test1 subject,4050"));
		}
	
	@Test
	//case 6 :fail to create new title with Course Code not starting with department code [10,20,30 or 40]
	public void test6() {	
		Output output = new Output("Your input should in this format:'enforcePrereqs: true or false,numberOfMidterms, numberOfAssignments,hasAFinal:true or false,MaxStudents,title,CourseCode\n"
        			+ "Note: Course code should conatin 6 digits, first 2 digits contains department code [CS-10,EC-20,EBT-30,EE-40] and last 4 course code.'"
        			+ "\n Error: department code is wrong",5);
		assertEquals(output,outputhandler.createCourse("false,1,2,true,25,test1 subject,004050"));
		}
	
	@Test
	//case 7 :fail to create new title with Course Code not starting with department code [10,20,30 or 40]
	public void test7() {	
		Output output = new Output("Your input should in this format:'enforcePrereqs: true or false,numberOfMidterms, numberOfAssignments,hasAFinal:true or false,MaxStudents,title,CourseCode\n"
        			+ "Note: Course code should conatin 6 digits, first 2 digits contains department code [CS-10,EC-20,EBT-30,EE-40] and last 4 course code.'"
        			+ "\n Error:No grading elemnt present.There should be atlease one grade element(numberOfMidterms or numberOfAssignments or hasAFinal)",5);
		assertEquals(output,outputhandler.createCourse("false,0,0,false,25,test1 subject,204050"));
		}
	
	@Test
	//case 8 :delete student successfully
	public void test8() {	
		Output output = new Output("Success!",2);
		assertEquals(output,outputhandler.deleteStudent("Superman@carleton.ca"));
		}
	
	@Test
	//case 9 :failed to delete student without studentemail.
	public void test9() {	
		Output output = new Output("Your input should in this format:'studentemail'",7);
		assertEquals(output,outputhandler.deleteStudent(""));
		}
	
	@Test
	//case 10 :successful student login
	public void test10() {	
		Output output = new Output("What can I do for you?Menu:\nregister course\nderegister course\nlog out",3);
		assertEquals(output,outputhandler.studentLogin("Wonderwomen@carleton.ca,wonderwomen"));
		}
	
	@Test
	//case 11 :failed to login student with wrong password
	public void test11() {	
		Output output = new Output("Wrong Password!Please Input Username and Password:'studentemail,password'",15);
		assertEquals(output,outputhandler.studentLogin("Wonderwomen@carleton.ca,wonder"));
		}
}
