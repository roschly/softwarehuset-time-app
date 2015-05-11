package sh.app;

import java.util.*;

import sh.app.User;

public class TimeApp {
	private ArrayList<Project> projects = new ArrayList<Project>();
	private ArrayList<User> users = new ArrayList<User>();
	
	public TimeApp(){
		
		// hardcode data
		try {
			User dev01 = new User("dev01");
			User dev02 = new User("dev02");
			User dev03 = new User("dev03"); 
			
			User pm01 = new User("pm01");
			
			this.users.add( dev01 );
			this.users.add( dev02 );
			this.users.add( dev03 );
			this.users.add( pm01 );			 
			
			Project p01 = new Project("p01", "2015-01", "2015-50");
			Project p02 = new Project("p02", "2015-01", "2015-50");
						
			this.projects.add( p01 );
			this.projects.add( p02 );
						
			p01.setProjectmanager(pm01);
						
			Task p01t1 = new Task(p01, "t1", pm01,  5.0, "2015-05", "2015-10");
			Task p01t2 = new Task(p01, "t2", pm01,  5.0, "2015-05", "2015-10");
			Task p01t3 = new Task(p01, "t3", pm01,  5.0, "2015-05", "2015-10");
			Task p01t4 = new Task(p01, "t4", pm01,  5.0, "2015-05", "2015-10");
			Task p01t5 = new Task(p01, "t5", pm01,  5.0, "2015-05", "2015-10");
			Task p01t6 = new Task(p01, "t6", pm01,  5.0, "2015-05", "2015-10");
			Task p01t7 = new Task(p01, "t7", pm01,  5.0, "2015-05", "2015-10");
			Task p01t8 = new Task(p01, "t8", pm01,  5.0, "2015-05", "2015-10");
			Task p01t9 = new Task(p01, "t9", pm01,  5.0, "2015-05", "2015-10");
			Task p01t10 = new Task(p01, "t10", pm01,  5.0, "2015-05", "2015-10");
			
			// dev01 assigned to 9 tasks "2015-05"-"2015-10"
			p01t1.addDeveloper(dev01, pm01, p01, this.getAvailableDevelopers(p01t1));
			p01t2.addDeveloper(dev01, pm01, p01, this.getAvailableDevelopers(p01t2));
			p01t3.addDeveloper(dev01, pm01, p01, this.getAvailableDevelopers(p01t3));
			p01t4.addDeveloper(dev01, pm01, p01, this.getAvailableDevelopers(p01t4));
			p01t5.addDeveloper(dev01, pm01, p01, this.getAvailableDevelopers(p01t5));
			p01t6.addDeveloper(dev01, pm01, p01, this.getAvailableDevelopers(p01t6));
			p01t7.addDeveloper(dev01, pm01, p01, this.getAvailableDevelopers(p01t7));
			p01t8.addDeveloper(dev01, pm01, p01, this.getAvailableDevelopers(p01t8));
			p01t9.addDeveloper(dev01, pm01, p01, this.getAvailableDevelopers(p01t9));
			p01t10.addDeveloper(dev01, pm01, p01, this.getAvailableDevelopers(p01t10));
			
			// dev02 assigned to 10 (max) tasks "2015-05"-"2015-10"
			p01t1.addDeveloper(dev02, pm01, p01, this.getAvailableDevelopers(p01t1));
			p01t2.addDeveloper(dev02, pm01, p01, this.getAvailableDevelopers(p01t2));
			p01t3.addDeveloper(dev02, pm01, p01, this.getAvailableDevelopers(p01t3));
			p01t4.addDeveloper(dev02, pm01, p01, this.getAvailableDevelopers(p01t4));
			p01t5.addDeveloper(dev02, pm01, p01, this.getAvailableDevelopers(p01t5));
			p01t6.addDeveloper(dev02, pm01, p01, this.getAvailableDevelopers(p01t6));
			p01t7.addDeveloper(dev02, pm01, p01, this.getAvailableDevelopers(p01t7));
			p01t8.addDeveloper(dev02, pm01, p01, this.getAvailableDevelopers(p01t8));
			p01t9.addDeveloper(dev02, pm01, p01, this.getAvailableDevelopers(p01t9)); 
			
			
			// dev01 activities
			Activity dev01act1 = new Activity("2015-02-15", 8.0, dev01, p01t1);
			Activity dev01act2 = new Activity("2015-02-16", 3.0, dev01, p01t2); 
			Activity dev01act3 = new Activity("2015-02-15", 5.0, dev01, p01t3); 
			
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
