package sh.app;

import java.util.ArrayList;

public class Project extends DateObject{
	private String name;
	private User projectmanager;
	private ArrayList<Task> tasks;
	
	// TODO: Fail if projectname is not unique
	public Project(String name, String startDate, String endDate) throws Exception{
		super(startDate, endDate);
		this.setName(name);
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public User getProjectmanager() {
		return this.projectmanager;
	}
	
	public void setProjectmanager(User projectmanager) {
		this.projectmanager = projectmanager;
	}
	public ArrayList<Task> getTasks() {
		return this.tasks;
	}
	
	public void addTask(Task task) {
		this.tasks.add(task);
	}
}
