package com.server.logic.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Course {
	boolean enforcePrereqs;
	int numberOfMidterms;// 0 to 2 
	int numberOfAssignments;// 0 to 5 
	boolean hasAFinal;   //make sure there is at least 1 grade element  
	int capsize; 
	String title;
	int myCode;
	int availableseats;
	public List<Student> Students;
	int weightOfMidterm=0;
	int weightOfFinal=0;
	int weightOfAssignment=0;
	
		
	public int getAvailableseats() {
		return availableseats;
	}


	public void setAvailableseats(int availableseats) {
		this.availableseats = availableseats;
	}
	
	


	public Course(boolean enforcePrereqs, int numberOfMidterms, int numberOfAssignments, boolean hasAFinal, int capsize,
			String title, int myCode) {
		super();
		this.enforcePrereqs = enforcePrereqs;
		this.numberOfMidterms = numberOfMidterms;
		this.numberOfAssignments = numberOfAssignments;
		this.hasAFinal = hasAFinal;
		this.capsize = capsize;
		this.title = title;
		this.myCode = myCode;
		this.availableseats=capsize;
		this.Students = new ArrayList<Student>();
		this.setWeights();		
	}
	
	//Description:Set random weights for assignments, mid terms and final
	public void setWeights(){
		Random ran = new Random();
		int bal =100;
		if(this.numberOfAssignments>0){
		this.weightOfAssignment=ran.nextInt(6) + 25;
		bal = bal - this.weightOfAssignment;
		}
		if((this.numberOfMidterms>0)){
		this.weightOfMidterm=ran.nextInt(6) + 25;
		bal = bal - this.weightOfMidterm;
		}
		if(this.hasAFinal){
		this.weightOfFinal=bal;
		}
		else{
			if(this.numberOfAssignments>0){
				this.weightOfAssignment=this.weightOfAssignment+bal;
			}
			else{
				this.weightOfMidterm=this.weightOfMidterm+bal;
			}
			
		}
	}
	
	public boolean isEnforcePrereqs() {
		return enforcePrereqs;
	}
	
	public void setEnforcePrereqs(boolean enforcePrereqs) {
		this.enforcePrereqs = enforcePrereqs;
	}
	
	public int getNumberOfMidterms() {
		return numberOfMidterms;
	}
	
	public void setNumberOfMidterms(int numberOfMidterms) {
		this.numberOfMidterms = numberOfMidterms;
	}
	
	public int getNumberOfAssignments() {
		return numberOfAssignments;
	}
	
	public void setNumberOfAssignments(int numberOfAssignments) {
		this.numberOfAssignments = numberOfAssignments;
	}
	
	public boolean isHasAFinal() {
		return hasAFinal;
	}
	
	public void setHasAFinal(boolean hasAFinal) {
		this.hasAFinal = hasAFinal;
	}
	
	public int getCapsize() {
		return capsize;
	}
	
	public void setCapsize(int capsize) {
		this.capsize = capsize;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getMyCode() {
		return myCode;
	}
	
	public void setMyCode(int myCode) {
		this.myCode = myCode;
	}


}
