package sh.app;

import java.util.HashSet;

public class Task extends DateObject {
	private String name;
	private Double estimatedTime;
	private HashSet<Developer> developers = new HashSet<Developer>();
	private HashSet<Activity> activities = new HashSet<Activity>();


	public Task(){
		
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
	
	public HashSet<Developer> getDevelopers(){
		return this.developers;
	}
	public void addDeveloper(Developer dev){
		this.developers.add(dev);
	}
	
	public HashSet<Activity> getActivities(){
		return this.activities;
	}
	public void addActivity(Activity activity){
		this.activities.add(activity);
	}
}
