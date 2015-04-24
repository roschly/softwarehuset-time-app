package sh.time.app;

import java.util.ArrayList;

public class Project {
	
	private static int nextProjectId = 0;
	
	private int id;
	private String name;
	private Developer projectmanager = null;
	private ArrayList<Developer> developers = new ArrayList<Developer>();
	private ArrayList<Task> tasks = new ArrayList<Task>();
	
	
	
	public Project(String name) {
		this.id = Project.nextProjectId;
		Project.nextProjectId++;
		
		this.name = name; 
	}
	
	public Boolean isAssignedAsDeveloper(Developer dev){
		return this.developers.contains(dev);
	}
	
	public void setProjectmanager(Developer dev){
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
	
	public void setDeveloper(Developer dev){
		this.developers.add(dev);
	}
	
	public ArrayList<Developer> getDevelopers(){
		return this.developers;
	}

}
