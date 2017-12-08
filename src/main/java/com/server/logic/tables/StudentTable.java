package com.server.logic.tables;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.server.logic.model.Course;
import com.server.logic.model.Student;
import com.utilities.Trace;

public class StudentTable {
	private Logger logger = Trace.getInstance().getLogger("opreation_file");
	List<Student> studentList=new ArrayList<Student>();
    private static class StudentListHolder {
        private static final StudentTable INSTANCE = new StudentTable();
    }
    private StudentTable(){
    	//set up the default list with some instances. read from file.
    	try {
    		FileReader reader = new FileReader("C:\\Users\\singh\\workspace-neon\\UniversityRegistration\\data\\StudentData.txt");
    		BufferedReader bufferedReader = new BufferedReader(reader);
    		String line;
    		while ((line = bufferedReader.readLine()) != null) {
    			//System.out.println(line);
    			String[] strArray = null;   
    	        strArray = line.split(",");    
    	        Student existinguser=new Student(Integer.parseInt(strArray[0]),strArray[1],strArray[2],strArray[3]);
    			studentList.add(existinguser);
    		}
    		reader.close();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}	

    	logger.info(String.format("Operation:Initialize StudentTable;StudentTable: %s", studentList));
    };
    public static final StudentTable getInstance() {
        return StudentListHolder.INSTANCE;
    }
	
    //Description: Create a student on the basis of inputs
    public Object createStudent(String studentEmail, String password, String status) {		
		boolean result=true;
		int flag=0;
		for(int i=0;i<studentList.size();i++){
			String email=(studentList.get(i)).getStudentname();
			if(email.equalsIgnoreCase(studentEmail)){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag==0){
			Student newStudent=new Student(studentList.size()+1,studentEmail,password,status);
			result=studentList.add(newStudent);
			//write file here.
			logger.info(String.format("Operation:Create New Student;Student Info:[%s,%s];State:Student created Successfully", studentEmail,password));
		}else{
			result=false;
			logger.info(String.format("Operation:Create New Student;Student Info:[%s,%s];State:Fail;Reason:The Student already existed.", studentEmail,password));
		}
		return result;	
	}
	
    //Description: check student exists or not
    public boolean lookup(int j) {
		boolean result=true;
		int flag=0;
		for(int i=0;i<studentList.size();i++){
			int userid=(studentList.get(i)).getStudentid();
			if(userid==j){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag==0){
			result=false;
		}
		return result;
	}
	
    //Description: returns student object on the basis of student id
    public Student searchStudent(int j) {
		for(int i=0;i<studentList.size();i++){
			int userid=(studentList.get(i)).getStudentid();
			if(userid==j){			
				return (studentList.get(i));
			}
		}
		return null;
	}
	
    //Description: delete index of the student from the student list.
	public Object deleteStudent(int i) {
		//Since the userid in "Student Creation" is automatically assigned to the user,upon its creation.
		//Each user has a unique userid.Even it is deleted,its userid can not be assigned to other user.
		//To maintain the correctness of the data,here instead delete index from the List.
		//TOO:I choose to remove the user's information instead the whole index.Keep its userid as reference.
		String result="";
		Student studentSel=null;
		int flag=0;
		int index=0;
		for(int j=0;j<studentList.size();j++){
			if(studentList.get(j).getStudentid()==i){
				studentSel=studentList.get(j);
				index=j;
				flag=flag+1;
			}else{
				flag=flag+0;
			}
		}
		
		if(flag==0){
			result="The Student Does Not Exist";
			logger.info(String.format("Operation:Delete Student;Student Info:[%s,%s];State:Fail;Reason:The Student Does Not Exist.", "N/A","N/A"));
		}else{
			String string=studentList.get(index).getStudentname();
			String string2=studentList.get(index).getPassword();
			
			List<Course> courses=studentList.get(index).Courses;
			for(Course cs:courses)					
			{
				cs.Students.remove(studentSel);
			}
			
				studentList.get(index).setStudentid(i);
				studentList.get(index).setPassword("N/A");
				studentList.get(index).setStudentname("N/A");
				result="success";
				logger.info(String.format("Operation:Delete Student;Student Info:[%s,%s];State:Student deleted Successfully", string,string2));
		}
    
		return result;

	}
	
	//Description: returns student id on the basis of student email
	public int lookup(String string) {
		int userid=-2;
		for(int i=0;i<studentList.size();i++){
			if(studentList.get(i).getStudentname().equalsIgnoreCase(string)){
				userid=i;
			}
		}
		return userid+1;
	}
	
	//Description: check student login credentials
    public int checkStudent(String string, String string2) {
		int result=0;
		int flag=0;
		int index=0;
		for(int i=0;i<studentList.size();i++){
			if(studentList.get(i).getStudentname().equalsIgnoreCase(string)){
				flag++;
				index=i;
			}
		}
		boolean password=studentList.get(index).getPassword().equalsIgnoreCase(string2);
		if(flag!=0 && password){
			result=0;
		}else if(flag==0){
			result=2;
		}else if(password==false){
			result=1;
		}
		return result;
	}
	
    //Description: add course to studet's course list
	public String addCourseToStudent(int student,Course course){
		if(studentList.get(student-1).Courses.contains(course)){
		   return "Courses already registered";
		}
		else{
			studentList.get(student-1).Courses.add(course);
			return "success";
		}
	}
	
	//Description: remove course from students's course list
	public String deRegisterCourse(int student,Course course){
		if(studentList.get(student-1).Courses.contains(course)){
			studentList.get(student-1).Courses.remove(course);
			return "success";			   
			}
			else{
				return "Course not registered yet";
			}
	}
	
	//Description: check course is added to studet's course list
		public boolean checkCourseInStudent(int student,Course course){
			if(studentList.get(student-1).Courses.contains(course)){
			   return true;
			}
			else{				
				return false;
			}
		}
		
		//Description: check student Marks 
	    public List<String> checkStudentMarks(String string) {
			List<String> result= new ArrayList<String>();
			for(int i=0;i<studentList.size();i++){
				if(studentList.get(i).getStudentname().equalsIgnoreCase(string)){
					for(Course course : studentList.get(i).Courses){
						result.add("Course : "+course.getMyCode()+", Marks ="+studentList.get(i).getMarks());						
					}
					break;
				}
			}
			
			return result;
		}
	    
	  //Description: check student Courses 
	    public int checkStudentCourses(String string) {
			int result= -1;
			for(int i=0;i<studentList.size();i++){
				if(studentList.get(i).getStudentname().equalsIgnoreCase(string)){
					result = studentList.get(i).Courses.size();
					break;
				}else{
					result = -1;
				}
			}
			return result;
		}
	    
	    //Description: check student Courses 
	    public List<String> registeredCourses(String string) {
			List<String> result= new ArrayList<String>();
			for(int i=0;i<studentList.size();i++){
				if(studentList.get(i).getStudentname().equalsIgnoreCase(string)){
					for(Course course : studentList.get(i).Courses){
						result.add(course.getMyCode()+"("+course.getTitle()+")");						
					}
					break;
				}
			}
			return result;
		}
	    
	  //Description: count students
	    public int countStudents() {
			return studentList.size();
		}
	    
	    
}
