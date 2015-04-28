package sh.ui;

import java.io.IOException;
import java.io.PrintWriter;

import sh.app.Activity;
import sh.app.Project;
import sh.app.Task;
import sh.app.User;

public class DevActivityScreen extends Screen {
	private User user; 
	private Project project;
	private Task task;
	private Activity activity;
	
	public DevActivityScreen(User user, Project project, Task task, Activity activity) {
		this.user = user; 
		this.project = project;
		this.task = task;
		this.activity = activity;
	}

	@Override
	public void printMenu(PrintWriter out) throws IOException {
		out.println("=== DEV: Activity view");
		out.println("0: Back");
		out.println("1: Edit activity");
		out.println("2: Delete activity");

	}

	@Override
	public boolean processInput(String input, PrintWriter out) {
		switch (input){
		case "0":
			timeAppUI.setScreen(new DevTaskScreen(this.user, this.project, this.task));
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
