package sh.app;

import java.util.*;
import sh.app.User;

public class TimeApp {
	
	public static ArrayList<Project> projects = new ArrayList<Project>();
	private ArrayList<User> users = new ArrayList<User>();
	public static HashSet<String> roles = new HashSet<String>();
	
	public static User currentUser;
	
	public static void main(String[] args){
		
		
	}
	
	public ArrayList<User> getUsers(){
		return this.users;
	}
	
	public HashSet<String> getRoles(){
		return this.roles;
	}
	
	public ArrayList<User> getUsersByProperty(String property, String value){
		
		ArrayList<User> users = new ArrayList<User>();
		
		
		for (User user : this.users){
			
			switch (property){
			case "username":
				if (user.getUsername().equals(value)){
					users.add(user);
				};
			case "role": 
				if (user.getRoles().contains(value)){
					users.add(user);
				};
			}
		}
		
		return new ArrayList<User>();
	}
	
	
	
	
}
