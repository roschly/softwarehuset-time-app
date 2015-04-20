package sh.time.app;

import java.util.*;
import java.lang.reflect.*;


public class TimeApp {

	public static Set<String> roles = new HashSet<String>(); 
	public static List<Project> projects = new ArrayList<Project>();
	public static UserbaseSet<User> users = new UserbaseSet<User>();
	
	public static Scanner console = new Scanner(System.in);
	public static User thisUser;
	
		
	
	
	public static void main(String[] args){
		
				
		// HARDCODE 
			// roles
			roles.add("admin");
			roles.add("dev");
			roles.add("projectmanager");
		
			//users
			users.add(new User("admin", "a", "admin"));
			users.add(new User("dev1", "d1", "dev"));
			
			
		
		
		// login
		thisUser = logIn();
		
		try{
			session();
		}
		catch (Exception e){
			System.out.println(e.getMessage());
			
		}
			
		
		
	}
	
	public static User logIn(){
		User user = null;
		String username;
		String password;
		
		System.out.println("LOGIN:");
		
		System.out.print("Username: ");
		username = console.next();
		user = users.getByProperty("username", username);
		
		while (user == null){
			System.out.println("Username does not exist");
			System.out.print("Username: ");
			username = console.next();
			
			user = users.getByProperty("username", username);
		}
		
		System.out.print("Password: ");
		password = console.next();
		
		while ( !user.password.equals(password) ){
			System.out.println("Wrong password! Try again");
			System.out.print("Password: ");
			password = console.next();
		}
		
		System.out.println("LOGIN completed");
		
		return user;
	}
	
	public static void session() throws ClassNotFoundException{
		
		if ( thisUser.role.equals("admin") ){
			
			Class<?> cls = Class.forName("sh.time.app.Admin");
			
			Method[] m = cls.getMethods();
			
			System.out.println("HEY: " + m[0]);
			
			System.out.println("Available actions:");
			System.out.println("----------");
			System.out.println("1: create project");
			System.out.println("2: assign project manager");
			System.out.print("> ");
			int action = console.nextInt();
			
			if (action == 1){
				String name;
				
				System.out.println();
				System.out.println("Create project");
				System.out.println("----------");
				System.out.print("Project name: ");
				name = console.next();
				
				Project newProject = new Project(name);
				TimeApp.projects.add(newProject);
				
				
			}
			
			if (action == 2){
				System.out.println();
				System.out.println("Assign project manager");
				System.out.println("----------");
				System.out.print("Choose : ");
			}
		}
	}
	 


}
