package sh.time.app;

import java.util.*;

public class TimeApp {
	
	boolean loggedIn = false;
	List<Project> projects = new ArrayList<Project>(); 
	
	public boolean adminLoggedIn() {
		if (loggedIn) {
			return true; 
		}
		return false;
	}

	public boolean adminLogIn(String string) {
		if (string.equals("adminadmin")){
			loggedIn = true; 
			return true; 
		} 
		return false; 
	}

	public void addProject(Project project) {
		this.projects.add(project);
		
	}

	public List<Project> getProjects() {
		return this.projects;
	}

}
