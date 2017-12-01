package com.server.logic.model;

import java.util.ArrayList;
import java.util.List;

public class Student {
	int studentId;
	String studentName;
	String password;
	String studentStatus;
	int marks;

	public List<Course> Courses;

	
	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	public String getStudentStatus() {
		return studentStatus;
	}

	public void setStudentStatus(String studentStatus) {
		this.studentStatus = studentStatus;
	}

	
	public Student(int studentId,String username,String password, String studentStatus){
		this.studentId=studentId;
		this.password=password;
		this.studentName=username;
		this.studentStatus = studentStatus;
		this.Courses= new ArrayList<Course>();
	}
	
	public String toString(){
		return "["+this.studentId+","+this.studentName+","+this.password+","+this.studentStatus+"]";
	}

	public int getStudentid() {
		return studentId;
	}

	public void setStudentid(int studentId) {
		this.studentId = studentId;
	}


	public String getStudentname() {
		return studentName;
	}

	public void setStudentname(String studentName) {
		this.studentName = studentName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
