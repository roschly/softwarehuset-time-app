package sh.time.app;
import static org.junit.Assert.assertEquals; 
import static org.junit.Assert.assertFalse; 
import static org.junit.Assert.assertTrue; 
import static org.junit.Assert.fail; 

import java.util.List; 

import org.junit.Test; 

public class TestCreateProject {

	@Test
	public void testLogIn() {
		TimeApp timeApp = new TimeApp(); 
		
		//check admin is not logged in
		assertFalse(timeApp.adminLoggedIn()); 
		
		// wrong admin login
		boolean login = timeApp.adminLogIn("wrong"); 
		
		//check admin logged in
		assertFalse(login);
		assertFalse(timeApp.adminLoggedIn()); 
				
		// correct admin login
		login = timeApp.adminLogIn("adminadmin"); 
		
		//check admin logged in
		assertTrue(login);
		assertTrue(timeApp.adminLoggedIn()); 
	}
	
	@Test
	public void testAdminCreateProject() {
		TimeApp timeApp = new TimeApp(); 
		
		assertFalse(timeApp.adminLoggedIn()); 
		
		// correct admin login
		boolean login = timeApp.adminLogIn("adminadmin");
		
		//check admin logged in
		assertTrue(login);
		assertTrue(timeApp.adminLoggedIn()); 
		
		String name = "Project1";
		Project project1 = new Project(name); 
		
		
		// check adding project increases size 
		List<Project> projects = timeApp.getProjects();
		int preSize = projects.size();
		timeApp.addProject(project1);
		assertEquals(projects.size(), preSize + 1);
	}
	
	
}
