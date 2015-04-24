package sh.time.app;

import java.util.ArrayList;

public class Project {
	
	private static int nextProjectId = 0;
	
	private int id;
	private String name;
	private Developer projectmanager = null;
	private ArrayList<Task> tasks = new ArrayList<Task>();
	
	
	
	public Project(String name) {
		this.id = Project.nextProjectId;
		Project.nextProjectId++;
		
		this.name = name; 
	}
	
	public void assignProjectmanager(Developer dev){
		this.projectmanager = dev;
	}
	
	public Developer getProjectmanager(){
		return this.projectmanager;
	}
	
	public void addTask(Task task){
		this.tasks.add(task);
	}
	
	public ArrayList<Task> getTasks(){
		return this.tasks;
	}

}
