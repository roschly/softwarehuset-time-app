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

public class TestRegisterTime {
	
	@Before
	public void setUp() throws OperationNotAllowedException{
		TimeApp timeApp = new TimeApp(); 	
	}
	

	@Test
	public void testSelectTask() throws Exception{
		Project project = new Project("p1","2015-01", "2015-02");
		User PM = new User("PM"); 
		project.setProjectmanager(PM);
		String taskName = "taskname"; 
		Task task = new Task(project,taskName, PM, 5.0, "2015-01", "2015-02");
		assertEquals(project.getTaskByName(taskName), task);
	}
	
	@Test
	public void testCreateActivityOnAssignedTask() throws Exception{
		TimeApp timeApp = new TimeApp();
		Project project = new Project("p1", "2015-01", "2015-40");
		User PM = new User("PM");
		timeApp.addUser(PM);
		project.setProjectmanager(PM); 
		Task task = new Task(project, "taskname", PM, 5.5,"2015-02", "2015-39"); 
		User user = new User("dev");
		timeApp.addUser(user);
		task.addDeveloper(user, PM, project, timeApp.getAvailableDevelopers(task));
		
		// Test add activity when user is assigned to task
		assertEquals(task.getActivities().size(), 0);
		Activity activity = new Activity("2015-02-01", 5.0, user, task);
		assertEquals(task.getActivities().size(), 1);
	}
	
	@Test
	public void testCreateActivityOnAssignedTaskIllegalDate() throws Exception{
		TimeApp timeApp = new TimeApp();
		Project project = new Project("p1", "2015-01", "2015-06");
		User PM = new User("PM");
		timeApp.addUser(PM);
		project.setProjectmanager(PM); 
		Task task = new Task(project, "taskname", PM, 5.5,"2015-02", "2015-04"); 
		User user = new User("dev");
		timeApp.addUser(user);
		task.addDeveloper(user, PM, project, timeApp.getAvailableDevelopers(task));
		
		assertEquals(task.getActivities().size(), 0);
		
		// Activity date before the task start date
		try {
			Activity activity1 = new Activity("2015-01-01", 5.0, user, task);
			fail("OperationNotAllowedException should have been thrown"); 
		} catch(OperationNotAllowedException e){
			assertEquals(e.getMessage(), "Activity date must be contained in task duration");
			assertEquals(e.getOperation(), "Create activity");
		}
		
		assertEquals(task.getActivities().size(), 0);
		
		// Activity date after the task start date
		try {
			Activity activity2 = new Activity("2015-10-01", 5.0, user, task);
			fail("OperationNotAllowedException should have been thrown"); 
		} catch(OperationNotAllowedException e){
			assertEquals(e.getMessage(), "Activity date must be contained in task duration");
			assertEquals(e.getOperation(), "Create activity");
		}
	
	}	
	
	
	@Test
	public void testCreateActivityOnNotAssignedTask() throws Exception{
		TimeApp timeApp = new TimeApp();
		Project project = new Project("p1", "2015-01", "2015-02");
		User PM = new User("PM");
		project.setProjectmanager(PM); 
		Task task = new Task(project, "taskname", PM, 5.5,"2015-01", "2015-02"); 
		User user = new User("dev");
		
		// NOT assigned to task 
		assertEquals(task.getActivities().size(), 0);
		try {
			Activity activity = new Activity("2015-01-01", 5.0, user, task);
			fail("OperationNotAllowedException should have been thrown"); 
		} catch (OperationNotAllowedException e) {
			assertEquals(e.getMessage(), "Must be assigned to task to create activity");
			assertEquals(e.getOperation(), "Create activity");
		}
		assertEquals(task.getActivities().size(), 0);
	}
	
	@Test
	public void testCreateActivityDateFormat() throws Exception{
		TimeApp timeApp = new TimeApp();
		Project project = new Project("p1", "2015-01", "2015-40");
		User PM = new User("PM");
		timeApp.addUser(PM);
		project.setProjectmanager(PM); 
		Task task = new Task(project, "taskname", PM, 5.5,"2015-02", "2015-39"); 
		User user = new User("dev");
		timeApp.addUser(user);
		task.addDeveloper(user, PM, project, timeApp.getAvailableDevelopers(task));
		
		// Test wrong date format
		String wrongDate = "1"; 
		try {
			Activity activityWrongDate = new Activity(wrongDate, 5.0, user, task);
			fail("OperationNotAllowedException should have been thrown"); 
		} catch (OperationNotAllowedException e) {
			assertEquals(e.getMessage(), "Date must have the format yyyy-MM-dd"); 
			assertEquals(e.getOperation(),"Set date"); 
		}
		
		assertEquals(task.getActivities().size(), 0);
		
		// Test wrong date format
		wrongDate = "01-01-2014"; 
		try {
			Activity activityWrongDate = new Activity(wrongDate, 5.0, user, task);
			fail("OperationNotAllowedException should have been thrown"); 
		} catch (OperationNotAllowedException e) {
			assertEquals(e.getMessage(), "Date must have the format yyyy-MM-dd"); 
			assertEquals(e.getOperation(),"Set date"); 
		}
		
		assertEquals(task.getActivities().size(), 0);
		
		// Test null date
		wrongDate = null; 
		try {
			Activity activityWrongDate = new Activity(wrongDate, 5.0, user, task);
			fail("OperationNotAllowedException should have been thrown"); 
		} catch (OperationNotAllowedException e) {
			assertEquals(e.getMessage(), "Date must have the format yyyy-MM-dd"); 
			assertEquals(e.getOperation(),"Set date"); 
		}
		
		assertEquals(task.getActivities().size(), 0);
	}

}
