package sh.ui;

import java.io.IOException;
import java.io.PrintWriter;

import sh.app.Activity;
import sh.app.Project;
import sh.app.Task;
import sh.app.User;

public class EditActivityScreen extends Screen {

	private User user;
	private Project project;
	private Task task;
	private Activity activity;
	
	public EditActivityScreen(User user, Project project, Task task, Activity activity) {
		this.user = user;
		this.project = project;
		this.task = task;
		this.activity = activity;
	}

	@Override
	public void printMenu(PrintWriter out) throws IOException {
		out.println("=== EDIT ACTIVITY");
		out.println("0: Back");
		// some description of the format of editing	

	}

	@Override
	public boolean processInput(String input, PrintWriter out) {
		switch (input){
		case "0":
			timeAppUI.setScreen(new ActivityScreen(this.user, this.project, this.task, this.activity));
			break;
		case "<correct format>":
			break;
		default:
			break;
		}
		return false;
	}

}
