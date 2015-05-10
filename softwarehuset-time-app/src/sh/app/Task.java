package sh.app;

import java.util.HashSet;
import java.util.ArrayList;

public class Task extends DateObject {
	private String name;
	private Double estimatedTime;
	private HashSet<User> developers = new HashSet<User>();
	private ArrayList<Activity> activities = new ArrayList<Activity>();

	public Task(Project project, String name, User user, Double estimatedTime, String startDate, String endDate) throws Exception{
		super(startDate, endDate);
		
		// Task duration must be contained in project duration
		if ( this.getStartDate().before(project.getStartDate()) ){
			throw new OperationNotAllowedException("Task start date cannot be before project start date", "Create task");
		}
		
		if ( project.getEndDate().before(this.getEndDate()) ){
			throw new OperationNotAllowedException("Task end date cannot be after project end date", "Create task");
		}
		
		this.setName(name);
		this.setEstimatedTime(estimatedTime);
		
		// If user is not PM on project, task is not added to project
		if (user.equals(project.getProjectmanager())){ 
				project.addTask(this);
		} else {
			throw new OperationNotAllowedException("Must be project manager to create task", "Create task"); 
		}
	}
	
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public Double getEstimatedTime(){
		return this.estimatedTime;
	}
	
	public void setEstimatedTime(Double time){
		this.estimatedTime = time;
	}
	
	public HashSet<User> getDevelopers(){
		return this.developers;
	}
	
	public void addDeveloper(User dev, User PM, Project project, ArrayList<User> availableDevs) throws Exception{
		if (! project.getProjectmanager().equals(PM) ) {
			throw new OperationNotAllowedException("Must be projectmanager to assign developer to task", "Assign developer"); 
		} 
		else if ( ! availableDevs.contains(dev)){
			throw new OperationNotAllowedException("The chosen developer is not available", "Assign developer"); 
		} 
		else {
			this.developers.add(dev);
		}
	}
	
	public ArrayList<Activity> getActivities(){
		return this.activities;
	}
	
	public void addActivity(Activity activity){
		this.activities.add(activity);
	}
	
	public Activity getActivityById(Integer id){
		for ( Activity activity : this.getActivities() ){
			if ( activity.getId() == id ){
				return activity;
			}
		}
		return null;
	}
}