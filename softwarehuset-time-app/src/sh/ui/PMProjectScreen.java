package sh.ui;

import java.io.IOException;
import java.io.PrintWriter;

import sh.app.Project;
import sh.app.User;

public class PMProjectScreen extends Screen {

	User user;
	Project project;
	
	public PMProjectScreen(User user, Project project) {
		this.user = user;
		this.project = project;
	}

	@Override
	public void printMenu(PrintWriter out) throws IOException {
		out.println("=== PM: Project view");
		out.println("0: Back");
		out.println("1: Project summary");
		out.println("2: Show tasks");
		out.println("3: Select task");
		out.println("4: Create task");
		

	}

	@Override
	public boolean processInput(String input, PrintWriter out) {
		// TODO Auto-generated method stub
		return false;
	}

}
