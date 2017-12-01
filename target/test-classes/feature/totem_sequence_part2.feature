Feature: Totem Sequence for Part2
		
	Scenario: Sequence
		Given that after Setup term has occurred
        And before Start Registration event occurs
		And Clerk provides Student's information student email khal@carleton.ca, student password khal and student status PARTTIME for student creation
		And student khal@carleton.ca will be created
		And Clerk provides course information enforce prerequisites false, number of mid terms 1,number of assignments 2, final true, capsize 25, title Sanskrit and code 401985 for course creation 
		And course 401985 will be created
		And after Start Registration event occurs 
		And student provides course code 401985 and student email khal@carleton.ca for registering the course
		And student khal@carleton.ca will be registered in the course 401985
		When after End Term event has occurred
		Then Student khal@carleton.ca get marks
		
		 
		 
		
