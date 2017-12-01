package com.server.logic.tables;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import com.server.logic.model.Course;
import com.server.logic.model.Student;
import com.server.logic.model.University;
import com.utilities.Trace;

public class UniversityTable {
	private Logger logger = Trace.getInstance().getLogger("opreation_file");
	List<Student> studentList=new ArrayList<Student>();
	
    private static class UniversityListHolder {
        private static final UniversityTable INSTANCE = new UniversityTable();
    }
    public static final UniversityTable getInstance() {
        return UniversityListHolder.INSTANCE;
    }
    
   //Description: Student registration for course specified as input
   public Object registerStudentForCourse(int student, String aCourse, Date date){
	   String result="";
	    boolean studentId=StudentTable.getInstance().lookup(student);
		boolean coursecode=CourseTable.getInstance().lookup(Integer.parseInt(aCourse));
		boolean limit=CourseTable.getInstance().checkLimit(Integer.parseInt(aCourse));
		University university= new University();
		if(studentId==false){
			result="User Invalid";
			logger.info(String.format("Operation:registerStudentForCourse;Info:[%d,%s,%s,%s];State:Fail;Reason:Invalid User.", student,aCourse,dateformat(date)));
		}else if(coursecode==false){
			result="coursecode Invalid";
			logger.info(String.format("Operation:registerStudentForCourse;Info:[%d,%s,%s,%s];State:Fail;Reason:Invalid Coursecode.", student,aCourse,dateformat(date)));
		}else{	
			if(!university.clerkActivities){
				if(limit){
					Student selStudent= StudentTable.getInstance().searchStudent(student);
					Course selCourse= CourseTable.getInstance().searchCourse(Integer.parseInt(aCourse));
					
					if(selStudent.getStudentStatus().equals("PARTTIME") && selStudent.Courses.size()>=2)
					{
						result="You can not register more than 2 courses";
						logger.info(String.format("Operation:registerStudentForCourse;Info:[%d,%s,%s];State:Fail;Reason:Can not register more than 2 courses.", student,aCourse,dateformat(date)));
					
						
					}else if(selStudent.getStudentStatus().equals("FULLTIME") && selStudent.Courses.size()>=4){
						result="You can not register more than 4 courses";
						logger.info(String.format("Operation:registerStudentForCourse;Info:[%d,%s,%s];State:Fail;Reason:Can not register more than 4 courses.", student,aCourse,dateformat(date)));
					}
					else{
						String response= StudentTable.getInstance().addCourseToStudent(student, selCourse);
						if(response.equals("success")){
							CourseTable.getInstance().addStudentToCourse(selStudent, Integer.parseInt(aCourse));
							
							result="success";
							logger.info(String.format("Operation:registerStudentForCourse;Info:[%d,%s,%s];State:Student registered Successfully",student,aCourse,dateformat(date)));
						}
						else{
							result= response;
							logger.info(String.format("Operation:registerStudentForCourse;Info:[%d,%s,%s];State:Fail;Reason:Already registered for this course", student,aCourse,dateformat(date)));
							
						}
				  }
					
				
				}else if(limit==false){
					result="The Maximun Number of Students is Reached";
					logger.info(String.format("Operation:registerStudentForCourse;Info:[%d,%s,%s];State:Fail;Reason:The Maximun Number of students is Reached.", student,aCourse,dateformat(date)));
				}	
			}else{
				result="Student registration yet not started";
				logger.info(String.format("Operation:registerStudentForCourse;Info:[%d,%s,%s];State:Fail;Reason:Student registration yet not started.", student,aCourse,dateformat(date)));
			}
		}
		return result;
	   
   }

 //Description: Student de-registration for course specified as input, Dropped if deadline is crossed
	public Object deRegisterCourse(int student,String course,Date date){
		String result="";
		boolean coursecode=CourseTable.getInstance().lookup(Integer.parseInt(course));
		Course selCourse= CourseTable.getInstance().searchCourse(Integer.parseInt(course));
		Student selStudent= StudentTable.getInstance().searchStudent(student);
		if(coursecode==false){
			result="coursecode Invalid";
			logger.info(String.format("Operation:DeRegisterStudentForCourse;Info:[%d,%s,%s];State:Fail;Reason:Invalid Course Code.", student,course,dateformat(date)));
		}else{
			University univ = new University();
			String output =StudentTable.getInstance().deRegisterCourse(student,selCourse);
			if(output.equals("success")){
				CourseTable.getInstance().removeStudent(selStudent, Integer.parseInt(course));
				if(univ.dropSubject){
					logger.info(String.format("Operation:DeRegisterStudentForCourse;Info:[%d,%s,%s];State:Course deregistred", student,course,dateformat(date)));
					result= "Course deregistred";
				}else{
					logger.info(String.format("Operation:DeRegisterStudentForCourse;Info:[%d,%s,%s];State:Subject dropped as maximum time allocated has been reached(2 weeks)", student,course,dateformat(date)));
					result= "Subject dropped as maximum time allocated has been reached(2 weeks)";
				}
				
			}else{
				logger.info(String.format("Operation:DeRegisterStudentForCourse;Info:[%d,%s,%s];State:Fail;Reason:Course not registered.", student,course,dateformat(date)));
				result= output;				
			}		
		}
		
		return result;
	}
   
   //Description: Submit marks to university once the term has ended
	public void submitMarks(){
		Random ran = new Random();
		for(Course course: CourseTable.getInstance().courseList){
			for(Student student: course.Students){
				student.setMarks(ran.nextInt(16) + 70);
			}
		}
			
	}
	
	//Description: Display Marks of students with more than 85%
	public Object displayMarks(){
		String result="";
		for(Course course: CourseTable.getInstance().courseList){
			for(Student student: course.Students){
				if(student.getMarks()>=85){					
					result= result + student.getStudentid()+"\t"+student.getMarks()+"\t"+course.getMyCode()+"\n"; 
				}
			}
		}
		return result;
			
	}
	
	//Description: DateFormat 
	private String dateformat(Date date){
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String datestr=format1.format(date);
		return datestr;
	}

}
