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

public class TestBasicFunctionality {
	
	@Test
	public void testGetEstimatedTime() throws Exception{
		Project project = new Project("p1","2015-01", "2015-04");
		User PM = new User("PM");
		project.setProjectmanager(PM); 
		Task task = new Task(project, "taskname", PM,  5.5,"2015-02", "2015-03");
		
		assertTrue(task.getEstimatedTime() == 5.5);
	}
	
	@Test
	public void testGetDeveloper() throws Exception {
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
		
		// Correct user
		assertEquals(activity.getDeveloper(), user); 
		
		// Wrong user
		User wrongUser = new User("dev1");
		assertNotEquals(activity.getDeveloper(), wrongUser); 
	}
	
	@Test
	public void testGetActivityDate() throws Exception {
		TimeApp timeApp = new TimeApp(); 
		Project project = new Project("p1","2015-01", "2015-40");
		User PM = new User("PM");
		timeApp.addUser(PM);
		project.setProjectmanager(PM); 
		Task task = new Task(project, "taskname", PM,  5.5,"2015-02", "2015-39"); 
		User user = new User("dev");
		timeApp.addUser(user);
		task.addDeveloper(user, PM, project, timeApp.getAvailableDevelopers(task));
		String date = "2015-02-01"; 
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setLenient(false);
		
		Activity activity = new Activity(date, 5.0, user, task);
		
		// Correct date
		assertEquals(activity.getDate(), format.parse(date)); 
	
	}
	
	// TestBasicFunctionality 
	// The test of dates in both project and task are collected.
	// since they both extend DateObject this is tested instead
	
	@Test
	public void testDateObjectConstructor() throws Exception {
		String startDate; 
		String endDate; 
		DateObject dateObject;
		
		// Correct dates. Start before end
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
}
