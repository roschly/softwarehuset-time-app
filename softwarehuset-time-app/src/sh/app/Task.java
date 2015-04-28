package sh.app;

import java.util.HashSet;
import java.util.ArrayList;

public class Task extends DateObject {
	private String name;
	private Double estimatedTime;
	private HashSet<User> developers = new HashSet<User>();


	public Task(String name, Double estimatedTime, String startDate, String endDate) throws Exception{
		super(startDate, endDate);
		
		this.setName(name);
		this.setEstimatedTime(estimatedTime);	
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
	
}
