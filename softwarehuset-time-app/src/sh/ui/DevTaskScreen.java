package sh.ui;

import java.io.IOException;
import java.io.PrintWriter;

import sh.app.Project;
import sh.app.Task;
import sh.app.User;

public class DevTaskScreen extends Screen {
	private User user; 
	private Project project;
	private Task task;
	
	public DevTaskScreen(User user, Project project, Task task) {
		this.user = user; 
		this.project = project;
		this.task = task;
	}

	@Override
	public void printMenu(PrintWriter out) throws IOException {
		out.println("=== DEV: Task view");
		out.println("0: Back");
		out.println("1: Show activities");
		out.println("2: Select activity");
		out.println("3: Create activity");

	}

	@Override
	public boolean processInput(String input, PrintWriter out) {
		switch (input){
		case "0":
			timeAppUI.setScreen(new DevProjectScreen(this.user, this.project));
			break;
		case "1":
			break;
		case "2":
			break;
		case "3":
			break;
		default:
			break;
		}
		return false;
	}

}
