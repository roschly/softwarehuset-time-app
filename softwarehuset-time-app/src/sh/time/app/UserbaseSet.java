package sh.time.app;

import java.util.HashSet;

public class UserbaseSet<U> extends HashSet<User>{
	
	public User getByProperty(String property, Object value){
		// TODO: Improve. Maybe with case or something more generic/clever
		
		for (User u : TimeApp.users){
			if ( property.equals("username") ){
				if ( u.username.equals(value) ){
					return u;
				}
			}
			else if ( property.equals("password") ){
				if ( u.password.equals(value) ){
					return u;
				}
			}
		}
		
		return null;
	}

}
