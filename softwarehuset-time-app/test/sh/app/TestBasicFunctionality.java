package sh.app;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
		SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD");
		
		// Test wrong date format
		String wrongDate = "1"; 
		try {
			Activity activityWrongDate = new Activity(wrongDate, 5.0, user, task);
			fail("OperationNotAllowedException should have been thrown"); 
		} catch (OperationNotAllowedException e) {
			assertEquals(e.getMessage(), "Date must have the format yyyy-MM-dd"); 
			assertEquals(e.getOperation(),"Set date"); 
		}
		
		assertEquals(task.getActivities().size(), 1);
		
		// Test wrong date format
		wrongDate = "01-01-2014"; 
		try {
			Activity activityWrongDate = new Activity(wrongDate, 5.0, user, task);
			fail("OperationNotAllowedException should have been thrown"); 
		} catch (OperationNotAllowedException e) {
			assertEquals(e.getMessage(), "Date must have the format yyyy-MM-dd"); 
			assertEquals(e.getOperation(),"Set date"); 
		}
		
		assertEquals(task.getActivities().size(), 1);
		
		// Test null date
		wrongDate = null; 
		try {
			Activity activityWrongDate = new Activity(wrongDate, 5.0, user, task);
			fail("OperationNotAllowedException should have been thrown"); 
		} catch (OperationNotAllowedException e) {
			assertEquals(e.getMessage(), "Date must have the format yyyy-MM-dd"); 
			assertEquals(e.getOperation(),"Set date"); 
		}
		
		assertEquals(task.getActivities().size(), 1);
		
	}
	
	//TODO check why if-statement in getActivityByID is not covered
	@Test 
	public void testGetActivityByID() throws Exception{
		Project project = new Project("p1","2015-01", "2015-04");
		Task task = new Task(project, "taskname", 5.5,"2015-02", "2015-03"); 
		User user = new User("dev");
		Activity activity = new Activity("2015-01-01", 5.0, user, task);
		
		//Test activity is in task
		assertEquals(task.getActivityById(activity.getId()), activity); 
		
		//Test activity is not in another task
		Task wrongTask = new Task(project, "taskname1", 5.5,"2015-02", "2015-03"); 
		assertEquals(wrongTask.getActivityById(activity.getId()), null); 
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
	
	@Test
	public void testGetDeveloper() throws Exception {
		Project project = new Project("p1","2015-01", "2015-04");
		Task task = new Task(project, "taskname", 5.5,"2015-02", "2015-03"); 
		User user = new User("dev");
		Activity activity = new Activity("2015-01-01", 5.0, user, task);
		
		// Correct user
		assertEquals(activity.getDeveloper(), user); 
		
		// Wrong user
		User wrongUser = new User("dev1");
		assertNotEquals(activity.getDeveloper(), wrongUser); 
	}
	
	@Test
	public void testGetDate() throws Exception {
		Project project = new Project("p1","2015-01", "2015-04");
		Task task = new Task(project, "taskname", 5.5,"2015-02", "2015-03"); 
		User user = new User("dev");
		String date = "2015-01-01"; 
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setLenient(false);
		
		Activity activity = new Activity(date, 5.0, user, task);
		
		// Correct date
		assertEquals(activity.getDate(), format.parse(date)); 
		
		// Different date
		String differentDate = "2015-03-01"; 
		assertNotEquals(activity.getDate(), format.parse(differentDate)); 
	}
	
	// DateObject
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
	
	@Test
	public void testGetEndDate() throws Exception{
		String startDate = "2014-01"; 
		String endDate = "2015-01"; 
		SimpleDateFormat format = new SimpleDateFormat("YYYY-ww");
		format.setLenient(false);
		DateObject dateObject = new DateObject(startDate, endDate); 
		
		assertEquals(dateObject.getEndDate(), format.parse(endDate)); 
		
	}
}
