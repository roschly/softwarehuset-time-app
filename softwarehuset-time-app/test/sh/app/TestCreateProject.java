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

public class TestCreateProject {
	
	// Test creation of project
			@Test
			public void testCreateProject() throws Exception{
				String startDate = "2015-02"; 
				String endDate = "2015-03"; 
				
				// Correct creation of project
				Project project = new Project("p1",startDate, endDate); 
				
				TimeApp timeApp = new TimeApp();
				int presize = timeApp.getProjects().size();
				
				// Add project with unique name
				timeApp.addProject(project); 

				assertEquals(timeApp.getProjects().size(), presize + 1);
				
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
}
