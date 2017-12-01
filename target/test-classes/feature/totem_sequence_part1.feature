Feature: Totem Sequence for Part1
		
	Scenario: Sequence
		Given that after Setup term has occurred
        And before Start Registration event occurs
        And Clerk provides course information enforce prerequisites false, number of mid terms 1,number of assignments 2, final true, capsize 25, title CourseA and code 201041 for course creation 
		And course 201041 will be created
		And Clerk provides Student's information student email studentA@carleton.ca, student password studentA and student status PARTTIME for student creation
		And student studentA@carleton.ca will be created
		And Clerk provides course code 201041 for course deletion
		And course 201041 will be deleted
		And Clerk provides Student's information student email studentB@carleton.ca, student password studentB and student status PARTTIME for student creation
		And student studentB@carleton.ca will be created
		And Clerk provides course information enforce prerequisites false, number of mid terms 1,number of assignments 2, final true, capsize 25, title CourseC and code 201043 for course creation 
		And course 201043 will be created
		And Clerk provides course information enforce prerequisites false, number of mid terms 1,number of assignments 2, final true, capsize 25, title CourseB and code 201042 for course creation 
		And course 201042 will be created
		And Clerk provides student email studentA@carleton.ca for student deletion
		And student studentA@carleton.ca will be deleted
		And Clerk provides Student's information student email studentA@carleton.ca, student password shaktiman and student status PARTTIME for student creation
		And student studentA@carleton.ca will be created
		And Clerk provides course code 201042 for course deletion
		And course 201042 will be deleted
		And Clerk provides course information enforce prerequisites false, number of mid terms 1,number of assignments 2, final true, capsize 25, title courseA and code 201041 for course creation 
		And course 201041 will be created
		And after Start Registration event occurs 
		And student studentA@carleton.ca provides course code 201043 for registeration
		And student studentA@carleton.ca will be registered in the course 201043
		And student studentA@carleton.ca provides course code 201041 for registeration
		And student studentA@carleton.ca will be registered in the course 201041
		And student studentA@carleton.ca provides course code 201043 for deregisteration of the course
		And student studentA@carleton.ca is deregistered from the course 201043
		And student studentB@carleton.ca provides course code 201041 for registeration
		And student studentB@carleton.ca will be registered in the course 201041
		And after End DeRegistration event occurs
		When student studentB@carleton.ca provides course code 201041 for de-registeration of the course
		Then messsage Subject dropped as maximum time allocated has been reached(2 weeks)! is displayed and student studentB@carleton.ca is dropped from the course 201041
		
		
		 
		 
		
