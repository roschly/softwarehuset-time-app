package sh.ui;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import sh.app.*; 

public class LoginScreen extends Screen {

	@Override
	public void printMenu(PrintWriter out) throws IOException {
		out.println("=== LOGIN");
		out.println("Enter username:");
	}

	//TODO Maybe implement with try catch 
	@Override
	public boolean processInput(String input, PrintWriter out){
		
		ArrayList<User> users = timeAppUI.getTimeApp().getUsersByProperty("username", input); 

		if (users.size() > 0) {
			User user = users.get(0);
			
			switch (user.getRole()) {
				case "admin": 
					timeAppUI.setScreen( new AdminOverviewScreen(user) );
					break;
				case "projectmanager": 
					timeAppUI.setScreen( new PMOverviewScreen(user) );
					break;
				case "developer": 
					timeAppUI.setScreen( new DevOverviewScreen(user) );
					break;
				// TODO: catch all case
			}
		}
		else {
				out.print("User doesn't exist!");
				timeAppUI.setScreen(new LoginScreen());
			}
		
		return false;
	}

}
	

