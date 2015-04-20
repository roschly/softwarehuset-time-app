package sh.time.app;

public class User {
	public static int nextUserId = 0;
	
	public int id;
	public String username;
	public String password;
	public String role;
	
	
	
	public User(String username, String password, String role){
		this.id = User.nextUserId;
		User.nextUserId++;
		
		this.username = username;
		this.password = password;
		this.role = role;
	}
}
