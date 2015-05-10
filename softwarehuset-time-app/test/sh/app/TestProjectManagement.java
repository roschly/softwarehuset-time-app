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
	
		@Test
		public void testPMCreateTask() throws Exception{
			
			Project project = new Project("p1","2015-01", "2015-40"); 
			User PM = new User("PM");
			project.setProjectmanager(PM);
		
			String taskName = "taskname"; 
			
			// Test PM add task with unique name
			assertEquals(project.getTasks().size(), 0); 
			Task task = new Task(project,taskName, PM,  5.0, "2015-01", "2015-39"); 
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
		public void testPMCreateTaskIllegalDate() throws Exception{
			
			Project project = new Project("p1","2015-02", "2015-04"); 
			User PM = new User("PM"); 
			project.setProjectmanager(PM);  
			
			assertEquals(project.getTasks().size(), 0); 
			
			// Test PM add task with start date before the project's start date
			try {
				Task task1 = new Task(project,"t1", PM, 1.0, "2015-01", "2015-03");
				fail("OperationNotAllowedException should have been thrown"); 
			} catch (OperationNotAllowedException e) {
				assertEquals(e.getMessage(), "Task start date cannot be before project start date"); 
				assertEquals(e.getOperation(), "Create task"); 
			}
			
			assertEquals(project.getTasks().size(), 0); 
			
			// Test PM add task with end date after the project's end date
			try {
				Task task2 = new Task(project,"t2", PM, 1.0, "2015-03", "2015-05");
				fail("OperationNotAllowedException should have been thrown"); 
			} catch (OperationNotAllowedException e) {
				assertEquals(e.getMessage(), "Task end date cannot be after project end date"); 
				assertEquals(e.getOperation(), "Create task"); 
			}
			
			assertEquals(project.getTasks().size(), 0); 
			
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


}
