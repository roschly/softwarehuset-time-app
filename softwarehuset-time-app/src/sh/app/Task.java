package sh.app;

import java.util.HashSet;
import java.util.ArrayList;

public class Task extends DateObject {
	private String name;
	private Double estimatedTime;
	private HashSet<User> developers = new HashSet<User>();
	private ArrayList<Activity> activities = new ArrayList<Activity>();

	public Task(Project project, String name, Double estimatedTime, String startDate, String endDate) throws Exception{
		super(startDate, endDate);
		
		this.setName(name);
		this.setEstimatedTime(estimatedTime);
		project.addTask(this);
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
	public void addDeveloper(User dev){
		this.developers.add(dev);
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
