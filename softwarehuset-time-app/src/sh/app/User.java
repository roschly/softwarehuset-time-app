package sh.app;

public class User {
	private String username;
	
	public User(String username){
		this.setUsername(username);
	}
	
	public String getUsername(){
		return this.username;
	}
	public void setUsername(String username){
		this.username = username;
	}

}
