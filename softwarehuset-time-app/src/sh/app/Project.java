package sh.app;

import java.util.ArrayList;

public class Project extends DateObject{
	
	private String name;
	private User projectmanager = null;
	private ArrayList<Task> tasks = new ArrayList<Task>();
	
	public Project(String name,  String startDate, String endDate) throws Exception{
		super(startDate, endDate);
		
		// name must not be empty string
		if ( name.equals("") ){
			throw new OperationNotAllowedException("Project name cannot be empty", "Construct project");
		}
		
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
	public void addTask(Task task) throws Exception {
		
		if ( this.getTaskByName(task.getName()) != null ){
			throw new OperationNotAllowedException("Task name must be unique", "Add task");
		}
		else {
			this.tasks.add(task);
		}
		
	}
	
	public Task getTaskByName(String name){
		
		for ( Task task : this.getTasks() ){
			if ( task.getName().equals(name) ){
				return task;
			}
		}
		
		return null;
	}
}
