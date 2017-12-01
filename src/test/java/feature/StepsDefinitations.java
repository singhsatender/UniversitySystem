package feature;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.*;

import com.server.logic.handler.OutputHandler;
import com.server.logic.handler.model.Output;
import com.server.logic.model.Course;
import com.server.logic.model.Student;
import com.server.logic.model.University;
import com.server.logic.tables.CourseTable;
import com.server.logic.tables.StudentTable;
import com.server.logic.tables.UniversityTable;


public class StepsDefinitations {
	
	//Step definitions for Part 1 -start
	@Given("^that (.*) Setup term has occurred$")
	public void that_after_Setup_term_has_occurred(String term) throws Throwable {
		University university= new University();
		if(term.equalsIgnoreCase("after")){
			university.termEnded=false;
		}
	}

	@Given("^(.*) Start Registration event occurs$")
	public void before_Start_Registration_event_occurs(String registration) throws Throwable {
		University university= new University();
		if(registration.equalsIgnoreCase("before")){
			university.clerkActivities=true;
		}else{
			university.clerkActivities=false;
		}
	}

	@When("^Clerk provides Student's information student email (.*), student password (.*) and student status (.*) for student creation$")
	public void clerk_creates_a_student(String studentEmail,String studentPass,String studentStatus) throws Throwable {
		OutputHandler outputhandler = new OutputHandler();
		outputhandler.createStudent(studentEmail+","+studentPass+","+studentStatus);	
	}

	@Then("^student (.*) will be created$")
	public void student_creation(String studentEmail) throws Throwable {		
		assert(StudentTable.getInstance().lookup(studentEmail)!=(-1));
	}

	@When("^Clerk provides course information enforce prerequisites (.*), number of mid terms (\\d+),number of assignments (\\d+), final (.*), capsize (\\d+), title (.*) and code (\\d+) for course creation$")
	public void clerk_creates_a_course(boolean enforcePrereqs, int numberOfMidterms, int numberOfAssignments, boolean hasAFinal, int capsize,
			String title, int myCode) {
		OutputHandler outputhandler = new OutputHandler();
		outputhandler.createCourse(enforcePrereqs+","+ numberOfMidterms+","+numberOfAssignments+","+ hasAFinal+","+capsize+","+title+","+myCode);
	}
	
	@Then("^course (\\d+) will be created$")
	public void clerk_created_course(int code) throws Throwable {
		assertTrue(CourseTable.getInstance().lookup(code));
	}
	
	@When("^Clerk provides course code (.*) for course deletion$")
	public void clerk_provides_course_code_for_course_deletion(String coursecode) throws Throwable {
		OutputHandler outputhandler = new OutputHandler();
		outputhandler.destroyACourse(coursecode);		
	}

	@Then("^course (\\d+) will be deleted$")
	public void course_will_be_deleted(int coursecode) throws Throwable {
		assertEquals(false,CourseTable.getInstance().lookup(coursecode));
	}
	
	@When("^Clerk provides student email (.*) for student deletion$")
	public void clerk_provides_student_email_for_student_deletion(String studentEmail ) throws Throwable {
		OutputHandler outputhandler = new OutputHandler();
		outputhandler.deleteStudent(studentEmail);
	}

	@Then("^student (.*) will be deleted$")
	public void student_will_be_deleted(String studentEmail) throws Throwable {
		assert(StudentTable.getInstance().lookup(studentEmail)==(-1));
	}
	
	@When("^student provides course code (\\d+) and student email (.*) for registering the course$")
	public void student_registration(int coursecode,String studentEmail) throws Throwable {
		OutputHandler outputhandler = new OutputHandler();
		outputhandler.register(studentEmail+","+coursecode);
	}

	@Then("^student (.*) will be registered in the course (\\d+)$")
	public void student_will_be_registered_in_the_course(String studentEmail,int coursecode) throws Throwable {
		int studentId=StudentTable.getInstance().lookup(studentEmail);
		Course selCourse= CourseTable.getInstance().searchCourse(coursecode);
		Student selStudent= StudentTable.getInstance().searchStudent(studentId);
		assertTrue((StudentTable.getInstance().checkCourseInStudent(studentId, selCourse))&&(CourseTable.getInstance().checkStudentInCourse(selStudent, coursecode)));
	}

	@When("^student (.*) provides course code (\\d+) for deregisteration of the course$")
	public void student_provide_course_code_for_deregisteration_of_the_course(String studentEmail,int coursecode) throws Throwable {
		OutputHandler outputhandler = new OutputHandler();
		outputhandler.deRegister(studentEmail+","+coursecode);
	}

	@Then("^student (.*) is deregistered from the course (\\d+)$")
	public void student_is_deregistered_from_the_course(String studentEmail,int coursecode) throws Throwable {
		int studentId=StudentTable.getInstance().lookup(studentEmail);
		Course selCourse= CourseTable.getInstance().searchCourse(coursecode);
		Student selStudent= StudentTable.getInstance().searchStudent(studentId);
		assertTrue(!((StudentTable.getInstance().checkCourseInStudent(studentId, selCourse))&&(CourseTable.getInstance().checkStudentInCourse(selStudent, coursecode))));
	}
	
	@When("^student (.*) provides course code (\\d+) for de-registeration of the course$")
	public void student_de_registeration_of_the_course(String studentEmail,int coursecode) throws Throwable {
	    // For Readability only
	}
	
	//redundant student creation
	@When("^Clerk provides same Student's information again$")
	public void clerk_provides_same_Student_s_information_again() throws Throwable {
	    //For Readablity only
	}

	@Then("^message (.*) will be diplayed for the student's information student email (.*), student password (.*) and student status (.*) for student creation$")
	public void redundant_student_creation(String message,String studentEmail,String studentPass,String studentStatus) throws Throwable {
		OutputHandler outputhandler = new OutputHandler();
		Output output = new Output(message,2);
		assertEquals(output,outputhandler.createStudent(studentEmail+","+studentPass+","+studentStatus));
	}
  
	//redundant course creation
	@When("^Clerk provides same course information again$")
	public void clerk_provides_same_course_information_again() throws Throwable {
		//For Readablity only
	}

	@Then("^message (.*) will be diplayed for the course information enforce prerequisites (.*), number of mid terms (\\d+),number of assignments (\\d+), final (.*), capsize (\\d+), title (.*) and code (\\d+) for course creation$")
	public void redundant_course_creation(String message,boolean enforcePrereqs, int numberOfMidterms, int numberOfAssignments, boolean hasAFinal, int capsize,
			String title, int myCode) throws Throwable {
		OutputHandler outputhandler = new OutputHandler();
		Output output = new Output(message,2);
		assertEquals(output,outputhandler.createCourse(enforcePrereqs+","+ numberOfMidterms+","+numberOfAssignments+","+ hasAFinal+","+capsize+","+title+","+myCode));	
	}

	@Then("^error message (.*) will be and student (.*) will not be registered in the course (\\d+)$")
	public void student_register_before_time(String message,String studentEmail,int courseCode) throws Throwable {
		OutputHandler outputhandler = new OutputHandler();
		Output output = new Output(message,10);
		assertEquals(output,outputhandler.register(studentEmail+","+ courseCode));
	}

	@When("^student (.*) tries to register course (\\d+) again$")
	public void student_register_course_again(String studentEmail,int arg1) throws Throwable {
	   //For Readablity Only
	}

	@Then("^error message (.*) will be displayed to the student (.*) and course (\\d+)$")
	public void student_register_course_again(String message,String studentEmail,int courseCode) throws Throwable {
		OutputHandler outputhandler = new OutputHandler();
		Output output = new Output(message,10);
		assertEquals(output,outputhandler.register(studentEmail+","+ courseCode));
	}
	
	@When("^student provides course code (\\d+) and student email (.*) for deregistering the course$")
	public void student_deregisters_without_registering(int arg1,String studentEmail) throws Throwable {
	    //For readability only
	}

	@Then("^error message (.*) will be displayed to the student (.*) for course (\\d+)$")
	public void student_deregistration_attempt(String message,String studentEmail,int courseCode) throws Throwable {
		OutputHandler outputhandler = new OutputHandler();
		Output output = new Output(message,3);
		assertEquals(output,outputhandler.deRegister(studentEmail+","+ courseCode));
	}	

	@When("^Clerk provides course information as course code (\\d+) for course deletion$")
	public void clerk_provides_course_information_as_course_code_for_course_deletion(int arg1) throws Throwable {
	   //For readability only
	}

	@Then("^message (.*) will be diplayed for the course code (.*)$")
	public void remove_nonexistent_course(String message, String courseCode) throws Throwable {
		OutputHandler outputhandler = new OutputHandler();
		Output output = new Output(message,2);
		assertEquals(output,outputhandler.destroyACourse(courseCode));
	}

	@When("^Clerk provides Student's information student email Antman@carleton\\.ca for student deletion$")
	public void clerk_provides_Student_s_information_student_email_singh_carleton_ca_for_student_deletion() throws Throwable {
		//For readability only
	}
	
	@Then("^message (.*) will be diplayed for the student's information student email (.*) for student deletion$")
	public void remove_nonexistent_student(String message, String studentEmail) throws Throwable {
		OutputHandler outputhandler = new OutputHandler();
		Output output = new Output(message,7);
		assertEquals(output,outputhandler.deleteStudent(studentEmail));
	}

	@Given("^that (.*) Term Ended event has occurred$")
	public void Term_Ended_event_has_occurred(String term) throws Throwable {
		University university= new University();
		if(term.equalsIgnoreCase("before")){
			university.termEnded=false;
		}else{
			university.termEnded=true;
		}
	}
	
	@Given("^(.*) End DeRegistration event occurs$")
	public void after_End_DeRegistration_event_occurs(String dropDate) throws Throwable {
		University university= new University();
		if(dropDate.equalsIgnoreCase("before")){
			university.dropSubject=true;
		}else{
			university.dropSubject=false;
		}
	}

	@Then("^messsage (.*) is displayed and student (.*) is dropped from the course (\\d+)$")
	public void student_drops_course(String message,String studentEmail,int courseCode) throws Throwable {
		OutputHandler outputhandler = new OutputHandler();
		Output output = new Output(message,3);
		assertEquals(output,outputhandler.deRegister(studentEmail+","+ courseCode));
	}
	
	@Given("^student (.*) provides course code (\\d+) for registeration$")
	public void student_registeration(String studentEmail,int coursecode) throws Throwable {
		OutputHandler outputhandler = new OutputHandler();
		outputhandler.register(studentEmail+","+coursecode);
	}
	
	//Step definitions for Part 1 -end
	
   //Step definitions for Part 2 -start
	@When("^(.*) End Term event has occurred$")
	public void End_Term_event(String term) throws Throwable {
		University university= new University();
		if(term.equalsIgnoreCase("before")){
			university.termEnded=false;
		}else{
			university.termEnded=true;
		}
		UniversityTable univTable = new UniversityTable();
	    univTable.submitMarks();
	}

	@Then("^Student (.*) get marks$")
	public void student_get_marks(String studentEmail) throws Throwable {
	    //checkStudentMarks
		assert(StudentTable.getInstance().checkStudentMarks(studentEmail)!=(-1));
	}
		
	int studentCourseCount = 0;
	
	@Given("^student (.*) checks the current courses he is enrolled in$")
	public void student_current_courses_enrolled(String studentEmail) throws Throwable {
		studentCourseCount = StudentTable.getInstance().checkStudentCourses(studentEmail);
	}

	@Given("^student (.*) checks list of courses offered this term$")
	public void list_of_courses_offered(String studentEmail) throws Throwable {
		assert(CourseTable.getInstance().countCourseList()!= (-1));
	}

	@When("^Again student checks the current courses he is enrolled in$")
	public void again_student_checks_the_current_courses_he_is_enrolled_in() throws Throwable {
	    //For Readablity Only
	}

	@Then("^it is confirmed that current courses have not changed for student (.*) and these two activities are independent of each other$")
	public void courses_enrolled_independence(String studentEmail) throws Throwable {
		assert(StudentTable.getInstance().checkStudentCourses(studentEmail)== studentCourseCount);
	}
	
    int courseCount = 0;
    
	@Given("^student (.*) confirms list of courses offered this term$")
	public void student_confirms_offered_courses(String studentEmail) throws Throwable {
	    courseCount = CourseTable.getInstance().countCourseList();
	}
	
	@When("^Again student (.*) checks list of courses offered this term$")
	public void again_student_ankur_carleton_ca_checks_list_of_courses_offered_this_term(String studentEmail) throws Throwable {
	    // For Readablity Only
	}

	@Then("^it is confirmed that list of courses have not changed for student (.*) and these two activities are independent of each other$")
	public void courses_offered_independence(String studentEmail) throws Throwable {
		assert(CourseTable.getInstance().countCourseList()== courseCount);
	}
	
	@Given("^student (.*) counts the current courses he is enrolled in$")
	public void student_counts_current_courses(String studentEmail) throws Throwable {
		assert(StudentTable.getInstance().checkStudentCourses(studentEmail)!=-1);
	}
	
	@Given("^clerk counts the list of students in the system$")
	public void clerk_counts_students() throws Throwable {
		assert(StudentTable.getInstance().countStudents()!=(-1));
	}
	
	int noOfStudents =0;
	
	@Given("^clerk checks the list of students in the system$")
	public void clerk_checks_students_in_the_system() throws Throwable {
		noOfStudents=StudentTable.getInstance().countStudents();
	}

	@When("^Again clerk checks the list of students in the system$")
	public void again_clerk_checks_the_list_of_students_in_the_system() throws Throwable {
	    // For Readability only
	}

	@Then("^it is confirmed that list of students has not changed and these two activities are independent of each other$")
	public void list_of_students_independence() throws Throwable {
		assert(StudentTable.getInstance().countStudents()== noOfStudents);
	}
	
	//Step definitions for Part 2 -end
}
