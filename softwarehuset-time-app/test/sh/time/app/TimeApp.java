package sh.time.app;

import java.util.*;

public class TimeApp {

	private String user; 
	private List<Project> projects = new ArrayList<Project>(); 
	
	public boolean adminLoggedIn() {
		if (this.user == "admin") {
			return true; 
		}
		return false;
	}

	public boolean logIn(String string) {
		if (string.equals("adminadmin")){
			this.user = "admin"; 
			return true; 
		} 
		return false; 
	}

	public void addProject(Project project) throws OperationNotAllowedException {
		if (! (this.user == "admin")) {
			throw new OperationNotAllowedException("Add project operation not allowed if not admin.", "Add project"); 
		} else {
			this.projects.add(project);
		}
		
		
	}

	public List<Project> getProjects() {
		return this.projects;
	}

}
