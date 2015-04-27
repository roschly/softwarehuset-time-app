package sh.app;

import java.util.*;

import sh.time.app.User;

public class TimeApp {
	
	public static ArrayList<Project> projects = new ArrayList<Project>();
	public static ArrayList<User> users = new ArrayList<User>();
	public static HashSet<String> roles = new HashSet<String>();
	
	public static User currentUser;
	
	public static void main(String[] args){
		Double time = 3.5;
		
		System.out.print("HEY HEY");
		//System.out.println( checkFormat(2.0) );
		
	}
	
	public static Boolean checkFormat(Double a){
		if (a % 2 == 0){
			return true;
		}
		return false;
	}
	
	
}
