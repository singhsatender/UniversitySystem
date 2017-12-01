Feature: Invalid Paths for Part1

	Scenario: Add Redundant Student
		Given that after Setup term has occurred
        And before Start Registration event occurs
		And Clerk provides Student's information student email singhA@carleton.ca, student password singh and student status PARTTIME for student creation
		And student singhA@carleton.ca will be created
		When Clerk provides same Student's information again
		Then message The User Already Exists! will be diplayed for the student's information student email singhA@carleton.ca, student password singh and student status PARTTIME for student creation
		
	Scenario: Add Redundant Course
		Given that after Setup term has occurred
        And before Start Registration event occurs
		And Clerk provides course information enforce prerequisites false, number of mid terms 1,number of assignments 2, final true, capsize 25, title courseS and code 409989 for course creation 
		And course 409989 will be created
		When Clerk provides same course information again
		Then message The Course Already Exists! will be diplayed for the course information enforce prerequisites false, number of mid terms 1,number of assignments 2, final true, capsize 25, title courseS and code 409989 for course creation
		
	Scenario: Register course before registration starts
		Given that after Setup term has occurred
        And before Start Registration event occurs
		And Clerk provides Student's information student email satenderK@carleton.ca, student password satender and student status PARTTIME for student creation
		And student satenderK@carleton.ca will be created
		And Clerk provides course information enforce prerequisites false, number of mid terms 1,number of assignments 2, final true, capsize 25, title courseR and code 409988 for course creation 
		And course 409988 will be created
		And before Start Registration event occurs 
		When student provides course code 409988 and student email satenderK@carleton.ca for registering the course
		Then error message Student registration yet not started ! will be and student satenderK@carleton.ca will not be registered in the course 409988
		
	Scenario: Register same course again
		Given that after Setup term has occurred
        And before Start Registration event occurs
		And Clerk provides Student's information student email satenderL@carleton.ca, student password satender and student status PARTTIME for student creation
		And student satenderL@carleton.ca will be created
		And Clerk provides course information enforce prerequisites false, number of mid terms 1,number of assignments 2, final true, capsize 25, title courseP and code 409987 for course creation 
		And course 409987 will be created
		And after Start Registration event occurs 
		And student provides course code 409987 and student email satenderL@carleton.ca for registering the course
		And student satenderL@carleton.ca will be registered in the course 409987 
		When student satenderL@carleton.ca tries to register course 409987 again
		Then error message Courses already registered ! will be displayed to the student satenderL@carleton.ca and course 409987
		
	Scenario: Deregister course which student has not registered yet
		Given that after Setup term has occurred
        And before Start Registration event occurs
		And Clerk provides Student's information student email satenderM@carleton.ca, student password satender and student status PARTTIME for student creation
		And student satenderM@carleton.ca will be created
		And Clerk provides course information enforce prerequisites false, number of mid terms 1,number of assignments 2, final true, capsize 25, title Mathematics and code 301345 for course creation 
		And course 301345 will be created
		And after Start Registration event occurs 
		When student provides course code 301345 and student email satenderM@carleton.ca for deregistering the course
		Then error message Course not registered yet! will be displayed to the student satenderM@carleton.ca for course 301345
		
	Scenario: Add Student after clerk’s student addition deadline is over
		Given that after Setup term has occurred
        And after Start Registration event occurs
		When Clerk provides Student's information student email singhM@carleton.ca, student password singh and student status PARTTIME for student creation
		Then message Maximum time allocated for student creation is over(20 days) will be diplayed for the student's information student email singhM@carleton.ca, student password singh and student status PARTTIME for student creation
	
	Scenario: Add Course after clerk’s course addition deadline is over
		Given that after Setup term has occurred
        And after Start Registration event occurs
		When Clerk provides course information enforce prerequisites false, number of mid terms 1,number of assignments 2, final true, capsize 25, title Software Engineering and code 409986 for course creation 
		Then message Maximum time allocated for course creation is over(20 days) will be diplayed for the course information enforce prerequisites false, number of mid terms 1,number of assignments 2, final true, capsize 25, title Software Engineering and code 201345 for course creation
	
	Scenario: Remove non-existent course
		Given that after Setup term has occurred
        And before Start Registration event occurs
		When Clerk provides course information as course code 601345 for course deletion 
		Then message Course Doesnot exist! will be diplayed for the course code 601345
	
	Scenario: Remove non-existent student
		Given that after Setup term has occurred
        And before Start Registration event occurs
		When Clerk provides Student's information student email Antman@carleton.ca for student deletion
		Then message The Student Does Not Exist! will be diplayed for the student's information student email Antman@carleton.ca for student deletion
		
    Scenario: Remove non-existent student after term ended
		Given that after Term Ended event has occurred
		When Clerk provides Student's information student email Antman@carleton.ca for student deletion
		Then message The Student Does Not Exist! will be diplayed for the student's information student email Antman@carleton.ca for student deletion
		
	
		
		
		
		
		
		
		
		 
		 
		
