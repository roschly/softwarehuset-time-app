package sh.app;

import java.util.*;

import sh.app.User;

public class TimeApp {
	
	private ArrayList<Project> projects = new ArrayList<Project>();
	private ArrayList<User> users = new ArrayList<User>();
	
	public TimeApp(){
		
		
		// hardcode data
		try {
			User dev = new User("dev");
			User dev2 = new User("dev2");
			User pm = new User("pm");
			this.users.add( pm );
			this.users.add( dev );
			this.users.add( dev2 ); 
			
			Project p1 = new Project("p1", "2015-01", "2015-02");
			Project p2 = new Project("p2", "2015-01", "2015-02");
			this.projects.add( p1 );
			this.projects.add( p2 );
			
			p1.setProjectmanager(pm);
			
			Task t1 = new Task(p1, "t1", pm,  5.0, "2015-02", "2015-03");
			t1.addDeveloper(dev, pm, p1);
			Activity activity = new Activity("2015-01-01", 8.0, dev, t1); 
			//new Task(p1, "t2", 10.0, "2015-02", "2015-03");
			
			//new Activity("2015-01-01", 8.0, dev, t1);
			//new Activity("2015-01-02", 7.5, dev, t1);
		} catch (Exception e){
			
		}
		
		
	}
	
	public ArrayList<User> getUsers(){
		return this.users;
	}
	
	public void addUser(User user) throws Exception{
		if ( this.getUserByName(user.getName()) != null ){
			throw new OperationNotAllowedException("User name must be unique", "Add user");
		}
		else {
			this.users.add(user);
		}
	}
	
	public ArrayList<Project> getProjects(){
		return this.projects;
	}
	
	public void addProject(Project project) throws Exception{
		
	
		// Project with that name already exists
		if ( this.getProjectByName(project.getName()) != null ){
			throw new OperationNotAllowedException("Project name must be unique", "Add project");
		} else {
			this.projects.add(project);
			//project.setTimeApp(this);
		}
	}
	
	public User getUserByName(String name){
		for (User user : this.users){
			if ( user.getName().equals(name) ){
				return user; 
			}
		}
		return null;
	}
	public Project getProjectByName(String name){
		for (Project project : this.getProjects()){
			if ( project.getName().equals(name) ){
				return project;
			}
		}
		return null;
	}
	
}
