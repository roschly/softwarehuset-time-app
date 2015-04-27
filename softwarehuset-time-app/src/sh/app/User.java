package sh.app;

import java.util.ArrayList;

public class User {
	private String username;
	private String password;
	private ArrayList<String> roles = new ArrayList<String>();
	
	public User(String username, String password, ArrayList<String> roles){
		this.setUsername(username);
		this.setPassword(password);
		
		try {
			this.setRoles(roles);
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
	
	public String getPassword(){
		return this.password;
	}
	public void setPassword(String password){
		this.password = password;
	}
	
	public ArrayList<String> getRoles(){
		return this.roles;
	}
	public void setRoles(ArrayList<String> roles) throws OperationNotAllowedException{
		for (String role : roles){
			if ( !TimeApp.roles.contains(role) ){
				throw new OperationNotAllowedException("One of the roles is recognized", "Set roles");
			}
		}
		
		this.roles = roles;
	}
	
}
