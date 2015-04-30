package sh.time.app;

import org.junit.Test;
import static org.junit.Assert.assertEquals; 
import static org.junit.Assert.assertFalse; 
import static org.junit.Assert.assertTrue; 
import static org.junit.Assert.fail; 



public class TestProjectmanagement {
	
	@Test
	public void testCreateTaskAsProjectmanager() throws MissingRightsException{
		Developer dev = new Developer("username", "password", "developer");
		
		Project project = new Project("projectname");
		
		// Make developer a project manager
		project.setProjectmanager(dev);
		
		int preSize = project.getTasks().size();
		
		dev.createTask(project, "taskname");
		
		// tasklist increase by 1
		assertEquals(project.getTasks().size(), preSize + 1);
	}
	
	@Test
	public void testCreateTaskAsNotProjectmanager() throws MissingRightsException{
		Developer dev = new Developer("username", "password", "developer");
		Project project = new Project("projectname");
		
		int preSize = project.getTasks().size();
		
		try {
			dev.createTask(project, "taskname");
			fail("MissingRightsException should have been thrown");
		} catch (MissingRightsException e) {
			assertEquals("Create task operation not allowed if not project manager", e.getMessage());
			assertEquals("Create task", e.getOperation());
		}
		
		// no increase in task list
		assertEquals(project.getTasks().size(), preSize);
	}
}
