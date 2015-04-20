package sh.time.app;

import org.junit.Test;
import static org.junit.Assert.assertEquals; 
import static org.junit.Assert.assertFalse; 
import static org.junit.Assert.assertTrue; 
import static org.junit.Assert.fail; 



public class TestProjectmanagement {
	
	@Test
	public void testCreateTaskAsProjectmanager(){
		Developer dev = new Developer();
		
		Project project = new Project("projectname");
		
		// Make developer a project manager
		project.assignProjectmanager(dev);
		
		int preSize = project.getTasks().size();
		 
		dev.createTask(project, "taskname");
		
		assertEquals(project.getTasks().size(), preSize + 1);
	}
	
	@Test
	public void testCreateTaskAsNotProjectmanager(){
		Developer dev = new Developer();
		Project project = new Project("projectname");
		
		int preSize = project.getTasks().size();
		 
		dev.createTask(project, "taskname");
		
		assertEquals(project.getTasks().size(), preSize);
	}
	

}
