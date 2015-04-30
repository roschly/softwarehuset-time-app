package sh.app;

import java.util.ArrayList;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;


public class TestBasicFunctionality {
	
	// TimeApp
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
	public void testAddUsers() throws Exception{
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
	public void testGetProjects() throws Exception{
		TimeApp timeApp = new TimeApp(); 
		Project project;
		
		assertEquals(timeApp.getProjects().size(), 0);
		
		project = new Project("p1", "2015-02", "2015-03");
		timeApp.addProject(project);
		
		ArrayList<Project> testArray = new ArrayList<Project>();
		assertEquals(testArray.size(), 0);
		
		testArray = timeApp.getProjects();
		assertEquals(testArray.size(), 1);
		
	}
	
	@Test
	public void testAddProject() throws Exception{
		TimeApp timeApp = new TimeApp();
		
		// Add project with unique name
		assertEquals(timeApp.getProjects().size(), 0);

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
	public void getProjectByName() throws Exception{
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
	
	// Project
	@Test
	public void testProjectConstructor() throws Exception{
		
		// Empty string as project name
		try {
			Project project = new Project("","2015-01", "2015-02"); 
			fail("OperationNotAllowedException should have been thrown"); 
		} catch (OperationNotAllowedException e) {
			assertEquals(e.getMessage(), "Project name cannot be empty"); 
			assertEquals(e.getOperation(), "Construct project"); 
		}
	}
	
	@Test 
	public void testGetAndSetProjectManager() throws Exception{
		Project project = new Project("p1","2015-01", "2015-02");  
		User user = new User("dev");
		
		project.setProjectmanager(user);
		
		assertEquals(project.getProjectmanager(), user); 
	}
	
	@Test
	public void testGetAndAddTask() throws Exception{
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
	
	//TODO check why if-statement in getTaskByName is not covered
	@Test
	public void getTaskByName() throws Exception{
		Project project = new Project("p1","2015-01", "2015-02"); 
		String taskName = "taskname"; 
		Task task = new Task(project,taskName, 5.0, "2015-01", "2015-02");
		assertEquals(project.getTaskByName(taskName), task);
	}
	
	// Task
	@Test
	public void testGetEstimatedTime() throws Exception{
		Project project = new Project("p1","2015-01", "2015-04");
		Task task = new Task(project, "taskname", 5.5,"2015-02", "2015-03");
		
		assertTrue(task.getEstimatedTime() == 5.5);
	}
	
	@Test
	public void testGetAndAddDeveloper() throws Exception{
		Project project = new Project("p1","2015-01", "2015-04");
		Task task = new Task(project, "taskname", 5.5,"2015-02", "2015-03"); 
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
	
	@Test
	public void testGetAndAddActivity() throws Exception{
		Project project = new Project("p1","2015-01", "2015-04");
		Task task = new Task(project, "taskname", 5.5,"2015-02", "2015-03"); 
		User user = new User("dev");
		
		
		// Test add activity
		assertEquals(task.getActivities().size(), 0);
		Activity activity = new Activity("2015-01-01", 5.0, user, task);
		assertEquals(task.getActivities().size(), 1);
	}
	
	// Activity
	@Test
	public void testSetAndGetDuration() throws Exception{
		Project project = new Project("p1","2015-01", "2015-04");
		Task task = new Task(project, "taskname", 5.5,"2015-02", "2015-03"); 
		User user = new User("dev");
		Activity activity = new Activity("2015-01-01", 5.0, user, task);
		
		Double dur = 1.5;
		
		// Test set a duration divisable by 0.5 AND not 0
		activity.setDuration(dur);
		assertEquals(activity.getDuration(), dur);
		
		// Test set a duration NOT divisable by 0.5
		dur = 1.7;
		try {
			activity.setDuration(dur);
		} catch(OperationNotAllowedException e){
			assertEquals(e.getMessage(), "Activity duration must be divisable by 0.5 AND not 0");
			assertEquals(e.getOperation(), "Set duration");
		}
		
		// Test set a duration to 0
		dur = 0.0;
		try {
			activity.setDuration(dur);
		} catch(OperationNotAllowedException e){
			assertEquals(e.getMessage(), "Activity duration must be divisable by 0.5 AND not 0");
			assertEquals(e.getOperation(), "Set duration");
		}
		
	}
}
