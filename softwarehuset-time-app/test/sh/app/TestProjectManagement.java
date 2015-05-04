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

public class TestProjectManagement {
	
	@Before
	public void setUp() throws OperationNotAllowedException{
		TimeApp timeApp = new TimeApp(); 
		
		
	}
	
	//  Where to put this
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
		Project project = new Project("p1","2015-01", "2015-04");
		User PM = new User("PM");
		timeApp.addUser(PM);
		project.setProjectmanager(PM); 
		Task task = new Task(project, "taskname", PM, 5.5,"2015-02", "2015-03"); 
		User user = new User("dev");
		timeApp.addUser(user);
		task.addDeveloper(user, PM, project, timeApp.getAvailableDevelopers(task));
		Activity activity = new Activity("2015-01-01", 5.0, user, task);
		
		// Correct user
		assertEquals(activity.getDeveloper(), user); 
		
		// Wrong user
		User wrongUser = new User("dev1");
		assertNotEquals(activity.getDeveloper(), wrongUser); 
	}
	
	@Test
	public void testGetDate() throws Exception {
		TimeApp timeApp = new TimeApp(); 
		Project project = new Project("p1","2015-01", "2015-04");
		User PM = new User("PM");
		timeApp.addUser(PM);
		project.setProjectmanager(PM); 
		Task task = new Task(project, "taskname", PM,  5.5,"2015-02", "2015-03"); 
		User user = new User("dev");
		timeApp.addUser(user);
		task.addDeveloper(user, PM, project, timeApp.getAvailableDevelopers(task));
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
	
	@Test
	public void testGetEndDate() throws Exception{
		String startDate = "2014-01"; 
		String endDate = "2015-01"; 
		SimpleDateFormat format = new SimpleDateFormat("YYYY-ww");
		format.setLenient(false);
		DateObject dateObject = new DateObject(startDate, endDate); 
		
		assertEquals(dateObject.getEndDate(), format.parse(endDate)); 
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
		
		TimeApp timeApp = new TimeApp();
		assertEquals(timeApp.getProjects().size(), 0);
		
		// Add project with unique name
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
		
		// Empty string as project name
		try {
			project = new Project("",startDate, endDate);  
			fail("OperationNotAllowedException should have been thrown"); 
		} catch (OperationNotAllowedException e) {
			assertEquals(e.getMessage(), "Project name cannot be empty"); 
			assertEquals(e.getOperation(), "Construct project"); 
		}
	}
	
	@Test 
	public void testAssignProjectManager() throws Exception{
		Project project = new Project("p1","2015-01", "2015-02");
		User user1 = new User("dev1");
		
		project.setProjectmanager(user1);
		assertEquals(project.getProjectmanager(), user1); 
	}
	
	@Test
	public void testSelectProject() throws Exception {
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
	public void testPMCreateTask() throws Exception{
		
		Project project = new Project("p1","2015-01", "2015-02"); 
		User PM = new User("PM"); 
		project.setProjectmanager(PM);
	
		String taskName = "taskname"; 
		
		// Test PM add task with unique name
		assertEquals(project.getTasks().size(), 0); 
		Task task = new Task(project,taskName, PM,  5.0, "2015-01", "2015-02"); 
		assertEquals(project.getTasks().size(), 1); 
		
		// Test PM add task with not unique name
		try {
			Task task1 = new Task(project,taskName, PM, 1.0, "2015-11", "2015-20");
			fail("OperationNotAllowedException should have been thrown"); 
		} catch (OperationNotAllowedException e) {
			assertEquals(e.getMessage(), "Task name must be unique"); 
			assertEquals(e.getOperation(), "Add task"); 
		}
		assertEquals(project.getTasks().size(), 1); 
		
	}
	
	@Test
	public void testNotPMCreateTask() throws Exception{
		TimeApp timeApp = new TimeApp(); 
		
		Project project = new Project("p1","2015-01", "2015-02"); 
		User dev = new User("dev"); 
		User PM = new User("PM"); 
		project.setProjectmanager(PM);
		String taskName = "taskname"; 
		
		// Test NOT PM add task
		assertEquals(project.getTasks().size(), 0); 
		
		try {
			Task task = new Task(project,taskName, dev, 5.0, "2015-01", "2015-02"); 
			fail("OperationNotAllowsException should have been thrown"); 
		} catch (OperationNotAllowedException e) {
			assertEquals(e.getMessage(), "Must be project manager to create task"); 
			assertEquals(e.getOperation(), "Create task"); 
		}
		assertEquals(project.getTasks().size(), 0); 
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
		Project project = new Project("p1", "2015-01", "2015-02");
		timeApp.addProject(project);
		User PM = new User("PM");
		project.setProjectmanager(PM);
		Task task = new Task(project, "task", PM, 5.0, "2014-03", "2014-06");
		
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
			Task taskName = new Task(project, "t" + i, PM, 5.0, "2014-01", "2014-10");
			taskName.addDeveloper(user1, PM, project, timeApp.getAvailableDevelopers(taskName));
		}
		
		SimpleDateFormat format = new SimpleDateFormat("YYYY-ww");
		format.setLenient(false);
		
		assertFalse(timeApp.isAvailable(user1, format.parse("2014-02")));
		assertTrue(timeApp.isAvailable(user2, format.parse("2014-02")));
		
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
		Project project = new Project("p1", "2015-01", "2015-02");
		timeApp.addProject(project);
		User PM = new User("PM");
		timeApp.addUser(PM);
		
		project.setProjectmanager(PM);
	
		User user = new User("dev"); 
		timeApp.addUser(user);
		
		//Loop til at assigne usere til tasks
		for ( int i = 1; i <= 10; i++ ){
			Task taskName = new Task(project, "t" + i, PM, 5.0, "2014-01", "2014-10");
			taskName.addDeveloper(user, PM, project, timeApp.getAvailableDevelopers(taskName));
		}
		
		assertEquals(user.getMaxActivities(), 10); 
		
		// The user has reached the max number of activities in the timeslot of the task
		Task task = new Task(project, "newtask" , PM, 5.0, "2014-01", "2014-10");
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
	
	// TestRegisterTime
	
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
		Project project = new Project("p1", "2015-01", "2015-02");
		User PM = new User("PM");
		timeApp.addUser(PM);
		project.setProjectmanager(PM); 
		Task task = new Task(project, "taskname", PM, 5.5,"2015-02", "2015-03"); 
		User user = new User("dev");
		timeApp.addUser(user);
		task.addDeveloper(user, PM, project, timeApp.getAvailableDevelopers(task));
		
		// Test add activity when user is assigned to task
		assertEquals(task.getActivities().size(), 0);
		Activity activity = new Activity("2015-01-01", 5.0, user, task);
		assertEquals(task.getActivities().size(), 1);
	}	
	
	@Test
	public void testCreateActivityOnNotAssignedTask() throws Exception{
		TimeApp timeApp = new TimeApp();
		Project project = new Project("p1", "2015-01", "2015-02");
		User PM = new User("PM");
		project.setProjectmanager(PM); 
		Task task = new Task(project, "taskname", PM, 5.5,"2015-02", "2015-03"); 
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
		Project project = new Project("p1", "2015-01", "2015-02");
		User PM = new User("PM");
		timeApp.addUser(PM);
		project.setProjectmanager(PM); 
		Task task = new Task(project, "taskname", PM, 5.5,"2015-02", "2015-03"); 
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
	
	// TestEditRegisteredTime
	@Test 
	public void testSelectActivity() throws Exception{
		TimeApp timeApp = new TimeApp();
		Project project = new Project("p1","2015-01", "2015-04");
		User PM = new User("PM");
		timeApp.addUser(PM);
		project.setProjectmanager(PM); 
		Task task = new Task(project, "taskname", PM,  5.5,"2015-02", "2015-03"); 
		User user = new User("dev");
		timeApp.addUser(user);
		task.addDeveloper(user, PM, project, timeApp.getAvailableDevelopers(task));
		Activity activity = new Activity("2015-01-01", 5.0, user, task);
		
		//Test activity is in task
		assertEquals(task.getActivityById(activity.getId()), activity); 
		
		//Test activity is not in another task
		Task wrongTask = new Task(project, "taskname1", PM, 5.5,"2015-02", "2015-03"); 
		assertEquals(wrongTask.getActivityById(activity.getId()), null); 
		
		//Test activity that does not exist
		assertEquals(task.getActivityById(1000), null); 
	}
	
	@Test
	public void testEditOwnActivity() throws Exception{
		TimeApp timeApp = new TimeApp();
		Project project = new Project("p1","2015-01", "2015-04");
		User PM = new User("PM");
		timeApp.addUser(PM);
		project.setProjectmanager(PM); 
		Task task = new Task(project, "taskname", PM, 5.5,"2015-02", "2015-03"); 
		User user = new User("dev");
		timeApp.addUser(user);
		task.addDeveloper(user, PM, project, timeApp.getAvailableDevelopers(task));
		Activity activity = new Activity("2015-01-01", 5.0, user, task);
		
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
	public void testEditActivityPM() throws Exception{
		TimeApp timeApp = new TimeApp();
		Project project = new Project("p1","2015-01", "2015-04");
		User PM = new User("PM");
		timeApp.addUser(PM);
		project.setProjectmanager(PM); 
		Task task = new Task(project, "taskname", PM, 5.5,"2015-02", "2015-03"); 
		User user = new User("dev");
		timeApp.addUser(user);
		task.addDeveloper(user, PM, project, timeApp.getAvailableDevelopers(task));
		Activity activity = new Activity("2015-01-01", 5.0, user, task);
		
		Double dur = 1.5;
		
		// Test set a duration divisable by 0.5 AND not 0
		activity.changeDuration(dur, PM, project);
		assertEquals(activity.getDuration(), dur);
		
	}
	
	@Test
	public void testEditActivityNotOwnNotPM() throws Exception{
		TimeApp timeApp = new TimeApp();
		Project project = new Project("p1","2015-01", "2015-04");
		User PM = new User("PM");
		timeApp.addUser(PM);
		project.setProjectmanager(PM); 
		Task task = new Task(project, "taskname", PM, 5.5,"2015-02", "2015-03"); 
		User user = new User("dev");
		timeApp.addUser(user);
		task.addDeveloper(user, PM, project, timeApp.getAvailableDevelopers(task));
		Activity activity = new Activity("2015-01-01", 5.0, user, task);
		User wrongUser = new User("wrongUser");
		
		Double dur = 1.5;
		
		// Try to edit other developers activity when NOT PM
		try {
			activity.changeDuration(dur, wrongUser, project);
			fail("OperationNotAllowedException should have been thrown"); 
		} catch (OperationNotAllowedException e){
			assertEquals(e.getMessage(), "Must be projectmanager to edit another developers activity");
			assertEquals(e.getOperation(), "Edit activity");
		}
		assertNotEquals(activity.getDuration(), dur);
	}

}
