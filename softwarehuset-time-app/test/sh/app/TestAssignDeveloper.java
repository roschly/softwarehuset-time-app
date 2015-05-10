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
	
	@Before
	public void setUp() throws OperationNotAllowedException{
		TimeApp timeApp = new TimeApp(); 	
	}
	
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
	public void testGetDevelopers() throws Exception{
		TimeApp timeApp = new TimeApp(); 
		User user1 = new User("dev1");
		
		timeApp.addUser(user1);
		
		ArrayList<User> testArray = new ArrayList<User>();
		assertEquals(testArray.size(), 0);
		
		testArray = timeApp.getUsers();
		assertEquals(testArray.size(), 1);
		
		User user2 = new User("dev2");
		timeApp.addUser(user2);
		testArray = timeApp.getUsers();
		assertEquals(testArray.size(), 2);
	}
	
	@Test
	public void testSelectDeveloper() throws Exception{
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
	public void testGetAvailableDevelopers() throws Exception{
		TimeApp timeApp = new TimeApp(); 
		Project project = new Project("p1", "2015-01", "2015-40");
		timeApp.addProject(project);
		User PM = new User("PM");
		project.setProjectmanager(PM);
		Task task = new Task(project, "task", PM, 5.0, "2015-03", "2015-06");
		
		User user1 = new User("dev1");
		User user2 = new User("dev2");
		User user3 = new User("dev3");
		User user4 = new User("dev4");
		User user5 = new User("dev5");
		
		timeApp.addUser(user1);
		timeApp.addUser(user2);
		timeApp.addUser(user3);
		timeApp.addUser(user4);
		timeApp.addUser(user5);
		
		
		ArrayList<User> testArray = new ArrayList<User>();
		assertEquals(testArray.size(), 0);
		
		testArray = timeApp.getAvailableDevelopers(task);
		assertEquals(testArray.size(), 5);
	
		//Loop til at assigne usere til tasks
		for ( int i = 1; i <= 10; i++ ){
			Task taskName = new Task(project, "t" + i, PM, 5.0, "2015-01", "2015-10");
			taskName.addDeveloper(user1, PM, project, timeApp.getAvailableDevelopers(taskName));
		}
		
		SimpleDateFormat format = new SimpleDateFormat("YYYY-ww");
		format.setLenient(false);
		
		assertFalse(timeApp.isAvailable(user1, format.parse("2015-02")));
		assertTrue(timeApp.isAvailable(user2, format.parse("2015-02")));
		
		// Test at kun de ledige returneres
		testArray = timeApp.getAvailableDevelopers(task);
		assertEquals(testArray.size(), 4);
	} 
	
	@Test
	public void testPMAssignAvailableDeveloper() throws Exception {
		TimeApp timeApp = new TimeApp();
		Project project = new Project("p1", "2015-01", "2015-02");
		timeApp.addProject(project);
		User PM = new User("PM");
		timeApp.addUser(PM);
		
		project.setProjectmanager(PM);
		
		// Return project
		Project testProject1 = timeApp.getProjectByName(project.getName());
		assertEquals(testProject1.getName(), project.getName());
		
		//Return task
		String taskName = "taskname"; 
		Task task = new Task(project,taskName, PM, 5.0, "2015-01", "2015-02");
		assertEquals(project.getTaskByName(taskName), task);	
	
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
		
		//Loop til at assigne usere til tasks
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
		
		// increase max activities
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
		
		// Return project
		Project testProject1 = timeApp.getProjectByName(project.getName());
		assertEquals(testProject1.getName(), project.getName());
		
		//Return task
		String taskName = "taskname"; 
		Task task = new Task(project,taskName, PM, 5.0, "2015-01", "2015-02");
		assertEquals(project.getTaskByName(taskName), task);	
	
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
