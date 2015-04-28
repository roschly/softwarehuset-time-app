package sh.ui;

import java.io.IOException;
import java.io.PrintWriter;

import sh.app.Project;
import sh.app.User;

public class DevProjectScreen extends Screen {
	private User user; 
	private Project project;
	
	public DevProjectScreen(User user, Project project) {
		this.user = user; 
		this.project = project;
	}

	@Override
	public void printMenu(PrintWriter out) throws IOException {
		out.println("=== DEV: Project view");
		out.println("0: Back");
		out.println("1: Show tasks");
		out.println("2: Select task");

	}

	@Override
	public boolean processInput(String input, PrintWriter out) {
		switch (input){
		case "0":
			timeAppUI.setScreen(new DevOverviewScreen(this.user));
			break;
		case "1":
			break;
		case "2":
			break;
		default:
			break;
		}
		return false;
	}

}
