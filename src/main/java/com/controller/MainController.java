package com.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.server.logic.handler.OutputHandler;
import com.server.logic.handler.model.Output;
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
		//Initialize the tables
		StudentTable.getInstance();
		CourseTable.getInstance();
		UniversityTable.getInstance();
		TimeSchedule timer= new TimeSchedule();
		timer.setClerkTimer();
		timer.setStudentTimer();
		timer.setTermEndTimer();

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
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginUser(ModelMap model, HttpServletRequest request) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String result = "";
		System.out.println("in Login User");

		if (email.equalsIgnoreCase("admin")) {
			if( outputhandler.clerkLogin(password).getOutput().equalsIgnoreCase("success")){
				result= "admin";
			}else{
				model.addAttribute("error",  outputhandler.clerkLogin(password).getOutput());
				result= "login";
			}
		} else { 
			if( outputhandler.studentLogin(email+password).getOutput().equalsIgnoreCase("success")){
				result= "clerk";
			}else{
				model.addAttribute("error", outputhandler.studentLogin(email+password).getOutput());
				result= "login";
			}
		}
		return result;
	 
	}
	
}