package com.server.logic.handler;

import com.server.logic.handler.model.Output;
import com.server.logic.handler.model.ServerOutput;

public class InputHandler {
	public static final int WAITING = 0;
	public static final int FINISHWAITING=1;
    public static final int CLERK = 2;
    public static final int STUDENT = 3;
    public static final int CREATESTUDENT=4;
    public static final int CREATECOURSE=5;
    public static final int CHECKMARKS=6;
    public static final int DELETESTUDENT=7;
    public static final int DESTROYCOURSE=8;
    public static final int CANCELCOURSE=9;
    public static final int REGISTER=10;
    public static final int DEREGISTER=11;
    public static final int DROPCOURSE=12;
    public static final int PAYFINE=13;
    public static final int CLERKLOGIN=14;
    public static final int STUDENTLOGIN=15;
    
    OutputHandler outputHandler=new OutputHandler();


	public ServerOutput processInput(String input, int state) {
		 String output = "";
		 Output o = new Output("",0);
		 ServerOutput oo = new ServerOutput(output,o.getState());
	        if (state == WAITING) {
	        	output = "Who Are you?Clerk or Student?";
	            state = FINISHWAITING;
	            oo.setOutput(output);
	            oo.setState(state);
	         }else if (state == FINISHWAITING) {
	            if (input.equalsIgnoreCase("clerk")) {
	            	output="Please Input The Password:";
	            	state=CLERKLOGIN;
	                oo.setOutput(output);
		            oo.setState(state);
	            }else if (input.equalsIgnoreCase("Student")) {
	            	output="Please Input Studentemail and Password:'studentemail,password'";
	            	state=STUDENTLOGIN;
	                oo.setOutput(output);
		            oo.setState(state);
	            }else{
	            	output = "Who Are you?Clerk or Student?";
	            	state = FINISHWAITING;
	            	oo.setOutput(output);
		            oo.setState(state);
	            }
	        }else if(state==CLERKLOGIN){
	        	o=outputHandler.clerkLogin(input);
        		output=o.getOutput();
        		state=o.getState();
        		oo.setOutput(output);
	            oo.setState(state);
	        }else if(state==STUDENTLOGIN){
	        	o=outputHandler.studentLogin(input);
        		output=o.getOutput();
        		state=o.getState();
        		oo.setOutput(output);
	            oo.setState(state);
	        }else if (state==CLERK){
	        	if (input.equalsIgnoreCase("create student")) {
	            	output = "Please Input Student Info:'StudentEmail,password,Status [FULL TIME or PART TIME]'";
	            	state=CREATESTUDENT;
	            	oo.setOutput(output);
		            oo.setState(state);
	            }else if (input.equalsIgnoreCase("create course")) {
	            	output = "Please Input Title Info:'enforcePrereqs: true or false,numberOfMidterms, numberOfAssignments,hasAFinal:true or false,MaxStudents,title,CourseCode\n"
	            			+ "Note: Course code should conatin 6 digits, first 2 digits contains department code [CS-10,EC-20,EBT-30,EE-40] and last 4 course code.'\n"
	            			+ "Note: There should be atlease one grade element(numberOfMidterms or numberOfAssignments or hasAFinal)";
	            	state=CREATECOURSE;
	            	oo.setOutput(output);
		            oo.setState(state);
	            }else if (input.equalsIgnoreCase("delete student")) {
	            	output = "Please Input User Info:'studentemail'";
	            	state=DELETESTUDENT;
	            	oo.setOutput(output);
		            oo.setState(state);
	            }else if (input.equalsIgnoreCase("destroy course")) {
		            output = "Please Input Title Info:'Course Code'";
		            state=DESTROYCOURSE;
		            oo.setOutput(output);
		            oo.setState(state);
	            }else if (input.equalsIgnoreCase("cancel course")) {
	            	output = "Please Input Title Info:'Course Code'";
	            	state=CANCELCOURSE;
	            	oo.setOutput(output);
		            oo.setState(state);
	            }else if (input.equalsIgnoreCase("check Marks")) {
	            	output = "List of all the students above 85 will be displayed. Press Yes to continue else Log out";
	            	state=CHECKMARKS;
	            	oo.setOutput(output);
		            oo.setState(state);
	            }else if(input.equalsIgnoreCase("log out")){
	            	output = "Successfully Log Out!";
	                state = WAITING;
	                oo.setOutput(output);
		            oo.setState(state);
	            }else if(input.equalsIgnoreCase("main menu")){
	        		output = "What can I do for you?Menu:\ncreate student\ncreate course\ndelete student\ndestroy course\ncancel course\ncheck marks\nlog out";
	                state = CLERK;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else{
	            	output = "What can I do for you?Menu:\ncreate student\ncreate course\ndelete student\ndestroy course\ncancel course\ncheck marks\nlog out";
	                state = CLERK;
	                oo.setOutput(output);
		            oo.setState(state);
	            }
	        }else if (state==STUDENT){
	        	if (input.equalsIgnoreCase("Register course")) {
	            	output = "Please select one course from below list in the format:'studentemail,course code'\ncourse code\tcourse Name ";
	            	//method to show university courses
	            	output=output+ outputHandler.displayCourses();	            	
	            	state=REGISTER;
	            	oo.setOutput(output);
		            oo.setState(state);
	            }else if (input.equalsIgnoreCase("deregister course")) {
	            	output = "Please Input Title Info:'studentemail,course code'";
	            	state=DEREGISTER;
	            	oo.setOutput(output);
		            oo.setState(state);
	            }else if(input.equalsIgnoreCase("log out")){
	            	output = "Successfully Log Out!";
	                state = WAITING;
	                oo.setOutput(output);
		            oo.setState(state);
	            }else if(input.equalsIgnoreCase("main menu")){
	        		output = "What can I do for you?Menu:\nregister course\nderegister course\nlog out";
	                state = STUDENT;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else{
	            	output = "Please select from the menu.\nregister course\nderegister course\nlog out";
	                state = STUDENT;
	                oo.setOutput(output);
		            oo.setState(state);
	            }
	        	
	        }else if(state==CREATESTUDENT){
	        	if(input.equalsIgnoreCase("log out")){
	            	output = "Successfully Log Out!";
	                state = WAITING;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else if(input.equalsIgnoreCase("main menu")){
	        		output = "What can I do for you?Menu:\ncreate student\ncreate course\ndelete student\ndestroy course\ncancel course\ncheck marks\nlog out";
	                state = CLERK;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else{
	        		o=outputHandler.createStudent(input);
	        		output=o.getOutput();
	        		state=o.getState();
	        		oo.setOutput(output);
		            oo.setState(state);
	        	}
	        }else if(state==CREATECOURSE){
	        	if(input.equalsIgnoreCase("log out")){
	            	output = "Successfully Log Out!";
	                state = WAITING;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else if(input.equalsIgnoreCase("main menu")){
	        		output = "What can I do for you?Menu:\ncreate student\ncreate course\ndelete student\ndestroy course\ncancel course\ncheck marks\nlog out";
	                state = CLERK;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else{
	        		o=outputHandler.createCourse(input);
	        		output=o.getOutput();
	        		state=o.getState();
	        		oo.setOutput(output);
		            oo.setState(state);
	        	}
	        }else if(state==CHECKMARKS){
	        	if(input.equalsIgnoreCase("log out")){
	            	output = "Successfully Log Out!";
	                state = WAITING;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else if(input.equalsIgnoreCase("main menu")){
	        		output = "What can I do for you?Menu:\ncreate student\ncreate course\ndelete student\ndestroy course\ncancel course\ncheck marks\nlog out";
	                state = CLERK;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else{
	        		o=outputHandler.displayMarks(input);
	        		output=o.getOutput();
	        		state=o.getState();
	        		oo.setOutput(output);
		            oo.setState(state);
	        	}
	        }else if(state==DELETESTUDENT){
	        	if(input.equalsIgnoreCase("log out")){
	            	output = "Successfully Log Out!";
	                state = WAITING;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else if(input.equalsIgnoreCase("main menu")){
	        		output = "What can I do for you?Menu:\ncreate student\ncreate course\ndelete student\ndestroy course\ncancel course\ncheck marks\nlog out";
	                state = CLERK;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else{
	        		o=outputHandler.deleteStudent(input);
	        		output=o.getOutput();
	        		state=o.getState();
	        		oo.setOutput(output);
		            oo.setState(state);
	        	}
	        }else if(state==DESTROYCOURSE){
	        	if(input.equalsIgnoreCase("log out")){
	            	output = "Successfully Log Out!";
	                state = WAITING;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else if(input.equalsIgnoreCase("main menu")){
	        		output = "What can I do for you?Menu:\ncreate student\ncreate course\ndelete student\ndestroy course\ncancel course\ncheck marks\nlog out";
	                state = CLERK;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else{
	        		o=outputHandler.destroyACourse(input);
	        		output=o.getOutput();
	        		state=o.getState();
	        		oo.setOutput(output);
		            oo.setState(state);
	        	}
	        }else if(state==CANCELCOURSE){
	        	if(input.equalsIgnoreCase("log out")){
	            	output = "Successfully Log Out!";
	                state = WAITING;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else if(input.equalsIgnoreCase("main menu")){
	        		output = "What can I do for you?Menu:\ncreate student\ncreate course\ndelete student\ndestroy course\ncancel course\ncheck marks\nlog out";
	                state = CLERK;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else{
	        		o=outputHandler.cancelACourse(input);
	        		output=o.getOutput();
	        		state=o.getState();
	        		oo.setOutput(output);
		            oo.setState(state);
	        	}
	        }else if(state==REGISTER){
	        	if(input.equalsIgnoreCase("log out")){
	            	output = "Successfully Log Out!";
	                state = WAITING;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else if(input.equalsIgnoreCase("main menu")){
	        		output = "What can I do for you?Menu:\nregister course\nderegister course";
	                state = STUDENT;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else{
	        		o=outputHandler.register(input);
	        		output=o.getOutput();
	        		state=o.getState();
	        		oo.setOutput(output);
		            oo.setState(state);
	        	}
	        }else if(state==DEREGISTER){
	        	if(input.equalsIgnoreCase("log out")){
	            	output = "Successfully Log Out!";
	                state = WAITING;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else if(input.equalsIgnoreCase("main menu")){
	        		output = "What can I do for you?Menu:\nregister course\nderegister course\nlog out";
	                state = STUDENT;
	                oo.setOutput(output);
		            oo.setState(state);
	        	}else{
	        		o=outputHandler.deRegister(input);
	        		output=o.getOutput();
	        		state=o.getState();
	        		oo.setOutput(output);
		            oo.setState(state);
	        	}
	        }
	        return oo;
	}


}
