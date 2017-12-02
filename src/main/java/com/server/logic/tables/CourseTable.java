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

public class CourseTable {
	private Logger logger = Trace.getInstance().getLogger("opreation_file");
	List<Course> courseList=new ArrayList<Course>();
    private static class CourseListHolder {
        private static final CourseTable INSTANCE = new CourseTable();
    }
    private CourseTable(){
    	//set up the default list with some instances. read from file.
    	try {
    		FileReader reader = new FileReader("C:\\Users\\singh\\workspace-neon\\UniversityRegistration\\data\\CourseData.txt");
    		BufferedReader bufferedReader = new BufferedReader(reader);
    		String line;
    		while ((line = bufferedReader.readLine()) != null) {
    			//System.out.println(line);
    			String[] strArray = null;   
    	        strArray = line.split(",");    
	    	        Course existingcourses=new Course(Boolean.parseBoolean(strArray[0]),Integer.parseInt(strArray[1]),Integer.parseInt(strArray[2]),Boolean.parseBoolean(strArray[3]),
	    	        		                          Integer.parseInt(strArray[4]),strArray[5],Integer.parseInt(strArray[6]));    	        
    	        courseList.add(existingcourses);
    		}
    		reader.close();
    		} catch (IOException e) {
    			e.printStackTrace();
    		} 	
    	logger.info(String.format("Operation:Initialize CourseTable;CourseTable: %s", courseList));
    };
    public static final CourseTable getInstance() {
        return CourseListHolder.INSTANCE;
    }
	
    //Description: Create course on the basis of the input passed.
    public Object createCourse(boolean enforcePrereqs, int numberOfMidterms, int numberOfAssignments, boolean hasAFinal, int capsize,String title, int myCode) {		
		boolean result=true;
		int flag=0;
		for(int i=0;i<courseList.size();i++){
			int courseCode=(courseList.get(i)).getMyCode();
			if(courseCode==myCode){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag==0){
			Course newtitle=new Course(enforcePrereqs,numberOfMidterms,numberOfAssignments,hasAFinal,capsize,title,myCode);
			result=courseList.add(newtitle);
			logger.info(String.format("Operation:Create New Course;Course Info:[%s,%s,%s,%s,%s,%s,%s];State:Course Creation Successful", enforcePrereqs,numberOfMidterms,numberOfAssignments,hasAFinal,capsize,title,myCode));
		}else{
			result=false;
			logger.info(String.format("Operation:Create New Course;Course Info:[%s,%s,%s,%s,%s,%s,%s];State:Fail,Reason:Course Already Exists", enforcePrereqs,numberOfMidterms,numberOfAssignments,hasAFinal,capsize,title,myCode));
		}
		return result;	
	}
	
    //Description: check course exists or not
    public boolean lookup(int myCode) {
		boolean result=true;
		int flag=0;
		for(int i=0;i<courseList.size();i++){
			int cousreCode=(courseList.get(i)).getMyCode();
			if(cousreCode==myCode){
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
	
    //Description: check course contains available seats for students
	public boolean checkLimit(int myCode) {
		boolean result=true;
		int flag=0;
		for(int i=0;i<courseList.size();i++){
			int cousreCode=(courseList.get(i)).getMyCode();
			if(cousreCode==myCode){				
				if((courseList.get(i)).getAvailableseats()>=(courseList.get(i)).getCapsize())
				{
					flag=flag+1;
				}
			}else{
				flag=flag+0;	
			}
		}
		if(flag==0){
			result=false;
		}
		return result;
	}
	
	//Description: unregister all students and delete course
	public Object destroyCourse(int myCode) {
		String result="";
		int index=0;
		Course courseSel=null;
		int flag=0;
		for(int i=0;i<courseList.size();i++){
			if(courseList.get(i).getMyCode()==myCode){
				courseSel= courseList.get(i);
				flag=flag+1;
				index=i;
			}else{
				flag=flag+0;
			}
		}
		if(flag!=0){
				String string2=courseList.get(index).getTitle();
				
				List<Student> students=courseList.get(index).Students;
				for(Student st:students)					
				{					
					st.Courses.remove(courseSel);
				}
				courseList.remove(index);
				result="success";
				logger.info(String.format("Operation:Destroy Course;Course Info:[%s,%s];State:Course Destroyed successfully", myCode,string2));
		}else{
			result="Course Doesnot exist";
			logger.info(String.format("Operation:Destroy Course;Course Info:[%s];Failure,Reason:Course Doesnot exist", myCode));
		}
		return result;
		
	}
	
	//Description: unregister all students
	public Object cancelCourse(int myCode) {
		String result="";
		int index=0;
		Course courseSel=null;
		int flag=0;
		for(int i=0;i<courseList.size();i++){
			if(courseList.get(i).getMyCode()==myCode){
				courseSel= courseList.get(i);
				flag=flag+1;
				index=i;
			}else{
				flag=flag+0;
			}
		}
		if(flag!=0){
				String string2=courseList.get(index).getTitle();
				
				List<Student> students=courseList.get(index).Students;
				for(Student st:students)					
				{
					
					st.Courses.remove(courseSel);
				}
				
				result="success";
				logger.info(String.format("Operation:Cancel Course;Course Info:[%s,%s];State:Course canceled successfully", myCode,string2));
		}else{
			result="Course Doesnot exist";
			logger.info(String.format("Operation:Cancel Course;Course Info:[%s,%s];State:failed,Reason:Course Doesnot exist", myCode));
		}
		return result;
	}

	//Description: returns course object on the basis of course code
	public Course searchCourse(int cousre){
		for(int i=0;i<courseList.size();i++){
			int cousreCode=(courseList.get(i)).getMyCode();
			if(cousreCode==cousre){				
				return (courseList.get(i));
			}
		}
		return null;
		
	}
	
	//Description: add student to course's student list
	public boolean addStudentToCourse(Student student,int course){
		for(int i=0;i<courseList.size();i++){
			int cousreCode=(courseList.get(i)).getMyCode();
			if(cousreCode== course){				
			 courseList.get(i).Students.add(student);
			 return true;
			}		
	   }
		return false;
	}
	
	//Description: delete student from course's student list
	public boolean removeStudent(Student student,int course){
		for(int i=0;i<courseList.size();i++){
			int cousreCode=(courseList.get(i)).getMyCode();
			if(cousreCode== course){				
			 courseList.get(i).Students.remove(student);
			 return true;
			}		
	   }
		return false;
	}
	
	//Description: returns all courses available
	public String courseList(){
		String courses="";
		for(int i=0;i<courseList.size();i++){
			courses=courses+"\n"+(courseList.get(i)).getMyCode()+"\t"+(courseList.get(i)).getTitle();			
		}
		return courses;
	}
	
	//Description: returns all list of courses available
		public ArrayList<String> subjectList(){
			List <String> subject = new ArrayList<String>();
			String courses="";
			for(int i=0;i<courseList.size();i++){
				subject.add((courseList.get(i)).getMyCode()+"( "+(courseList.get(i)).getTitle()+" )");			
			}
			return (ArrayList<String>) subject;
		}
		
	
	//Description: check student in course's student list
		public boolean checkStudentInCourse(Student student,int course){
			for(int i=0;i<courseList.size();i++){
				int cousreCode=(courseList.get(i)).getMyCode();
				if(cousreCode== course){				
				 return true;
				}		
		   }
			return false;
		}
	
		//Description: returns all courses available
		public int countCourseList(){			
				
			return courseList.size();
		}
}
