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

public class TestGetAvailableDevelopers {

	@Test
	public void testAddUsersToTimeApp() throws Exception{
		TimeApp timeApp = new TimeApp();  
		User user = new User("dev"); 
		int presize = timeApp.getUsers().size();    
		
		// Add user with unique name
		timeApp.addUser(user);
		assertEquals(timeApp.getUsers().size(), presize + 1);
		
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
		int presize = timeApp.getUsers().size(); 
		
		timeApp.addUser(user1);
		
		ArrayList<User> testArray = new ArrayList<User>();
		
		testArray = timeApp.getUsers();
		assertEquals(testArray.size(), presize + 1);
		
		User user2 = new User("dev2");
		timeApp.addUser(user2);
		testArray = timeApp.getUsers();
		assertEquals(testArray.size(), presize + 2);
	}
	
	@Test
	public void testGetAvailableDevelopers() throws Exception{
		TimeApp timeApp = new TimeApp(); 
		Project project = new Project("p1", "2015-01", "2015-40");
		timeApp.addProject(project);
		User PM = new User("PM");
		project.setProjectmanager(PM);
		Task task = new Task(project, "task", PM, 5.0, "2015-03", "2015-06");
		int presize = timeApp.getAvailableDevelopers(task).size();
		
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
		assertEquals(testArray.size(), presize + 5);
	
		//Loop til at assigne user til tasks
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
		assertEquals(testArray.size(), presize + 4);
	} 
	
}
