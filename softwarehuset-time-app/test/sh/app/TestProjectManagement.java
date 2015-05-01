package sh.app;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

public class TestProjectManagement {
	
	
	// Basic functionality 
	// The test of dates in both project and task are collected.
	// since they both extend DateObject this is tested instead
	
	@Test
	public void testDateObjectConstructor() throws Exception {
		String startDate; 
		String endDate; 
		DateObject dateObject;
		
		//Correct dates. Start before end
		startDate = "2014-01"; 
		endDate = "2015-01"; 
		dateObject = new DateObject(startDate, endDate); 
		
		// Illegal week start
		startDate = "2014-57"; 
		try {
			dateObject = new DateObject(startDate, endDate); 
			fail("OperationNotAllowedException should have been thrown"); 
		} catch (OperationNotAllowedException e) {
			assertEquals(e.getMessage(), "Date must have the format YYYY-ww");
			assertEquals(e.getOperation(), "Set startdate");
		} 
		
		// Wrong date-format start
		startDate = "01-2014"; 
		try {
			dateObject = new DateObject(startDate, endDate); 
			fail("OperationNotAllowedException should have been thrown"); 
		} catch (OperationNotAllowedException e) {
			assertEquals(e.getMessage(), "Date must have the format YYYY-ww");
			assertEquals(e.getOperation(), "Set startdate");
		}
		
		// Wrong date-format start
		startDate = "12345678910"; 
		try {
			dateObject = new DateObject(startDate, endDate); 
			fail("OperationNotAllowedException should have been thrown"); 
		} catch (OperationNotAllowedException e) {
			assertEquals(e.getMessage(), "Date must have the format YYYY-ww");
			assertEquals(e.getOperation(), "Set startdate");
		}
		
		// Startdate null
		startDate = null; 
		try {
			dateObject = new DateObject(startDate, endDate); 
			fail("OperationNotAllowedException should have been thrown"); 
		} catch (OperationNotAllowedException e) {
			assertEquals(e.getMessage(), "Date must have the format YYYY-ww");
			assertEquals(e.getOperation(), "Set startdate");
		}
		
		// Illegal week end
		startDate = "2014-01"; 
		endDate = "2014-57"; 
		try {
			dateObject = new DateObject(startDate, endDate); 
			fail("OperationNotAllowedException should have been thrown"); 
		} catch (OperationNotAllowedException e) {
			assertEquals(e.getMessage(), "Date must have the format YYYY-ww");
			assertEquals(e.getOperation(), "Set enddate");
		} 
		
		// Wrong date-format end
		endDate = "01-2014"; 
		try {
			dateObject = new DateObject(startDate, endDate); 
			fail("OperationNotAllowedException should have been thrown"); 
		} catch (OperationNotAllowedException e) {
			assertEquals(e.getMessage(), "Date must have the format YYYY-ww");
			assertEquals(e.getOperation(), "Set enddate");
		}
		
		// Wrong date-format end 
		endDate = "12345678910"; 
		try {
			dateObject = new DateObject(startDate, endDate); 
			fail("OperationNotAllowedException should have been thrown"); 
		} catch (OperationNotAllowedException e) {
			assertEquals(e.getMessage(), "Date must have the format YYYY-ww");
			assertEquals(e.getOperation(), "Set enddate");
		}
		// Enddate null
		endDate = null; 
		try {
			dateObject = new DateObject(startDate, endDate); 
			fail("OperationNotAllowedException should have been thrown"); 
		} catch (OperationNotAllowedException e) {
			assertEquals(e.getMessage(), "Date must have the format YYYY-ww");
			assertEquals(e.getOperation(), "Set enddate");
		}
		
		// End date before start date
		startDate = "2015-07"; 
		endDate = "2015-01"; 
		
		try {
			dateObject = new DateObject(startDate, endDate); 
			fail("OperationNotAllowedException should have been thrown"); 
		} catch (OperationNotAllowedException e) {
			assertEquals(e.getMessage(), "End date must be after start date");
			assertEquals(e.getOperation(), "Set enddate");
		}
	}
	// TestProjectManagement
	
	// Test creation of project
	@Test
	public void testCreateProject() throws Exception{
		String startDate = "2015-02"; 
		String endDate = "2015-03"; 
		
		// Correct creation of project
		Project project = new Project("p1",startDate, endDate); 
		
		// Empty string as project name
		try {
			project = new Project("",startDate, endDate);  
			fail("OperationNotAllowedException should have been thrown"); 
		} catch (OperationNotAllowedException e) {
			assertEquals(e.getMessage(), "Project name cannot be empty"); 
			assertEquals(e.getOperation(), "Construct project"); 
		}
	}
	
	// Test add project to timeApp 
	@Test
	public void testAddProject() throws Exception {
		TimeApp timeApp = new TimeApp();
		assertEquals(timeApp.getProjects().size(), 0);
		
		// Add project with unique name
		Project project = new Project("p1", "2015-02", "2015-03");
		timeApp.addProject(project);

		assertEquals(timeApp.getProjects().size(), 1);
		
		// Add project with not unique name
		Project project1 = new Project("p1", "2015-02", "2015-03");
		
		try {
			timeApp.addProject(project1);
			fail("OperatioNotAllowedException should have been thrown");
		} catch (OperationNotAllowedException e) {
			assertEquals(e.getMessage(), "Project name must be unique");
			assertEquals(e.getOperation(), "Add project");
		}		
		
	}
	
	@Test
	public void selectProject() throws Exception {
		TimeApp timeApp = new TimeApp();
		Project project = new Project("p1", "2015-01", "2015-02");
		timeApp.addProject(project);
		
		// Return project
		Project testProject1 = timeApp.getProjectByName(project.getName());
		assertEquals(testProject1.getName(), project.getName());
		
		// Return null
		Project testProject2 = timeApp.getProjectByName( "wrong name" );
		assertEquals(testProject2, null);
	}
	
	@Test
	public void createTaskAndAddItToProject() throws Exception{
		Project project = new Project("p1","2015-01", "2015-02"); 
		String taskName = "taskname"; 
		
		// Test add task with unique name
		assertEquals(project.getTasks().size(), 0); 
		Task task = new Task(project,taskName, 5.0, "2015-01", "2015-02"); 
		assertEquals(project.getTasks().size(), 1); 
		
		// Test add task with not unique name
		try {
			Task task1 = new Task(project,taskName, 1.0, "2015-11", "2015-20");
			fail("OperationNotAllowedException should have been thrown"); 
		} catch (OperationNotAllowedException e) {
			assertEquals(e.getMessage(), "Task name must be unique"); 
			assertEquals(e.getOperation(), "Add task"); 
		}
		
	}
	
	// TestAssignDeveloperToTask
	
	@Test
	public void testAddUsersToTimeApp() throws Exception{
		TimeApp timeApp = new TimeApp(); 
		User user = new User("dev"); 
		assertEquals(timeApp.getUsers().size(), 0);   
		
		// Add user with unique name
		
		timeApp.addUser(user);
		assertEquals(timeApp.getUsers().size(), 1);
		
		// Add user with not unique name
		try {
			timeApp.addUser(user);
			fail("OperationNotAllowedException should have been thrown");
		} catch (OperationNotAllowedException e) {
			assertEquals(e.getMessage(), "User name must be unique");
			assertEquals(e.getOperation(), "Add user");
		}
	}
	
	@Test
	public void testGetUsers() throws Exception{
		TimeApp timeApp = new TimeApp(); 
		User user = new User("dev");
		
		timeApp.addUser(user);
		
		ArrayList<User> testArray = new ArrayList<User>();
		assertEquals(testArray.size(), 0);
		
		testArray = timeApp.getUsers();
		assertEquals(testArray.size(), 1);
	}
	
	@Test
	public void getUserByName() throws Exception{
		TimeApp timeApp = new TimeApp();
		User user = new User("dev");
		timeApp.addUser(user);
		
		// Return user
		User testUser1 = timeApp.getUserByName(user.getName());
		assertEquals(testUser1.getName(), user.getName());
		
		// Return null
		User testUser2 = timeApp.getUserByName( "wrong name" );
		assertEquals(testUser2, null);
	}


	
	@Test
	public void selectProjectAndTaskAndAddDeveloper() throws Exception {
		TimeApp timeApp = new TimeApp();
		Project project = new Project("p1", "2015-01", "2015-02");
		timeApp.addProject(project);
		
		// Return project
		Project testProject1 = timeApp.getProjectByName(project.getName());
		assertEquals(testProject1.getName(), project.getName());
		
		//Return task
		String taskName = "taskname"; 
		Task task = new Task(project,taskName, 5.0, "2015-01", "2015-02");
		assertEquals(project.getTaskByName(taskName), task);	
	
		// Add developer to task 
		User user = new User("dev"); 
		
		// Test add developer
		assertEquals(task.getDevelopers().size(), 0); 
		task.addDeveloper(user); 
		assertEquals(task.getDevelopers().size(), 1); 
		
		// Test that you cannot add the same developer twice
		// Implemented with HashSet
		task.addDeveloper(user);
		assertEquals(task.getDevelopers().size(), 1);
		
	}
	
		
}
