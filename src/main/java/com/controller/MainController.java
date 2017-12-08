package com.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.server.logic.handler.OutputHandler;
import com.server.logic.tables.CourseTable;
import com.server.logic.tables.StudentTable;
import com.server.logic.tables.UniversityTable;
import com.utilities.TimeSchedule;



/**
 * This class is the main controller of this Electronic commerce application.
 * All the request go through this controller.
 *
 */
@Controller
@RequestMapping("/")
public class MainController {

	// Object for logger class instantiated
	final static Logger logger = Logger.getLogger(MainController.class);

	// Map initialized to store cart information
	Map<Integer, Object> map = new HashMap<Integer, Object>();
	
	OutputHandler outputhandler = new OutputHandler();
	

	/**
	 * Method to fetch all the categories and the products from Product catalog web
	 * service and then send all the data to home page which is index.jsp
	 * 
	 * @param model
	 *            Used to forward the data received from web services
	 * @param request
	 *            The request object received
	 * @return View of the home page
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homePage(ModelMap model, HttpServletRequest request) {

		logger.info("Application started. Loading home page.");
		
		// Code to fetch all the students and courses from file system and start the timers
		

		// Code to fetch categories from Product Catalog Web Service
		//List<String> categoryList = productManagement.getCategoryList();

		// Adding all the categories in the model
		logger.info("Adding the categories in the model");
		//model.addAttribute("categoryList", categoryList);

		// Adding all the products in the model
		logger.info("Adding all the products in the model");
		//model.addAttribute("cdList", cdList);

		return "index";
	}
	
	@PostConstruct
	public void init() {
		System.out.println("startup called");
	     //startup logic here
		//Initialize the tables
				StudentTable.getInstance();
				CourseTable.getInstance();
				UniversityTable.getInstance();
				TimeSchedule timer= new TimeSchedule();
				timer.setClerkTimer();
				timer.setStudentTimer();
				timer.setTermEndTimer();		
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginUser(ModelMap model,@RequestParam("email") String email, @RequestParam("password") String password, HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		// Creating a session and adding map to it.
		HttpSession session = request.getSession();
		logger.info("Adding the login in the session");				

		if (email.equalsIgnoreCase("admin")) {
			if( outputhandler.clerkLogin(password).getOutput().equalsIgnoreCase("success")){
				result= "adminMenu";
				session.setAttribute("user", "admin");
			}else{
				model.addAttribute("error",  outputhandler.clerkLogin(password).getOutput());
				result= "index";
			}
		} else { 
			if( outputhandler.studentLogin(email+","+password).getOutput().equalsIgnoreCase("success")){
				result= "studentMenu";
				session.setAttribute("user", email);
				model.addAttribute("userEmail",email);
			}else{
				model.addAttribute("error", outputhandler.studentLogin(email+","+password).getOutput());
				result= "index";
			}
		}
		return result;
	 
	}
	
	@RequestMapping(value = "/studentCreation", method = RequestMethod.POST)
	public String studentCreation(ModelMap model,@RequestParam("email") String email, @RequestParam("password") String password,  @RequestParam("status") String status, HttpServletRequest request, HttpServletResponse response) {
		String result = "";
			if( outputhandler.createStudent(email+","+password+","+status).getOutput().equalsIgnoreCase("success")){
				result= "adminMenu";
				model.addAttribute("message",  "student creation successfull");
			}else{
				model.addAttribute("message",  outputhandler.createStudent(email+","+password+","+status).getOutput());
				result= "adminMenu";			
		}
		return result;
	 
	}
	
	@RequestMapping(value = "/studentRegistration", method = RequestMethod.POST)
	public String studentRegistration(ModelMap model,@RequestParam("course") String course, HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		String[] strArray = null;   
        strArray = course.split("\\(");
        System.out.println("value"+strArray[0]);
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("user");
			if( outputhandler.register(user+","+strArray[0]).getOutput().equalsIgnoreCase("success")){
				result= "studentMenu";
				model.addAttribute("message",  "student registration successfull");
			}else{
				model.addAttribute("message",  outputhandler.register(user+","+strArray[0]).getOutput());
				result= "studentMenu";			
		}
		model.addAttribute("userEmail",session.getAttribute("user"));
		return result;
	 
	}
	
	@RequestMapping(value = "/studentDeRegistration", method = RequestMethod.POST)
	public String studentDeRegistration(ModelMap model,@RequestParam("course") String course, HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		String[] strArray = null;   
        strArray = course.split("\\(");
        System.out.println("value"+strArray[0]);
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("user");
		String output =outputhandler.deRegister(user+","+strArray[0]).getOutput();
			if( output.equalsIgnoreCase("Course deregistred")){
				result= "studentMenu";
				model.addAttribute("message",  "student De-Registration successfull");
			}else{
				model.addAttribute("message",  output);
				result= "studentMenu";			
		}
		model.addAttribute("userEmail",session.getAttribute("user"));
		return result;
	 
	}
	
	@RequestMapping(value = "/courseCreation", method = RequestMethod.POST)
	public String courseCreation(ModelMap model,@RequestParam("midterms") String midterms, @RequestParam("assignments") String assignments,  @RequestParam("finalExam") String finalExam, @RequestParam("size") String size, @RequestParam("title") String title, @RequestParam("classCode") String classCode, HttpServletRequest request, HttpServletResponse response) {
		String result = "";
			if( outputhandler.createCourse("false,"+midterms+","+assignments+","+finalExam+","+size+","+title+","+classCode).getOutput().equalsIgnoreCase("success")){
				result= "adminMenu";
				model.addAttribute("message",  "course creation successfull");
			}else{
				model.addAttribute("message",  outputhandler.createCourse("false,"+midterms+","+assignments+","+finalExam+","+size+","+title+","+classCode).getOutput());
				result= "adminMenu";			
		}
		return result;
	 
	}
	
	@RequestMapping(value = "/deletestudent", method = RequestMethod.POST)
	public String deleteStudent(ModelMap model,@RequestParam("email") String email, HttpServletRequest request, HttpServletResponse response) {
		String result = "";
			if( outputhandler.deleteStudent(email).getOutput().equalsIgnoreCase("success")){
				result= "adminMenu";
				model.addAttribute("message",  "Student deleted successfully");
			}else{
				model.addAttribute("message",  outputhandler.deleteStudent(email).getOutput());
				result= "adminMenu";			
		}
		return result;
	 
	}
	
	
	@RequestMapping(value = "/deletecourse", method = RequestMethod.POST)
	public String deletecourse(ModelMap model,@RequestParam("Coursecode") String Coursecode, HttpServletRequest request, HttpServletResponse response) {
		String result = "";
			if( outputhandler.destroyACourse(Coursecode).getOutput().equalsIgnoreCase("success")){
				result= "adminMenu";
				model.addAttribute("message",  "Course Deleted successfully");
			}else{
				model.addAttribute("message",  outputhandler.destroyACourse(Coursecode).getOutput());
				result= "adminMenu";			
		}
		return result;
	 
	}
	
	@RequestMapping(value = "/cancelcourse", method = RequestMethod.POST)
	public String cancelcourse(ModelMap model,@RequestParam("Coursecode") String Coursecode, HttpServletRequest request, HttpServletResponse response) {
		String result = "";
			if( outputhandler.cancelACourse(Coursecode).getOutput().equalsIgnoreCase("success")){
				result= "adminMenu";
				model.addAttribute("message",  "Course Deleted successfully");
			}else{
				model.addAttribute("message",  outputhandler.cancelACourse(Coursecode).getOutput());
				result= "adminMenu";			
		}
		return result;
	 
	}
	
	@RequestMapping(value = "/adminMenu", method = RequestMethod.POST)
	public String adminMenu(ModelMap model,@RequestParam("adminAction") String action, HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		if(action.equalsIgnoreCase("CREATESTUDENT")){
			result = "studentCreation";
		}else if(action.equalsIgnoreCase("CREATECOURSE")){
			result = "courseCreation";
		}
		else if(action.equalsIgnoreCase("DELETESTUDENT")){
			result = "deletestudent";
		}else if(action.equalsIgnoreCase("DESTROYCOURSE")){
			model.addAttribute("Courses", outputhandler.displayCourses());
			result = "deletecourse";
		}
		else if(action.equalsIgnoreCase("CANCELCOURSE")){
			model.addAttribute("Courses", outputhandler.displayCourses());
			result = "cancelcourse";
		}else if(action.equalsIgnoreCase("CHECKMARKS")){
			model.addAttribute("Marks", outputhandler.displayDeanList());
			result = "displaymarks";
		}
		return result;
	 
	}
	
	@RequestMapping(value = "/studentMenu", method = RequestMethod.POST)
	public String studentMenu(ModelMap model,@RequestParam("studentAction") String action, HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		HttpSession session = request.getSession();
		if(action.equalsIgnoreCase("REGISTERCOURSE")){
			result = "studentRegistration";	  
			model.addAttribute("Courses", outputhandler.displaySubjects());
		}else if(action.equalsIgnoreCase("DEREGISTERCOURSE")){
			int coursesRegistered = StudentTable.getInstance().checkStudentCourses(""+session.getAttribute("user"));
			if(coursesRegistered !=-1 && coursesRegistered !=0){
			result = "studentDeRegistration";
			model.addAttribute("Courses", StudentTable.getInstance().registeredCourses(""+session.getAttribute("user")));
			}else{
				result = "studentMenu";
				model.addAttribute("message", "no course registered yet");
			}
		}else if(action.equalsIgnoreCase("CHECKMARKS")){
			model.addAttribute("Marks", outputhandler.displayStudentMarks(""+session.getAttribute("user")));			
			result = "displayStudentMarks";
		}
		
		model.addAttribute("userEmail",session.getAttribute("user"));
		return result;
	 
	}
	

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
	    session.invalidate(); 
		return "index";
	 
	}
	
}