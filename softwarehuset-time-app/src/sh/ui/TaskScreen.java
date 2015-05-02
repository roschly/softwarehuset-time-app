package sh.ui;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
		out.println("=== TASK VIEW: " + this.task.getName());
		out.println("0: Back");
		out.println("1: Show all developers"); // timeApp.getUsers()
		out.println("2 <developer name>: Assign developer"); // task.assignDev(dev)
		out.println("3: Show activities"); // task.getActivities , display ID DEV WEEK DURATION (displayList)
		out.println("4 <activity ID>: Select activity"); // select via ID
		out.println("5 <date: YYYY-MM-DD> <duration>: Create your activity"); // task.addActivity( new Activity )
		//out.println("1: Delete task");		
		//out.println("2: View available developers");
		
	}

	@Override
	public boolean processInput(String input, PrintWriter out) {
		
		String[] cmdInputs = input.split(" ");
		String cmdNumber = cmdInputs[0];
		
		switch (cmdNumber){
		case "0":
			timeAppUI.setScreen( new ProjectScreen(this.user, this.project) );
			break;
		case "1":
			// Show all developers
			ArrayList<User> users = timeAppUI.getTimeApp().getUsers();
			ArrayList<String> userNames = new ArrayList<String>();
			
			for (User user : users){
				userNames.add(user.getName());
			}
			out.println("List of developers");
			Screen.displayList( userNames );
			break;
		case "2":
			// Assign developer
			if (cmdInputs.length != 2){
				out.print("ERROR: Incorrect number of arguments");
				timeAppUI.setScreen( new TaskScreen(this.user, this.project, this.task) );
				break;
			}
			else {
				String devName = cmdInputs[1];
				User user = timeAppUI.getTimeApp().getUserByName(devName);	
				
				if (user == null){
					out.println("No developer with that name");
					timeAppUI.setScreen( new TaskScreen(this.user, this.project, this.task) );
				}
				else {
					try {
						this.task.addDeveloper(user, this.user, this.project);
						out.println("Developer: " + user.getName() + ", assigned to task: " + this.task.getName());
					} catch (Exception e) {
						out.println(e.getMessage());
					}
				}
				break;
			}			
		case "3":
			// Show activities
			ArrayList<Activity> activities = this.task.getActivities();
			ArrayList<String> activityIDs = new ArrayList<String>();
			
			for (Activity activity : activities){
				activityIDs.add( activity.getId().toString() );
			}
			out.println("List of activity IDs");
			Screen.displayList( activityIDs );
			break;
		case "4":
			// Select activity
			if (cmdInputs.length != 2){
				out.print("ERROR: Incorrect number of arguments");
				timeAppUI.setScreen( new TaskScreen(this.user, this.project, this.task) );
				break;
			}
			else {
				try {
					Integer id = Integer.parseInt(cmdInputs[1]);
					Activity activity = this.task.getActivityById(id);
					timeAppUI.setScreen( new ActivityScreen(this.user, this.project, this.task, activity) );
				} catch(Exception e){
					out.println(e.getMessage());
				}
				break;
			}			
		case "5":
			// Create your activity
			if (cmdInputs.length != 3){
				out.print("ERROR: Incorrect number of arguments");
				timeAppUI.setScreen( new TaskScreen(this.user, this.project, this.task) );
				break;
			}
			else {
				String date = cmdInputs[1];
				String duration = cmdInputs[2];
				
				try {
					Activity activity = new Activity(date, Double.parseDouble(duration), this.user, this.task );
					timeAppUI.setScreen( new ActivityScreen(this.user, this.project, this.task, activity) );
				} catch(Exception e){
					out.println(e.getMessage());
				}
			}
			break;
		default:
			out.print("ERROR: wrong input");
			break;
		}
		return false;
	}

}
