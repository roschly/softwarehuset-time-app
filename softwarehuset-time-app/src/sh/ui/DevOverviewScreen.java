package sh.ui;

import java.io.IOException;
import java.io.PrintWriter;

import sh.app.*;

public class DevOverviewScreen extends Screen {
	
	private User user; 
	
	public DevOverviewScreen(User user) {
		this.user = user; 
	}
	
	@Override
	public void printMenu(PrintWriter out) throws IOException {
		out.println("=== DEV: Overview");
		out.println("0: Back");
		out.println("1: Show projects");
		out.println("2: Select project");

	}

	@Override
	public boolean processInput(String input, PrintWriter out) {

		switch (input){
		case "0":
			timeAppUI.setScreen(new LoginScreen());
			break;
		case "1":
			Developer.showProjects(user);
			//timeAppUI.getTimeApp().projects;
			break;
		case "2":
			break;
		default:
			break;
		}
		
		return false;
	}

}
