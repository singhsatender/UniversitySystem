package com.server.logic.handler;

import java.util.Date;

import com.server.logic.handler.model.Output;
import com.server.logic.model.University;
import com.server.logic.tables.CourseTable;
import com.server.logic.tables.StudentTable;
import com.server.logic.tables.UniversityTable;
import com.utilities.Config;

public class OutputHandler {
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
    public static final int RETURN=12;
    public static final int PAYFINE=13;
    public static final int CLERKLOGIN=14;
    public static final int STUDENTLOGIN=15;
    
    //Description: check conditions and call student creation method
	public Output createStudent(String input) {
		Output output=new Output("",0);
		String[] strArray = null;   
		University univ=new University();
	    if(univ.clerkActivities){
        strArray = input.split(",");
        boolean email=strArray[0].contains("@");
        Object result="";
        if(strArray.length!=3 || email!=true){
        	output.setOutput("Your input should in this format:'StudentEmail,password,Status [FULLTIME or PARTTIME]'");
        	output.setState(CREATESTUDENT);
        }else{
        	result=StudentTable.getInstance().createStudent(strArray[0], strArray[1],strArray[2]);
        	if(result.equals(true)){
        		output.setOutput("Success");
        	}else{
        		output.setOutput("The User Already Exists!");
        	}
        	
        	output.setState(CLERK);
        }
	   }else{
		   output.setOutput("Maximum time allocated for student creation is over(20 days)");
    	   output.setState(CLERK);
	   }
		return output;
	}
    
	//Description: check conditions and call course creation method
	public Output createCourse(String input) {
		Output output=new Output("",0);
		String[] strArray = null;   
        strArray = input.split(",");
        Object result="";
        int courseCode=0;
        if(strArray.length==7){
         courseCode= Integer.parseInt(strArray[6])/10000;
        }
        
        University univ=new University();
        if(univ.clerkActivities){
        if(strArray.length!=7){
        	output.setOutput("Your input should in this format:'enforcePrereqs: true or false,numberOfMidterms, numberOfAssignments,hasAFinal:true or false,MaxStudents,title,CourseCode\n"
	            			+ "Note: Course code should conatin 6 digits, first 2 digits contains department code [CS-10,EC-20,EBT-30,EE-40] and last 4 course code.'");
        	output.setState(CREATECOURSE);
        }else if(strArray[6].length()!=6){
        	output.setOutput("Your input should in this format:'enforcePrereqs: true or false,numberOfMidterms, numberOfAssignments,hasAFinal:true or false,MaxStudents,title,CourseCode\n"
        			+ "Note: Course code should conatin 6 digits, first 2 digits contains department code [CS-10,EC-20,EBT-30,EE-40] and last 4 course code.'"
        			+ "\n Error: Course code length is wrong");
        	output.setState(CREATECOURSE);
        }else if(courseCode!=10 && courseCode!=20 && courseCode!=30 && courseCode!=40){
        	output.setOutput("Your input should in this format:'enforcePrereqs: true or false,numberOfMidterms, numberOfAssignments,hasAFinal:true or false,MaxStudents,title,CourseCode\n"
        			+ "Note: Course code should conatin 6 digits, first 2 digits contains department code [CS-10,EC-20,EBT-30,EE-40] and last 4 course code.'"
        			+ "\n Error: department code is wrong");
        	output.setState(CREATECOURSE);
        }else if(Integer.parseInt(strArray[1])==0 && Integer.parseInt(strArray[2])==0 &&Boolean.parseBoolean(strArray[3])==false){
        	output.setOutput("Your input should in this format:'enforcePrereqs: true or false,numberOfMidterms, numberOfAssignments,hasAFinal:true or false,MaxStudents,title,CourseCode\n"
        			+ "Note: Course code should conatin 6 digits, first 2 digits contains department code [CS-10,EC-20,EBT-30,EE-40] and last 4 course code.'"
        			+ "\n Error:No grading elemnt present.There should be atlease one grade element(numberOfMidterms or numberOfAssignments or hasAFinal)");
        	output.setState(CREATECOURSE);
        }else{
        	result=CourseTable.getInstance().createCourse(Boolean.parseBoolean(strArray[0]),Integer.parseInt(strArray[1]),Integer.parseInt(strArray[2]),Boolean.parseBoolean(strArray[3]),
                    Integer.parseInt(strArray[4]),strArray[5],Integer.parseInt(strArray[6]));
        	if(result.equals(true)){
        		output.setOutput("Success");
        	}else{
        		output.setOutput("The Course Already Exists!");
        	}
        	output.setState(CLERK);
        }
       }else{
    	   output.setOutput("Maximum time allocated for course creation is over(20 days)");
    	   output.setState(CLERK);
       }
        
		return output;
	}
    
	//Description: check conditions and call student deletion method
	public Output deleteStudent(String input) {
		Output output=new Output("",0);
		String[] strArray = null;   
        strArray = input.split(",");
        int studentId=StudentTable.getInstance().lookup(strArray[0]);
        boolean email=strArray[0].contains("@");
        Object result="";
        if(strArray.length!=1 || email!=true){
        	output.setOutput("Your input should in this format:'studentemail'");
        	output.setState(DELETESTUDENT);
        }else if(studentId==-1){
        	output.setOutput("The Student Does Not Exist!");
        	output.setState(DELETESTUDENT);
        }else{
        	result=StudentTable.getInstance().deleteStudent(studentId);
        	if(result.equals("success")){
        		output.setOutput("Success");
        	}else{
        		output.setOutput(result+"!");
        	}
        	output.setState(CLERK);
        }
		return output;
	}
    
	//Description: check conditions and call course destruction method
	public Output destroyACourse(String input) {
		Output output=new Output("",0);
		String[] strArray = null;   
        strArray = input.split(",");
        Object result="";
        if(strArray.length!=1){
        	output.setOutput("Your input should in this format:'Course Code',Course Code should be a 6 digit number");
        	output.setState(DESTROYCOURSE);
        }else{
        	result=CourseTable.getInstance().destroyCourse(Integer.parseInt(strArray[0]));
        	if(result.equals("success")){
        		output.setOutput("Success");
        	}else{
        		output.setOutput(result+"!");
        	}
        	output.setState(CLERK);
        }
		return output;
	}
	
	//Description: returns all available course
	public String displayCourses() {
		
		String result;
		return result=CourseTable.getInstance().courseList();
	}
	
	//Description: returns all students with 85% or more marks
	public Output displayMarks(String input) {
		Output output=new Output("",0);
		if(input.equalsIgnoreCase("yes")){
		University univ = new University();
		if(univ.termEnded){
			output.setOutput("Result\n Student ID\tMarks\tCourse code\n"+UniversityTable.getInstance().displayMarks());
			output.setState(CLERK);
		}else{
				output.setOutput("Term not ended yet. Can not display marks before that.");
				output.setState(CLERK);
			}
		}else{
			output.setOutput("Input wrong. Please try again.");
			output.setState(CLERK);
		}
     return output;
	}
	
	//Description: check conditions and call cancel course method
	public Output cancelACourse(String input) {
		Output output=new Output("",0);
		String[] strArray = null;   
        strArray = input.split(",");
        boolean number=isInteger(strArray[0]);
        Object result="";
        if(strArray.length!=1){
        	output.setOutput("Your input should in this format:'Course Code',Course Code should be a 6 digit number");
        	output.setState(CANCELCOURSE);
        }else{
        		result=CourseTable.getInstance().cancelCourse(Integer.parseInt(strArray[0]));
            	if(result.equals("success")){
            		output.setOutput("Success!");
            	}else{
            		output.setOutput(result+"!");
            	}
            	output.setState(CLERK);

        }
		return output;
	}
    
	//Description: check conditions and call student registration method
	public Output register(String input) {
		Output output=new Output("",0);
		String[] strArray = null;   
        strArray = input.split(",");
        boolean email=strArray[0].contains("@");
        int studentId=StudentTable.getInstance().lookup(strArray[0]);
        Object result="";        
        if(strArray.length!=2 || email!=true){
        	output.setOutput("Your input should in this format:'studentemail,Course code'");
        	output.setState(REGISTER);
        }else if(studentId==-1){
        	output.setOutput("The User Does Not Exist!");
        	output.setState(REGISTER);
        }else{
        	   result=UniversityTable.getInstance().registerStudentForCourse(studentId, strArray[1], new Date());
        		if(result.equals("success")){
            		output.setOutput("Success!");
            		output.setState(STUDENT);
            	}else{
            		output.setOutput(result+" !");
            		output.setState(REGISTER);
            	}        	
        	
        }
		return output;
	}
    
	//Description: check conditions and call student de-registration method
	public Output deRegister(String input) {
		Output output=new Output("",0);
		String[] strArray = null;   
        strArray = input.split(",");
        boolean email=strArray[0].contains("@");
        int studentId=StudentTable.getInstance().lookup(strArray[0]);
        Object result="";
        if(strArray.length!=2 || email!=true){
        	output.setOutput("Your input should in this format:'studentemail,course code'");
        	output.setState(DEREGISTER);
        }else if(studentId==-1){
        	output.setOutput("The Student Does Not Exist!");
        	output.setState(DEREGISTER);
        }else{
        	boolean courseCode=isNumber(strArray[1]);
        	if(courseCode!=true){
        		output.setOutput("Your input should in this format:'studentemail,course code'");
            	output.setState(DEREGISTER);
        	}else{
        		result=UniversityTable.getInstance().deRegisterCourse(studentId, strArray[1], new Date());
        		
            		output.setOutput(result+"!");
        	}
        	output.setState(STUDENT);
        }
		return output;
	}
	
	//Description: validate clerk login credentials and show appropriate menu
	public Output clerkLogin(String input) {
		Output output=new Output("",0);
		if(input.equalsIgnoreCase(Config.CLERK_PASSWORD)){
			output.setOutput("success");
        	output.setState(CLERK);
		}else{
			output.setOutput("Wrong Password!Please Input The Password:");
        	output.setState(CLERKLOGIN);
		}
		return output;
	}
    
	//Description: validate student login credentials and show appropriate menu
	public Output studentLogin(String input) {
		Output output=new Output("",0);
		String[] strArray = null;   
        strArray = input.split(",");
        boolean email=strArray[0].contains("@");
        int result=0;
        if(strArray.length!=2 || email!=true){
        	output.setOutput("Your input should in this format:'studentemail,password'");
        	output.setState(STUDENTLOGIN);
        }else{
        	result=StudentTable.getInstance().checkStudent(strArray[0], strArray[1]);
        	if(result==0){
        		output.setOutput("success");
            	output.setState(STUDENT);
        	}else if(result==1){
        		output.setOutput("Wrong Password!Please Input Username and Password:'studentemail,password'");
            	output.setState(STUDENTLOGIN);
        	}else{
        		output.setOutput("The Student Does Not Exist!Please The Studentemail and Password:'studentemail,password'");
            	output.setState(STUDENTLOGIN);
        	}
        }
		return output;
	}
	
	//Description: check integer
	public static boolean isInteger(String value) {
		char[] ch = value.toCharArray();
		boolean isNumber=true;
		if(value.length()==13){
			for (int i = 0; i < ch.length; i++) {
				isNumber = Character.isDigit(ch[i]);
			}
		}else{
			isNumber=false;
		}
		return isNumber;
		 }
	
	//Description:check integer
	public boolean isNumber(String value) {
		char[] ch = value.toCharArray();
		boolean isNumber=true;
			for (int i = 0; i < ch.length; i++) {
				isNumber = Character.isDigit(ch[i]);
			}
		return isNumber;
	}
}
