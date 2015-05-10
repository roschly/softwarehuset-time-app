package sh.app;

import java.util.*;

import sh.app.User;

public class TimeApp {
	private ArrayList<Project> projects = new ArrayList<Project>();
	private ArrayList<User> users = new ArrayList<User>();
	
	public TimeApp(){
		
		/*
		// hardcode data
		try {
			User dev01 = new User("dev01");
			User dev02 = new User("dev02");
			User dev03 = new User("dev03");
			User dev04 = new User("dev04");
			User dev05 = new User("dev05");
			User dev06 = new User("dev06"); 
			
			User pm01 = new User("pm01");
			User pm02 = new User("pm02"); 
			User pm03 = new User("pm03"); 
			
			this.users.add( dev01 );
			this.users.add( dev02 );
			this.users.add( dev03 );
			this.users.add( dev04 );
			this.users.add( dev05 );
			this.users.add( dev06 );
			this.users.add( pm01 );
			this.users.add( pm02 );
			this.users.add( pm03 );
			 
			
			Project p01 = new Project("p01", "2015-01", "2015-50");
			Project p02 = new Project("p02", "2014-01", "2015-50");
			Project p03 = new Project("p02", "2015-02", "2015-03");
			
			this.projects.add( p01 );
			this.projects.add( p02 );
			this.projects.add( p03 );
			
			p01.setProjectmanager(pm01);
			p02.setProjectmanager(pm02);
			p03.setProjectmanager(pm03);
			
			Task p01t1 = new Task(p01, "t1", pm01,  5.0, "2015-05", "2015-10");
			Task p01t2 = new Task(p01, "t2", pm01,  5.0, "2015-10", "2015-15");
			Task p01t3 = new Task(p01, "t3", pm01,  5.0, "2015-15", "2015-20");
			Task p01t4 = new Task(p01, "t4", pm01,  5.0, "2015-20", "2015-25");
			Task p01t5 = new Task(p01, "t5", pm01,  5.0, "2015-25", "2015-30");
			Task p01t6 = new Task(p01, "t6", pm01,  5.0, "2015-30", "2015-35");
			Task p01t7 = new Task(p01, "t7", pm01,  5.0, "2015-35", "2015-40");
			Task p01t8 = new Task(p01, "t8", pm01,  5.0, "2015-40", "2015-45");
			Task p01t9 = new Task(p01, "t9", pm01,  5.0, "2015-45", "2015-50");
			
			Task p02t1 = new Task(p02, "t1", pm02,  5.0, "2015-05", "2015-10");
			Task p02t2 = new Task(p02, "t2", pm02,  5.0, "2015-05", "2015-10");
			Task p02t3 = new Task(p02, "t3", pm02,  5.0, "2015-05", "2015-10");
			Task p02t4 = new Task(p02, "t4", pm02,  5.0, "2015-05", "2015-10");
			Task p02t5 = new Task(p02, "t5", pm02,  5.0, "2015-05", "2015-10");
			Task p02t6 = new Task(p02, "t6", pm02,  5.0, "2015-05", "2015-10");
			Task p02t7 = new Task(p02, "t7", pm02,  5.0, "2015-05", "2015-10");
			Task p02t8 = new Task(p02, "t8", pm02,  5.0, "2015-05", "2015-10");
			Task p02t9 = new Task(p02, "t9", pm02,  5.0, "2015-05", "2015-10");
			Task p02t10 = new Task(p02, "t10", pm02,  5.0, "2015-05", "2015-10");
			
			// dev01 assigned to 9 tasks "2015-05"-"2015-10"
			p02t1.addDeveloper(dev01, pm02, p02, this.getAvailableDevelopers(p02t1));
			p02t2.addDeveloper(dev01, pm02, p02, this.getAvailableDevelopers(p02t2));
			p02t3.addDeveloper(dev01, pm02, p02, this.getAvailableDevelopers(p02t3));
			p02t4.addDeveloper(dev01, pm02, p02, this.getAvailableDevelopers(p02t4));
			p02t5.addDeveloper(dev01, pm02, p02, this.getAvailableDevelopers(p02t5));
			p02t6.addDeveloper(dev01, pm02, p02, this.getAvailableDevelopers(p02t6));
			p02t7.addDeveloper(dev01, pm02, p02, this.getAvailableDevelopers(p02t7));
			p02t8.addDeveloper(dev01, pm02, p02, this.getAvailableDevelopers(p02t8));
			p02t9.addDeveloper(dev01, pm02, p02, this.getAvailableDevelopers(p02t9));
			
			// dev02 assigned to 10 (max) tasks "2015-05"-"2015-10"
			p02t1.addDeveloper(dev02, pm02, p02, this.getAvailableDevelopers(p02t1));
			p02t2.addDeveloper(dev02, pm02, p02, this.getAvailableDevelopers(p02t2));
			p02t3.addDeveloper(dev02, pm02, p02, this.getAvailableDevelopers(p02t3));
			p02t4.addDeveloper(dev02, pm02, p02, this.getAvailableDevelopers(p02t4));
			p02t5.addDeveloper(dev02, pm02, p02, this.getAvailableDevelopers(p02t5));
			p02t6.addDeveloper(dev02, pm02, p02, this.getAvailableDevelopers(p02t6));
			p02t7.addDeveloper(dev02, pm02, p02, this.getAvailableDevelopers(p02t7));
			p02t8.addDeveloper(dev02, pm02, p02, this.getAvailableDevelopers(p02t8));
			p02t9.addDeveloper(dev02, pm02, p02, this.getAvailableDevelopers(p02t9)); 
			p02t10.addDeveloper(dev02, pm02, p02, this.getAvailableDevelopers(p02t10));
			
		} catch (Exception e){
		}
		*/
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
