package sh.ui;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import sh.app.Project;
import sh.app.Task;
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
		out.println("1: Show tasks");
		out.println("2 <taskname>: Select task");
		out.println("3 <start date> <end date> <taskname> <estimatedTime>: Create task");
		out.println("4: Show all developers");
		out.println("5 <developer name>: Assign project manager");
	}

	@Override
	public boolean processInput(String input, PrintWriter out) {
		String[] cmdInputs = input.split(" ");
		String cmdNumber = cmdInputs[0];
		
		switch (cmdNumber){
		case "0":
			timeAppUI.setScreen( new OverviewScreen(this.user) );
			break;
		case "1":
			// Show tasks
			ArrayList<Task> tasks = this.project.getTasks();
			ArrayList<String> taskNames = new ArrayList<String>();
			
			for (Task task : tasks){
				taskNames.add(task.getName());
			}
			out.println("List of tasks");
			Screen.displayList( taskNames );
			break;
		case "2":
			// Select task
			if (cmdInputs.length != 2){
				out.print("ERROR: Incorrect number of arguments");
				timeAppUI.setScreen( new ProjectScreen(this.user, this.project) );
				break;
			}
			else {
				String cmdArgument = cmdInputs[1];
				Task task = this.project.getTaskByName(cmdArgument);
				
				if (task == null){
					out.println("No task with that name");
					timeAppUI.setScreen( new ProjectScreen(this.user, this.project) );
				}
				else {
					timeAppUI.setScreen( new TaskScreen(this.user, this.project, task) );
				}
				break;
			}			
		case "3":
			// Create task
			if (cmdInputs.length < 4){
				out.print("ERROR: Too few arguments");
				timeAppUI.setScreen( new ProjectScreen(this.user, this.project) );
				break;
			}
			else {
				String startDate = cmdInputs[1];
				String endDate = cmdInputs[2];
				String taskname = cmdInputs[3];
				String estimatedTime = cmdInputs[4];
				
				
				try {
					Task task = new Task(this.project, taskname, this.user, Double.parseDouble(estimatedTime), startDate, endDate);
					timeAppUI.setScreen( new TaskScreen(this.user, this.project, task) );
				} catch(Exception e){
					out.println(e.getMessage());
				}
				break;
			}
		case "4":
			// Show all developers
			ArrayList<User> users = timeAppUI.getTimeApp().getUsers();
			ArrayList<String> userNames = new ArrayList<String>();
			
			for (User user : users){
				userNames.add(user.getName());
			}
			out.println("List of developers");
			Screen.displayList( userNames );
			break;			
		case "5":
			// Assign Project manager
			if (cmdInputs.length != 2){
				out.print("ERROR: Incorrect number of arguments");
				timeAppUI.setScreen( new ProjectScreen(this.user, this.project) );
				break;
			}
			else {
				String devName = cmdInputs[1];
				User user = timeAppUI.getTimeApp().getUserByName(devName);	
				
				if (user == null){
					out.println("No developer with that name");
					timeAppUI.setScreen( new ProjectScreen(this.user, this.project) );
				}
				else {
					this.project.setProjectmanager(user);
					out.println("New Projectmanager: " + this.project.getProjectmanager().getName() + ", for project: " + this.project.getName());
				}
				break;
			}			
		default:
			out.print("ERROR: wrong input");
			break;
		}
		return false;
	}
}
