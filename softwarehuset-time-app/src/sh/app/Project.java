package sh.app;

import java.util.ArrayList;

public class Project extends DateObject{
	private String name;
	private Projectmanager projectmanager;
	private ArrayList<Task> tasks;
	
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
	
	public Projectmanager getProjectmanager() {
		return this.projectmanager;
	}
	
	public void setProjectmanager(Projectmanager projectmanager) {
		this.projectmanager = projectmanager;
	}
	public ArrayList<Task> getTasks() {
		return this.tasks;
	}
	
	public void addTask(Task task) {
		this.tasks.add(task);
	}
}
