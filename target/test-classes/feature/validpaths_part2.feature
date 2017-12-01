Feature: Valid Paths for Part2

    Background:
       Given that after Setup term has occurred
        And before Start Registration event occurs
		
	Scenario: Student get marks
		Given Clerk provides Student's information student email satenderE@carleton.ca, student password satendere and student status PARTTIME for student creation
		And student satenderE@carleton.ca will be created
		And Clerk provides course information enforce prerequisites false, number of mid terms 1,number of assignments 2, final true, capsize 25, title Game Animation and code 209999 for course creation 
		And course 209999 will be created
		And after Start Registration event occurs 
		And student provides course code 209999 and student email satenderE@carleton.ca for registering the course
		And student satenderE@carleton.ca will be registered in the course 209999
		When after End Term event has occurred
		Then Student satenderE@carleton.ca get marks
		
		Scenario: Independence of courses offered and student’s registered courses
		Given Clerk provides Student's information student email ankur@carleton.ca, student password ankur and student status PARTTIME for student creation
		And student ankur@carleton.ca will be created
		And Clerk provides course information enforce prerequisites false, number of mid terms 1,number of assignments 2, final true, capsize 25, title Software and code 201555 for course creation 
		And course 201555 will be created
		And student ankur@carleton.ca checks the current courses he is enrolled in
		And student ankur@carleton.ca checks list of courses offered this term
		When Again student checks the current courses he is enrolled in 
		Then it is confirmed that current courses have not changed for student ankur@carleton.ca and these two activities are independent of each other 
		

		Scenario: Independence of student’s registered courses and courses offered
		Given Clerk provides Student's information student email satenderF@carleton.ca, student password satenderf and student status PARTTIME for student creation
		And student satenderF@carleton.ca will be created
		And Clerk provides course information enforce prerequisites false, number of mid terms 1,number of assignments 2, final true, capsize 25, title courseW and code 409994 for course creation 
		And course 409994 will be created
		And student satenderF@carleton.ca confirms list of courses offered this term
		And student satenderF@carleton.ca counts the current courses he is enrolled in
		When Again student satenderF@carleton.ca checks list of courses offered this term
		Then it is confirmed that list of courses have not changed for student satenderF@carleton.ca and these two activities are independent of each other 
			
		Scenario: Independence of student’s registered courses and list of students
		Given Clerk provides Student's information student email ankurG@carleton.ca, student password ankurg and student status PARTTIME for student creation
		And student ankurG@carleton.ca will be created
		And Clerk provides course information enforce prerequisites false, number of mid terms 1,number of assignments 2, final true, capsize 25, title courseV and code 409993 for course creation 
		And course 409993 will be created
		And student ankurG@carleton.ca checks the current courses he is enrolled in
		And clerk counts the list of students in the system
		When Again student checks the current courses he is enrolled in 
		Then it is confirmed that current courses have not changed for student ankurG@carleton.ca and these two activities are independent of each other 
		
		Scenario: Independence of list of students and student’s registered courses
		Given Clerk provides Student's information student email ankurH@carleton.ca, student password ankur and student status PARTTIME for student creation
		And student ankurH@carleton.ca will be created
		And Clerk provides course information enforce prerequisites false, number of mid terms 1,number of assignments 2, final true, capsize 25, title courseY and code 409992 for course creation 
		And course 409992 will be created
		And  clerk checks the list of students in the system
		And student ankurH@carleton.ca counts the current courses he is enrolled in
		When Again clerk checks the list of students in the system
		Then it is confirmed that list of students has not changed and these two activities are independent of each other 
		
		Scenario: Independence of courses offered and list of students
		Given Clerk provides Student's information student email ankurI@carleton.ca, student password ankur and student status PARTTIME for student creation
		And student ankurI@carleton.ca will be created
		And Clerk provides course information enforce prerequisites false, number of mid terms 1,number of assignments 2, final true, capsize 25, title courseU and code 409991 for course creation 
		And course 409991 will be created
		And clerk checks the list of students in the system
		And student ankurI@carleton.ca checks list of courses offered this term
		When Again clerk checks the list of students in the system
		Then it is confirmed that list of students has not changed and these two activities are independent of each other 
		
		Scenario: Independence of list of students and courses offered
		Given Clerk provides Student's information student email ankurJ@carleton.ca, student password ankur and student status PARTTIME for student creation
		And student ankurJ@carleton.ca will be created
		And Clerk provides course information enforce prerequisites false, number of mid terms 1,number of assignments 2, final true, capsize 25, title coourseT and code 409990 for course creation 
		And course 409990 will be created
		And student ankurJ@carleton.ca confirms list of courses offered this term
		And clerk counts the list of students in the system
		When Again student ankurJ@carleton.ca checks list of courses offered this term
		Then it is confirmed that list of courses have not changed for student ankurJ@carleton.ca and these two activities are independent of each other 
		
		
