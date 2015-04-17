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
		boolean login = timeApp.logIn("wrong"); 
		
		//check admin logged in
		assertFalse(login);
		assertFalse(timeApp.adminLoggedIn()); 
				
		// correct admin login
		login = timeApp.logIn("adminadmin"); 
		
		//check admin logged in
		assertTrue(login);
		assertTrue(timeApp.adminLoggedIn()); 
	}
	
	
	@Test
	public void testAdminAddProject() throws Exception {
		TimeApp timeApp = new TimeApp(); 
		
		assertFalse(timeApp.adminLoggedIn()); 
		
		// correct admin login
		boolean login = timeApp.logIn("adminadmin");
		
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
	
	@Test
	public void testAdminCreateProjectNotLoggedIn() throws Exception {
		TimeApp timeApp = new TimeApp(); 
		
		assertFalse(timeApp.adminLoggedIn()); 
		
		String name = "Project1";
		Project project1 = new Project(name); 
		
		try { 
			timeApp.addProject(project1);
			fail("OperationNotAllowedException exception should have been thrown"); 
		} catch (OperationNotAllowedException e){
			assertEquals("Add project operation not allowed if not admin.", e.getMessage()); 
			assertEquals("Add project", e.getOperation()); 
		}
	}
	
//	@Test 
//	public void testAdminCreateProject() throws Exception {
//		TimeApp timeApp = new TimeApp();
//		
//		a
//		
//	}
	
	
}
