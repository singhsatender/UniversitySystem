Feature: Valid Paths for Part1

   Background:
       Given that after Setup term has occurred
        And before Start Registration event occurs

	Scenario: Clerk creates a student
		When Clerk provides Student's information student email satender@carleton.ca, student password satender and student status PARTTIME for student creation
		Then student satender@carleton.ca will be created
		
	Scenario: Clerk creates a course
		When Clerk provides course information enforce prerequisites false, number of mid terms 1,number of assignments 2, final true, capsize 25, title Software Engineering and code 201345 for course creation 
		Then course 201345 will be created
		
	Scenario: Clerk remove a student
		Given Clerk provides Student's information student email satenderA@carleton.ca, student password satendera and student status PARTTIME for student creation
		And student satenderA@carleton.ca will be created
		When Clerk provides student email satenderA@carleton.ca for student deletion
		Then student satenderA@carleton.ca will be deleted
		
	Scenario: Clerk removes a course
		Given Clerk provides course information enforce prerequisites false, number of mid terms 1,number of assignments 2, final true, capsize 25, title CourseZ and code 409998 for course creation 
		And course 409998 will be created
		When Clerk provides course code 409998 for course deletion
		Then course 409998 will be deleted
		
	Scenario: Register student for a course
		Given Clerk provides Student's information student email satenderB@carleton.ca, student password satenderb and student status PARTTIME for student creation
		And student satenderB@carleton.ca will be created
		And Clerk provides course information enforce prerequisites false, number of mid terms 1,number of assignments 2, final true, capsize 25, title CourseY and code 409997 for course creation 
		And course 409997 will be created
		And after Start Registration event occurs 
		When student provides course code 409997 and student email satenderB@carleton.ca for registering the course
		Then student satenderB@carleton.ca will be registered in the course 409997
		
	Scenario: DeRegister student for a course
		Given Clerk provides Student's information student email satenderC@carleton.ca, student password satenderc and student status PARTTIME for student creation
		And student satenderC@carleton.ca will be created
		And Clerk provides course information enforce prerequisites false, number of mid terms 1,number of assignments 2, final true, capsize 25, title CourseX and code 409996 for course creation 
		And course 409996 will be created
		And after Start Registration event occurs 
		And student provides course code 409996 and student email satenderC@carleton.ca for registering the course
		And student satenderC@carleton.ca will be registered in the course 409996
		When student satenderC@carleton.ca provides course code 409996 for deregisteration of the course
		Then student satenderC@carleton.ca is deregistered from the course 409996
		
		
	Scenario: Drop course
		Given Clerk provides Student's information student email satenderD@carleton.ca, student password satenderd and student status PARTTIME for student creation
		And student satenderD@carleton.ca will be created
		And Clerk provides course information enforce prerequisites false, number of mid terms 1,number of assignments 2, final true, capsize 25, title Physics and code 409995 for course creation 
		And course 409995 will be created
		And after Start Registration event occurs 
		And student provides course code 409995 and student email satenderD@carleton.ca for registering the course
		And student satenderD@carleton.ca will be registered in the course 409995
		And after End DeRegistration event occurs
		When student satenderD@carleton.ca provides course code 409995 for de-registeration of the course
		Then messsage Subject dropped as maximum time allocated has been reached(2 weeks)! is displayed and student satenderD@carleton.ca is dropped from the course 409995
		
		

		
		
		 
		 
		
