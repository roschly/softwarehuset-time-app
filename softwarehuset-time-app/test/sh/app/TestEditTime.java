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

public class TestEditTime {
	
	@Before
	public void setUp() throws OperationNotAllowedException{
		TimeApp timeApp = new TimeApp(); 	
	}
	
	
	@Test 
	public void testSelectActivity() throws Exception{
		TimeApp timeApp = new TimeApp();
		Project project = new Project("p1","2015-01", "2015-40");
		User PM = new User("PM");
		timeApp.addUser(PM);
		project.setProjectmanager(PM); 
		Task task = new Task(project, "taskname", PM,  5.5, "2015-02", "2015-39"); 
		User user = new User("dev");
		timeApp.addUser(user);
		task.addDeveloper(user, PM, project, timeApp.getAvailableDevelopers(task));
		Activity activity = new Activity("2015-03-01", 5.0, user, task);
		
		//Test activity is in task
		assertEquals(task.getActivityById(activity.getId()), activity); 
		
		//Test activity that does not exist
		assertEquals(task.getActivityById(1000), null); 
	}
	
	@Test
	public void testEditOwnActivity() throws Exception{
		TimeApp timeApp = new TimeApp();
		Project project = new Project("p1","2015-01", "2015-40");
		User PM = new User("PM");
		timeApp.addUser(PM);
		project.setProjectmanager(PM); 
		Task task = new Task(project, "taskname", PM, 5.5,"2015-02", "2015-39"); 
		User user = new User("dev");
		timeApp.addUser(user);
		task.addDeveloper(user, PM, project, timeApp.getAvailableDevelopers(task));
		Activity activity = new Activity("2015-02-01", 5.0, user, task);
		
		Double dur = 1.5;
		
		// Test set a duration divisable by 0.5 AND not 0
		activity.changeDuration(dur, user, project);
		assertEquals(activity.getDuration(), dur);
		
		// Test set a duration NOT divisable by 0.5
		dur = 1.7;
		try {
			activity.changeDuration(dur, user, project);
			fail("OperationNotAllowedException should have been thrown"); 
		} catch(OperationNotAllowedException e){
			assertEquals(e.getMessage(), "Activity duration must be divisable by 0.5 AND not 0");
			assertEquals(e.getOperation(), "Set duration");
		}
		
		// Test set a duration to 0
		dur = 0.0;
		try {
			activity.changeDuration(dur, user, project);
			fail("OperationNotAllowedException should have been thrown"); 
		} catch(OperationNotAllowedException e){
			assertEquals(e.getMessage(), "Activity duration must be divisable by 0.5 AND not 0");
			assertEquals(e.getOperation(), "Set duration");
		}
	}
		
	@Test
	public void testEditActivityNotOwn() throws Exception{
		TimeApp timeApp = new TimeApp();
		Project project = new Project("p1","2015-01", "2015-40");
		User PM = new User("PM");
		timeApp.addUser(PM);
		project.setProjectmanager(PM); 
		Task task = new Task(project, "taskname", PM, 5.5,"2015-02", "2015-39"); 
		User user = new User("dev");
		timeApp.addUser(user);
		task.addDeveloper(user, PM, project, timeApp.getAvailableDevelopers(task));
		Activity activity = new Activity("2015-02-01", 5.0, user, task);
		User wrongUser = new User("wrongUser");
		
		Double dur = 1.5;
		
		// Try to edit other developers activity 
		try {
			activity.changeDuration(dur, wrongUser, project);
			fail("OperationNotAllowedException should have been thrown"); 
		} catch (OperationNotAllowedException e){
			assertEquals(e.getMessage(), "Can only edit own activity");
			assertEquals(e.getOperation(), "Edit activity");
		}
		assertNotEquals(activity.getDuration(), dur);
	}

}



