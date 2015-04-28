package sh.app;

import java.util.*;

import sh.app.User;

public class TimeApp {
	
	public ArrayList<Project> projects = new ArrayList<Project>();
	public ArrayList<User> users = new ArrayList<User>();
	
	public static User currentUser;
	
	public TimeApp(){
		
		// hardcode data
		this.users.add( new User("adm") );
		this.users.add( new User("pm") );
		this.users.add( new User("dev") );
		
		try {
			this.projects.add( new Project("p1", "2015-01", "2015-02") );
			this.projects.add( new Project("p2", "2015-01", "2015-02") );
			this.projects.add( new Project("p3", "2015-01", "2015-02") );
		} catch (Exception e){
			
		}
		
		
	}
	
	public ArrayList<User> getUsers(){
		return this.users;
	}
	
	
	public User getUserByUsername(String username){
		for (User user : this.users){
			if ( user.getUsername().equals(username) ){
				return user; 
			}
		}
		return null;
	}
	
	public Project getProjectByName(String name){
		for (Project project : this.projects){
			if ( project.getName().equals(name) ){
				return project;
			}
		}
		return null;
	}
	
	public ArrayList<Project> getProjects(){
		return this.projects;
	}
}
