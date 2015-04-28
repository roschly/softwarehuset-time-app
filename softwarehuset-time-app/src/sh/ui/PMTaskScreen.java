package sh.ui;

import java.io.IOException;
import java.io.PrintWriter;

import sh.app.Project;
import sh.app.Task;
import sh.app.User;

public class PMTaskScreen extends Screen {

	private User user;
	private Project project;
	private Task task;
	
	public PMTaskScreen(User user, Project project, Task task) {
		this.user = user;
		this.project = project;
		this.task = task;
	}

	@Override
	public void printMenu(PrintWriter out) throws IOException {
		out.println("=== PM: Task view");
		out.println("0: Back");
		out.println("1: View available developers");
		out.println("2: Assign developer");
		out.println("3: Delete task");
	}

	@Override
	public boolean processInput(String input, PrintWriter out) {
		// TODO Auto-generated method stub
		return false;
	}

}
