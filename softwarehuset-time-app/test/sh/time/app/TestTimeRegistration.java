package sh.time.app;

import org.junit.Test;

import static org.junit.Assert.assertEquals; 
import static org.junit.Assert.assertFalse; 
import static org.junit.Assert.assertTrue; 
import static org.junit.Assert.fail; 

import java.util.Date;
import java.util.HashMap;

public class TestTimeRegistration {
	
	@Test
	public void testDeveloperCreateProjectActivity() throws Exception{
		TimeApp timeApp = new TimeApp();
		
		Developer dev = new Developer("dev", "dev", "developer");
		
		Project project = new Project("projectname");
		
		// assign developer to project
		project.setDeveloper(dev);
		
		// developer create activity
		Date date = new Date();
		HashMap<Task, Double> taskList = new HashMap<Task, Double>();
		Task task = new Task("taskname");
		taskList.put(task, 4.0);
		
		// check creating activity increases timeapp.activities
		int preSize = TimeApp.activities.size();
		dev.createProjectActivity(date, taskList);
		assertEquals(TimeApp.activities.size(), preSize + 1);
	}
	
	// Developer creates project activity and assigns a task from a project that the developer is not a part of
	@Test
	public void testDeveloperCreateProjectActivityThatDeveloperIsNotAssignedTo() throws Exception{
		TimeApp timeApp = new TimeApp();
		Developer dev = new Developer("dev", "dev", "developer");
		
		Project project = new Project("projectname");
		
		assertFalse(project.isAssignedAsDeveloper(dev));
		
		Task task = new Task("taskname1");
		Date date = new Date();
		HashMap<Task, Double> taskList = new HashMap<Task, Double>();
		taskList.put(task, 4.0);
		
		try {
			dev.createProjectActivity(date, taskList);
			fail("MissingRightsException should have been thrown");
		} catch (MissingRightsException e) {
			assertEquals("Assigning a task from a project that the developer is not a part of, is not allowed", e.getMessage());
			assertEquals("Create project activity", e.getOperation());
		}
		
		/*
		// check creating activity increases timeapp.activities
		int preSize = TimeApp.activities.size();
		dev.createProjectActivity(date, taskList);
		assertEquals(TimeApp.activities.size(), preSize);
		*/
	}
	
	
}
