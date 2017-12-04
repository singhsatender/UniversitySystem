package com.utilities;
import java.util.Timer;
import java.util.TimerTask;

import com.server.logic.model.University;
import com.server.logic.tables.UniversityTable;

public class TimeSchedule {

	  //Description:Scehdule time for clerk operations.(1day=20seconds)
	  public void setClerkTimer(){
	  Timer timer = new Timer();
	  TimerTask task = new TimerTask() {
	      @Override
	   public void run() {
	    System.out.println("Clerk can not create course and student now." + System.currentTimeMillis());
	    University univ = new University();
	    univ.dropSubject=true;
	    univ.clerkActivities = false;
	       }
	  };

	  timer.schedule(task, 180000);//20days
	}
	  
	 //Description:Scehdule time for student operations.
	  public void setStudentTimer(){
		  Timer timer = new Timer();
		  TimerTask task = new TimerTask() {
		      @Override
		   public void run() {
		    System.out.println("Student course derregister deadlione over" + System.currentTimeMillis());
		    University univ = new University();
		    univ.dropSubject=false;
		       }
		  };

		  timer.schedule(task, 300000);//40days
		}
	  
	//Description:Scehdule time to mark term end. Also submit marks once it is fired.
	  public void setTermEndTimer(){
		  Timer timer = new Timer();
		  TimerTask task = new TimerTask() {
		      @Override
		   public void run() {
		    System.out.println("Term Ended" + System.currentTimeMillis());
		    University univ = new University();
		    univ.termEnded=true;
		    UniversityTable univTable = new UniversityTable();
		    univTable.submitMarks();
		    System.out.println("Marks are now available");
		       }
		  };

		  timer.schedule(task, 420000);//84 days
		}

}
