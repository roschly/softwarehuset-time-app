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
			t1.addDeveloper(dev, pm, p1, this.getAvailableDevelopers(t1));
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

	public ArrayList<User> getAvailableDevelopers(Task task) {
		ArrayList<User> availableDevelopers = new ArrayList<User>();
		Calendar cal = Calendar.getInstance(); 
		int startWeek, endWeek;
		
		cal.setTime(task.getEndDate());
		endWeek = cal.get(Calendar.WEEK_OF_YEAR);
		
		cal.setTime(task.getStartDate());
		startWeek = cal.get(Calendar.WEEK_OF_YEAR); 
		
		int diff = endWeek-startWeek; 
		
		for (User user : users){
			cal.setTime(task.getStartDate()); 
			//while ( !cal.getTime().after(task.getEndDate()) ){
			for (int i = 0; i < diff; i++) {
				cal.add(Calendar.WEEK_OF_YEAR, i);
				if ( ! isAvailable(user, cal.getTime()) ){
					break; 
				}
				if (i == diff-1 ) {
					availableDevelopers.add(user); 
				}
			}
		}
		return availableDevelopers;
	}

	public boolean isAvailable(User user, Date time) {
		int taskCount = 0; 
		for (Project project : projects) {
			for( Task task : project.getTasks() ) {
				if( task.getDevelopers().contains(user) && !task.getStartDate().after(time) && !task.getEndDate().before(time) ) {
					taskCount ++; 
				}
			}
		}
		return taskCount < user.getMaxActivities();
	}
}
