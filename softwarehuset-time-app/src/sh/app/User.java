package sh.app;

import java.util.ArrayList;

public class User {
	private String username;
	private String role;
	
	public User(String username, String role){
		this.setUsername(username);
		
		try {
			this.setRole(role);
		} catch(OperationNotAllowedException e){
			// TODO: handle error
		}
		
	}
	
	public String getUsername(){
		return this.username;
	}
	public void setUsername(String username){
		this.username = username;
	}
	
	public String getRole(){
		return this.role;
	}
	public void setRole(String role) throws OperationNotAllowedException{
		
		/*
		if ( !TimeApp.getRoles().contains(role) ){
			throw new OperationNotAllowedException("The role is not recognized", "Set role");
		}
		*/
		this.role = role;
	}
	
}
