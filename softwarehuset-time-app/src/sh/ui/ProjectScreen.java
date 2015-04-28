package sh.ui;

import java.io.IOException;
import java.io.PrintWriter;

import sh.app.Project;
import sh.app.User;

public class ProjectScreen extends Screen {

	private User user;
	private Project project;
	
	public ProjectScreen(User user, Project project) {
		this.user = user;
		this.project = project;
	}

	@Override
	public void printMenu(PrintWriter out) throws IOException {
		out.println("=== PROJECT VIEW: " + this.project.getName());
		out.println("0: Back");
		out.println("1: Delete project");
		out.println("2: Show tasks");
		out.println("3: Select task");
		out.println("4: Create task");
		//out.println("5: Assign project manager");
		//out.println("6: Project summary");
	}

	@Override
	public boolean processInput(String input, PrintWriter out) {
		switch (input){
		case "0":
			timeAppUI.setScreen( new OverviewScreen(this.user) );
			break;
		case "1":
			// Delete project			
			break;
		case "2":
			// Show tasks
			break;
		case "3":
			// Select task
			//Task task;
			//timeAppUI.setScreen( new TaskScreen(this.user, this.project, task) );
			break;
		case "4":
			// Create task
			break;
		default:
			break;
		}
		return false;
	}

}
