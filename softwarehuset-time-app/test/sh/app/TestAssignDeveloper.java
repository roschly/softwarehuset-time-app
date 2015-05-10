package sh.app;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

public class TestAssignDeveloper {
	
	@Test
	public void testPMAssignAvailableDeveloper() throws Exception {
		TimeApp timeApp = new TimeApp();
		Project project = new Project("p1", "2015-01", "2015-02");
		timeApp.addProject(project);
		User PM = new User("PM");
		timeApp.addUser(PM);
		
		project.setProjectmanager(PM);
		Project testProject1 = timeApp.getProjectByName(project.getName());
		String taskName = "taskname"; 
		Task task = new Task(project,taskName, PM, 5.0, "2015-01", "2015-02");	
	
		User user = new User("dev"); 
		timeApp.addUser(user);
		
		// PM add developer
		assertEquals(task.getDevelopers().size(), 0); 
		task.addDeveloper(user, PM, project, timeApp.getAvailableDevelopers(task)); 
		assertEquals(task.getDevelopers().size(), 1); 
		
		// Test that you cannot add the same developer twice
		// Implemented with HashSet
		task.addDeveloper(user, PM, project, timeApp.getAvailableDevelopers(task));
		assertEquals(task.getDevelopers().size(), 1);
		
	}
	
	@Test
	public void testIncreaseMaxActivities() throws Exception {
		TimeApp timeApp = new TimeApp();
		Project project = new Project("p1", "2015-01", "2015-40");
		timeApp.addProject(project);
		User PM = new User("PM");
		timeApp.addUser(PM);
		
		project.setProjectmanager(PM);
	
		User user = new User("dev"); 
		timeApp.addUser(user);
		
		// Loop til at assigne usere til tasks
		for ( int i = 1; i <= 10; i++ ){
			Task taskName = new Task(project, "t" + i, PM, 5.0, "2015-01", "2015-10");
			taskName.addDeveloper(user, PM, project, timeApp.getAvailableDevelopers(taskName));
		}
		
		assertEquals(user.getMaxActivities(), 10); 
		
		// The user has reached the max number of activities in the timeslot of the task
		Task task = new Task(project, "newtask" , PM, 5.0, "2015-01", "2015-10");
		try {
			task.addDeveloper(user, PM, project, timeApp.getAvailableDevelopers(task));
			fail("OperationNotAllowedException should have been thrown"); 
		} catch(OperationNotAllowedException e) {
			assertEquals(e.getMessage(), "The chosen developer is not available");
			assertEquals(e.getOperation(), "Assign developer");
		}
		
		// Increase max activities
		user.setMaxActivities(20);
		assertEquals(user.getMaxActivities(), 20); 
		task.addDeveloper(user, PM, project, timeApp.getAvailableDevelopers(task));
		
	}
	
	@Test
	public void testNotPMAssignDeveloper() throws Exception {
		TimeApp timeApp = new TimeApp();
		Project project = new Project("p1", "2015-01", "2015-02");
		timeApp.addProject(project);
		User PM = new User("PM");
		project.setProjectmanager(PM);
		User dev1 = new User("dev1");
		
		Project testProject1 = timeApp.getProjectByName(project.getName());
		
		String taskName = "taskname"; 
		Task task = new Task(project,taskName, PM, 5.0, "2015-01", "2015-02");	
	
		User dev2 = new User("dev2"); 
		
		// Test NOT pm add developer
		assertEquals(task.getDevelopers().size(), 0);  
		
		try {
			task.addDeveloper(dev2, dev1, project, timeApp.getAvailableDevelopers(task)); 
			fail("OperationNotAllowedException should have been thrown");
		} catch(OperationNotAllowedException e){
			assertEquals(e.getMessage(), "Must be projectmanager to assign developer to task");
			assertEquals(e.getOperation(), "Assign developer");
		}
		
		assertEquals(task.getDevelopers().size(), 0); 
	}

}
