package sh.app;

import java.util.Date;

public class User {
	
	private String name;
	private int maxActivities = 10; 
	
	public User(String name){
		this.setName(name);
	}
	
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	public int getMaxActivities(){
		return this.maxActivities;
	}
	
	public void setMaxActivities(int maxActivities){
		this.maxActivities = maxActivities;
	}

}
