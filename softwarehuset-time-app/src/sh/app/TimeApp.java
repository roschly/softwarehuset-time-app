package sh.app;

import java.util.*;

import sh.app.User;

public class TimeApp {
	
	public ArrayList<Project> projects = new ArrayList<Project>();
	public ArrayList<User> users = new ArrayList<User>();
	public HashSet<String> roles = new HashSet<String>();
	
	public static User currentUser;
	
	public TimeApp(){
		this.roles.add("admin");
		this.roles.add("projectmanager");
		this.roles.add("developer");
		
		this.users.add( new User("adm", "admin") );
		this.users.add( new User("pm", "projectmanager") );
		this.users.add( new User("dev", "developer") );
		
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
				if (user.getRole().contains(value)){
					users.add(user);
				};
			}
		}
		return users;
	}

	
}
