package sh.ui;

import java.io.IOException;
import java.io.PrintWriter;

import sh.app.Activity;
import sh.app.Project;
import sh.app.Task;
import sh.app.User;

public class TaskScreen extends Screen {

	private User user;
	private Project project;
	private Task task;
	
	public TaskScreen(User user, Project project, Task task){
		this.user = user;
		this.project = project;
		this.task = task;
	}
	
	@Override
	public void printMenu(PrintWriter out) throws IOException {
		out.println("=== TASK VIEW");
		out.println("0: Back");
		//out.println("1: Delete task");		
		//out.println("2: View available developers");
		out.println("Show all developers"); // timeApp.getUsers()
		out.println("3 <dev name>: Assign developer"); // task.assignDev(dev)
		out.println("4: Show activities"); // task.getActivities , display ID DEV WEEK DURATION (displayList)
		out.println("5 <activity ID>: Select activity"); // select via ID
		out.println("6 <week> <duration>: Create your activity"); // task.addActivity( new Activity )

	}

	@Override
	public boolean processInput(String input, PrintWriter out) {
		switch (input){
		case "0":
			timeAppUI.setScreen( new ProjectScreen(this.user, this.project) );
			break;
		case "1":
			// Delete task
			break;
		case "2":
			// View available developers
			break;
		case "3":
			// Assign developer
			break;
		case "4":
			// Show activities
			break;
		case "5":
			// Select activity
			Activity activity;
			timeAppUI.setScreen( new ActivityScreen(this.user, this.project, this.task, activity) );
			break;
		case "6":
			// Create activity
			break;
		default:
			break;
		}
		return false;
	}

}
