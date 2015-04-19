package sh.time.app;
import static org.junit.Assert.assertEquals; 
import static org.junit.Assert.assertNotEquals; 
import static org.junit.Assert.assertFalse; 
import static org.junit.Assert.assertTrue; 
import static org.junit.Assert.fail; 

import java.util.List; 

import org.junit.Test; 

public class TestCreateProject {
	
	@Test
	public void testCreateProject() {
		TimeApp timeApp = new TimeApp(); 
		
		Integer preSize = timeApp.projects.size();
				
		Admin admin = new Admin();
		
		admin.createProject("nytProject");
		assertEquals(timeApp.projects.size(), preSize + 1); 
	}

	/*@Test
	public void testLogIn() {
		TimeApp timeApp = new TimeApp(); 
		
		//check admin is not logged in
		String role = "admin";
		assertFalse(timeApp.logIn(username, )
		assertNotEquals(timeApp.loggedIn(), role); 
		
		// wrong admin login
		String username = "wrongUser"; 
		String password = "wrongPassword"; 
		
		boolean login = timeApp.user.logIn(username, password); 
		
		//check admin logged in
		assertFalse(login);
		assertNotEquals(timeApp.user.loggedIn(), role);  
				
		// correct admin login
		username = "admin"; 
		password = "adminadmin"; 
		login = timeApp.user.logIn(username, password); 
		
		//check admin logged in
		assertTrue(login);
		assertEquals(timeApp.user.loggedIn(), role); 
		
	}
	
	
	
	@Test
	public void testAdminAddProject() throws Exception {
		TimeApp timeApp = new TimeApp(); 
		
		String name = "Project1";
		Project project1 = new Project(name); 
		
		
		// check adding project increases size 
		List<Project> projects = timeApp.getProjects();
		int preSize = projects.size();
		timeApp.addProject(project1);
		assertEquals(projects.size(), preSize + 1);
	}
	
	@Test
	public void testAdminAddProjectNotLoggedIn() throws Exception {
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
	*/
	
}
